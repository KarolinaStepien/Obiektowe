package lab4;

import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("with-header.csv",",",true);
        while(reader.next()){
            int id = reader.getInt("id");
            String surname = reader.get("nazwisko");
            double number = reader.getDouble("nrdomu");

            System.out.printf(Locale.Us,"%d %s %d",id, surname, number);
        }

        /*CSVReader reader = new CSVReader("titanic-part.csv",",",true);
        while(reader.next()){
            int id = reader.getInt(0);
            String name = reader.get(3);
            double fare = reader.getDouble(9);
            System.out.printf(Locale.Us,"%d %s %d",id, name, fare);

        }*/
    }
}
