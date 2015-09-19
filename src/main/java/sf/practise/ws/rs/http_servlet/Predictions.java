package sf.practise.ws.rs.http_servlet;

import javax.servlet.ServletContext;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: sathih
 * Date: 10/9/14
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class Predictions {

    private ConcurrentMap<Integer,Prediction> predictions;
    private ServletContext sctx;
    private AtomicInteger mapKey;

    public Predictions() {
        predictions = new ConcurrentHashMap<>();
        mapKey = new AtomicInteger();
    }

    public void setServletContext(ServletContext sctx){
        this.sctx = sctx;
    }

    public ServletContext getServletContext(){
        return this.sctx;
    }

    public ConcurrentMap<Integer, Prediction> getMap(){
        if(getServletContext() == null){
            return null;
        }
        if(predictions.size() < 1){
            populate();
        }
        return this.predictions;
    }

    private void populate() {
        InputStream inputStream = sctx.getResourceAsStream("/WEB-INF/data/predictions.txt");
        if(inputStream!=null){
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String record;
                while ((record = bufferedReader.readLine())!=null){
                    String[] parts = record.split("!");
                    Prediction prediction = new Prediction();
                    prediction.setWho(parts[0]);
                    prediction.setWhat(parts[1]);
                    addPrediction(prediction);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int addPrediction(Prediction prediction) {
        int id = mapKey.getAndIncrement();
        predictions.put(id,prediction);
        return id;
    }

    public String toXML(Object o){
        String xmlString = null;
        try{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(byteArrayOutputStream);
        xmlEncoder.writeObject(o);
        xmlEncoder.close();
        xmlString = byteArrayOutputStream.toString();
        }catch (Exception e){}
        return xmlString;
    }
}
