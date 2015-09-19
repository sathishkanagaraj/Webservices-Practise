package sf.practise.ws.rs.http_servlet;

import org.json.JSONObject;
import org.json.XML;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: sathih
 * Date: 10/9/14
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class PredictionsServlet extends HttpServlet {

    private Predictions predictions;

    @Override
    public void init() throws ServletException {
        predictions = new Predictions();
        predictions.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        Integer key = (idParam == null) ? null: Integer.valueOf(idParam.trim());
        boolean json = false;
        String accept = req.getHeader("accept");
        if(accept!=null && accept.contains("json")){
            json = true;
        }
        if(key == null){
            Collection<Prediction> values = predictions.getMap().values();
            Object[] objects = values.toArray();
            Arrays.sort(objects);
            String xml = predictions.toXML(objects);
            sendResponse(resp,xml,json);
        }else{
            Prediction prediction = predictions.getMap().get(key);
            if(prediction == null){
                String msg = key + "does not mapped to any prediction";
                sendResponse(resp,predictions.toXML(msg),false);
            } else{
                sendResponse(resp,predictions.toXML(prediction),json);
            }
        }
    }

    private void sendResponse(HttpServletResponse resp, String payload, boolean json) {
        if(json){
            JSONObject jsonObject = XML.toJSONObject(payload);
            payload = jsonObject.toString(3);
        }
        try {
            OutputStream outputStream = resp.getOutputStream();
            outputStream.write(payload.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            throw  new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String whoParam = req.getParameter("who");
        String whatParam = req.getParameter("what");
        System.out.println("whatParam = " + whatParam);
        System.out.println("whoParam = " + whoParam);
        if(whoParam == null || whatParam == null){
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
        }
        Prediction prediction = new Prediction();
        prediction.setWho(whoParam);
        prediction.setWhat(whatParam);
        int id = predictions.addPrediction(prediction);
        String msg = "Prediction "+ id + " Created.\n";
        sendResponse(resp,predictions.toXML(msg),false);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String data = bufferedReader.readLine();
        //assume data like (id=33#who=sathish}
        boolean isWhoAvailable = false;
        String who = null;
        String what = null;
        String[] dataSplit = data.split("#");
        String[] idPart = dataSplit[0].split("=");
        String key = idPart[1];
        String[] rest = dataSplit[1].split("=");
        if(dataSplit[1].contains("who")){
            isWhoAvailable = true;
            who = rest[1];
        } else{
            what = rest[1];
        }
        if(key == null){
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
        }
        Prediction prediction = predictions.getMap().get(Integer.valueOf(key));
        if(prediction == null){
            String msg = key + " Doest not map to any prediction. \n";
            sendResponse(resp,msg,false);
        }
        if(isWhoAvailable){
            prediction.setWho(who);
        } else{
            prediction.setWhat(what);
        }
        String msg = "Prediction " + key + " has been edited.\n";
        sendResponse(resp, msg, false);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if(idParam == null){
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
        }
        Integer key = Integer.valueOf(idParam.trim());
        predictions.getMap().remove(key);
        String msg = "Prediction " + key + " Removed.\n";
        sendResponse(resp, msg, false);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw  new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw  new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw  new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
