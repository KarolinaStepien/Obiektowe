package lab3;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> list_of_items = new ArrayList<>() ;

    void writeHTML(PrintStream out){
        out.print("<ul>\n");
        for (ListItem element : list_of_items) {
            element.writeHTML(out);
        }
        out.print("</ul>\n");
    }

}
