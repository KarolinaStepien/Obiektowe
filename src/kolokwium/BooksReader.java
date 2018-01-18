package kolokwium;

import lab4.CSVReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class BooksReader {

    public static void main(String[] args) throws IOException {

        CSVReader reader = new CSVReader("src/kolokwium/ibuk_wykaz_pozycji.csv", ";", true);
        HashMap newmap = new HashMap();
        HashMap newmap2 = new HashMap();
        while (reader.next()) {

            int rok = reader.getInt("Rok wydania");
            if(!newmap.containsKey(rok)){
                newmap.put(rok, 1);
            }
            else{
                newmap.replace(rok, Integer.parseInt("newmap.get(rok) + 1)"));
            }

            String wydawnictwo = reader.get("Wydawnictwo");
            if(Objects.equals(wydawnictwo, "Wydawnictwo Naukowe PWN")){
                System.out.println(reader.get("Tytuł"));
            }

            String kategoria = reader.get("Kategoria");
            if(!newmap2.containsKey(kategoria)){
                newmap2.put(kategoria, 1);
            }
            else{
                newmap2.replace(kategoria, Integer.parseInt("newmap2.get(kategoria) + 1)"));
            }

        }
    }
}
