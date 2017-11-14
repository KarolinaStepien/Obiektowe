package lab3;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertTrue;

public class ParagraphTest {
    @Test
    public void writeHTML() throws Exception {
        String content = "nananananana" +
                "nanananananananananana" +
                "lalalalalal" +
                "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuu uuu";

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new Paragraph(content).writeHTML(ps);
        String result = null;

        try{
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //System.out.println(result);

        assertTrue(result.contains("<p>"));
        assertTrue(result.contains("</p"));
        assertTrue(result.contains(content));
    }

}