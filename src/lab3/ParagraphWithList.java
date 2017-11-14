package lab3;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {
    protected UnorderedList ulist;

    ParagraphWithList(){
        super();
        this.ulist = new UnorderedList();
    }

    ParagraphWithList(String content) {
        super(content);
        this.ulist = new UnorderedList();
    }

    ParagraphWithList setContent(String content){
        super.setContent(content);
        return this;
    }

    ParagraphWithList addListItem(String item_name){
        ListItem item = new ListItem(item_name);
        ulist.list_of_items.add(item);
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>%s\n", content);
        ulist.writeHTML(out);
        out.printf("</p>\n");
    }
}
