package lab4;

import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        CSVReader reader = null;
        try {
            reader = new CSVReader("src/lab4/CsvFiles/with-header.csv", ";", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (reader.next()) {
                String imie = reader.get("imie");
                double nrdomu = reader.getDouble("nrdomu");
                int nrmieszkania = reader.getInt(7); //zly wyjatek

                System.out.printf(Locale.US, "%s %f %d\n", imie, nrdomu, nrmieszkania);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*CSVReader reader = new CSVReader("src/lab4/with-header.csv", ";", true);
        while (reader.next()) {
        int id = reader.getInt(0);
        String name = reader.get(3);
        double fare = reader.getDouble(0);
        System.out.printf(Locale.US, "%d %s %f \n", id, name, fare);

    }*/
}
