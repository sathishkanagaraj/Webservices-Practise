package sf.practise.ws.rs.http_servlet;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sathih
 * Date: 10/9/14
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class Prediction implements Serializable,Comparable<Prediction> {

    private String who;
    private String what;
    private int id;

    public Prediction() {
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Prediction o) {
        return this.id - o.id;
    }
}
