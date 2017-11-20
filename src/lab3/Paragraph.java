package lab3;

import javax.xml.bind.annotation.XmlValue;
import java.io.PrintStream;

public class Paragraph {
    @XmlValue
    protected String content;

    Paragraph(){
        this.setContent("");
    }

    Paragraph(String content){
        this.setContent(content);
    }

    Paragraph setContent(String my_content){
        this.content = my_content;
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>%s</p>\n", content);
    }
}
