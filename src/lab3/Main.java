package lab3;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) {
        Document cv = new Document("Jana Kowalski - CV");
        cv.setPhoto("...");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("...");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Języki programowania")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );
        //do konsoli
        cv.writeHTML(System.out);

        //do pliku
        try {
            cv.writeHTML(new PrintStream("cv.html","UTF-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        cv.write("cv.xml");
        //Document cv2 = Document.read("cv.xml");
        //cv2.writeHTML(System.out);

    }
}
