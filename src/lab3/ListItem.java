package lab3;

import java.io.PrintStream;

public class ListItem {
    String content;

    ListItem (String content){
        this.setItem(content);
    }

    void setItem (String my_item){
        this.content = my_item;
    }

    void writeHTML(PrintStream out){
        out.printf("<li>%s</li>\n", content);
    }
}
