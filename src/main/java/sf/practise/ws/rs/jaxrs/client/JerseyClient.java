package sf.practise.ws.rs.jaxrs.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: sathih
 * Date: 11/9/14
 * Time: 6:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class JerseyClient {

    public static void main(String[] args) {
        Client client = Client.create();
        client.setFollowRedirects(true);
        WebResource resource = client.resource("http://localhost:9876/resourceA/json");
        String response = resource.accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println("response = " + response);
    }
}
