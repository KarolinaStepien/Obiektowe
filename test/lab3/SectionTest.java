package lab3;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertTrue;

public class SectionTest {
    @Test
    public void writeHTML() throws Exception {
        String title = "Jan Kowalski";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        Paragraph nana = new Paragraph("aaa");
        new Section(title).
                addParagraph(nana).
                writeHTML(ps);

        String result = null;

        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //System.out.println(result);

        assertTrue(result.contains("<section>"));
        assertTrue(result.contains("<h2>"));
        assertTrue(result.contains("<p"));
        assertTrue(result.contains(title));

    }

}