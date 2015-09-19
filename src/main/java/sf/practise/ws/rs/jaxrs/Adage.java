package sf.practise.ws.rs.jaxrs;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: sathih
 * Date: 11/9/14
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "adage")
public class Adage {

    private String words;
    private int wordCount;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
        this.wordCount = words.trim().split("\\s+").length;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    @Override
    public String toString() {
        return "Adage{" +
                "words='" + words + '\'' +
                ", wordCount=" + wordCount +
                '}';
    }
}
