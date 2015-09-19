package sf.practise.ws.rs.jaxrs;

import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: sathih
 * Date: 11/9/14
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/")
public class Adages {

    public String[] aphorisms = {"What can be shown cannot be said",
            "If a lion could talk, we could not understand him",
            "Philosophy is a battle against bewitchment of",
            "Our intelligence by means of language","Ambition is the death of thought",
            "The limits of my language is the limits of the world"};
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText(){
        return "Plain Text";
    }

    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public JAXBElement<Adage> getXml(){
        return convertToXml(createAdage());
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(){
        return convertToJson(createAdage());
    }

    private String convertToJson(Adage adage) {
        String json = "If you see this , there is a problem";
        try {
            json = new ObjectMapper().writeValueAsString(adage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private JAXBElement<Adage> convertToXml(Adage adage) {
        return new JAXBElement<>(new QName("adage"),Adage.class,adage);
    }

    private Adage createAdage() {
        Adage adage = new Adage();
        adage.setWords(aphorisms[new Random().nextInt(aphorisms.length)]);
        return adage;
    }
}
