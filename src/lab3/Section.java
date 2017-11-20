package lab3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    @XmlAttribute
    protected String title;
    @XmlElements(value= {
            @XmlElement(name = "paragraph", type = Paragraph.class),
            @XmlElement(name = "paragraph-with-list", type = ParagraphWithList.class)
    })
    protected List<Paragraph> paragraps = new ArrayList<>() ;

    Section(String title){
        this.setTitle(title);
    }

    void setTitle(String title){
        this.title = title;
    }

    Section addParagraph(String paragraphText){
        Paragraph paragraph = new Paragraph(paragraphText);
        paragraps.add(paragraph);
        return this;
    }

    Section addParagraph(Paragraph p){
        paragraps.add(p);
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<section>\n" +
                "<h2>%s</h2>\n", title);
        for( Paragraph element : paragraps){
            element.writeHTML(out);
        }
        out.printf("</section>\n");
    }
}
