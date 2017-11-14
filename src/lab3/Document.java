package lab3;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    protected String title;
    protected Photo photo;
    protected List<Section> sections = new ArrayList<>();

    Document(String title){
        this.setTitle(title);
    }

    void setTitle(String title){
        this.title = title;
    }

    void setPhoto(String photoUrl){
        this.photo = new Photo(photoUrl);
    }

    Section addSection(String sectionTitle){
        Section section = new Section(sectionTitle);
        sections.add(section);
        return section;
    }

    Document addSection(Section s){
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<!DOCTYPE html>\n" +
                "<html lang=\"pl\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>CV</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>%s</h1>\n", title);
        photo.writeHTML(out);
        for ( Section element : sections){
            element.writeHTML(out);
        }
        out.printf("</body>\n" +
                "</html>\n");
    }
}
