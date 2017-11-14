package lab3;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class UnorderedListTest {
    @Test
    public void writeHTML() throws Exception {
        List<ListItem> list_of_items = new ArrayList<>() ;

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new UnorderedList().writeHTML(ps);
        String result = null;

        try{
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(result);

        assertTrue(result.contains("<ul>"));
        assertTrue(result.contains("</ul"));
    }

}