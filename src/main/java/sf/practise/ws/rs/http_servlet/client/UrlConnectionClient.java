package sf.practise.ws.rs.http_servlet.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: sathih
 * Date: 10/9/14
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class UrlConnectionClient {

    public static void main(String[] args) {
        if(args.length < 1){
            System.err.println("Application Invoke URL is required");
        }
        try {
            URL url = new URL(args[0]);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String record = null;
            while((record = bufferedReader.readLine())!=null){
                System.out.println(record);
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException("The URl is not correct\n"+e);
        } catch (IOException e) {
            throw new RuntimeException("Could not open connection\n"+e);
        }
    }
}
