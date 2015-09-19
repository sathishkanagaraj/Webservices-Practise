package sf.practise.ws.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: sathih
 * Date: 12/9/14
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService
public class RandService {

    private static final int maxRands = 16;

    @WebMethod
    public int next1(){
        return new Random().nextInt();
    }

    public int nextN(final int n){
        int t = 0;
        t = n+1;
        return t;
    }
}
