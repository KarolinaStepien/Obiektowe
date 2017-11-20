package lab3;

import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    @XmlElement(name = "items")
    List<ListItem> list_of_items = new ArrayList<>() ;

    void writeHTML(PrintStream out){
        out.print("<ul>\n");
        for (ListItem element : list_of_items) {
            element.writeHTML(out);
        }
        out.print("</ul>\n");
    }

}
