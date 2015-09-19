package sf.practise.ws.soap;

import javax.xml.ws.Endpoint;

/**
 * Created with IntelliJ IDEA.
 * User: sathih
 * Date: 12/9/14
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class RandPublisher {

    public static void main(String[] args) {
        final String url = "http://localhost:8080/rs";
        System.out.println("Publishing Rand service at endpoint :" + url);
        Endpoint.publish(url, new RandService());
    }
}
