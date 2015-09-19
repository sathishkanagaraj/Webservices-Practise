package sf.practise.ws.rs.http_servlet.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: sathih
 * Date: 10/9/14
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleHttpClient {

    public static void main(String[] args) {
        if(args.length <1){
            System.err.println("Invoke URL required <url>");
        }
        try {
            URL url = new URL(args[0]);
            String host = url.getHost();
            int port = url.getPort();
            String path = url.getPath();
            String request = "GET "+ path +" HTTP/1.1\n";
            request += "host: " + host;
            request += "\n\n";
            Socket socket = new Socket(host,port);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(request);
            printWriter.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String record = null;
            while((record = bufferedReader.readLine())!=null){
                System.out.println(record);
            }
            socket.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Not a valid URL. \n"+e);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Please try again. Unknown host \n"+e);
        } catch (IOException e) {
            throw new RuntimeException("Please tyy again. Something's wrong\n"+e);
        }
    }
}
