package lab4;

import java.io.IOException;
import java.util.Locale;

//import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {

        /*CSVReader reader = new CSVReader("src/lab4/CsvFiles/with-header.csv", ";", true);
        while (reader.next()) {
            int id = reader.getInt("id");
            String name = reader.get("imię");
            double flat = reader.getDouble("nrmieszkania");

            System.out.printf(Locale.US, "%d %s %f \n", id, name, flat);
        }*/

        /*CSVReader reader = new CSVReader("src/lab4/CsvFiles/no-header.csv", ";", false);
        try {
            while (reader.next()) {
                int id = reader.getInt(0);
                String name = reader.get(1);
                double flat = reader.getDouble(5);

                System.out.printf(Locale.US, "%d %s %f \n", id, name, flat);
            }
        }
        catch (Exception e) {
            System.out.println("Blebleble");
        }*/

        CSVReader reader = new CSVReader("src/lab4/CsvFiles/titanic-part.csv", ",", true);
        try{
            while (reader.next()) {
                String name = reader.get("Name");

                System.out.printf(Locale.US, "%s \n", name);
            }
        }
        catch (Exception e) {
            System.out.println("Blebleble");
        }

        /*CSVReader reader = new CSVReader("src/lab4/CsvFiles/date-time-no-header.csv", ";", false);
        while (reader.next()) {
            LocalDate d = reader.getDate(0, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println(d);
        }*/

        /*CSVReader reader = new CSVReader("src/lab4/CsvFiles/date-time-with-header.csv", ";", true);
        while (reader.next()) {
            LocalTime t = reader.getTime("time", DateTimeFormatter.ofPattern("HH:mm"));
            System.out.println(t);
        }*/
    }
}
