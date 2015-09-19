package sf.practise.ws.rs.jaxrs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sathih
 * Date: 11/9/14
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
@ApplicationPath("/resourceA")
public class RestFullAdage extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(Adages.class);
        return classSet;
    }

    @Override
    public Set<Object> getSingletons() {
        return super.getSingletons();
    }
}
