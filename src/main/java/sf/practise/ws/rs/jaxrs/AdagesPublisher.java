package sf.practise.ws.rs.jaxrs;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 * User: sathih
 * Date: 11/9/14
 * Time: 1:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdagesPublisher {

    public static final String URI = "/resourceA";
    public static final int PORT = 9876;
    public static final String URL = "http://localhost:"+ PORT + URI;

    public static void main(String[] args) {
        new AdagesPublisher().publish();
    }

    private void publish(){
        HttpServer httpServer = getServer();
        HttpHandler httpHandler = RuntimeDelegate.getInstance().createEndpoint(new RestFullAdage(), HttpHandler.class);
        httpServer.createContext(URI,httpHandler);
        httpServer.start();
        msg(httpServer);
    }

    private void msg(HttpServer httpServer) {
        String out = "Publishing RestfulAdage on " + URL + " . Hit any key to stop";
        System.out.println("out = " + out);
        try {
            System.in.read();
        } catch (IOException e) {
            httpServer.stop(0);
        }
    }

    private HttpServer getServer() {
        HttpServer httpServer = null;
        try {
            httpServer = HttpServer.create(new InetSocketAddress("localhost", 9876), 8);
        } catch (IOException e) {
             throw new RuntimeException(e);
        }
        return httpServer;
    }
}
