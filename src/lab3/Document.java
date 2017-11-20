package lab3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Document {
    @XmlAttribute
    protected String title;
    @XmlElement
    protected Photo photo;
    @XmlElement(name = "section")
    protected List<Section> sections = new ArrayList<>();

    Document(){}

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

    public void write(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            FileWriter writer= new FileWriter(fileName);;
            m.marshal(this, writer);
        } catch (JAXBException | IOException ex) {
            ex.printStackTrace();
        }

    }
    public static Document read(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Unmarshaller m = jc.createUnmarshaller();
            FileReader reader = new FileReader(fileName);
            return (Document) m.unmarshal(reader);
        } catch (JAXBException | FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
