package lab5;

import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        AdminUnitList aul = new AdminUnitList();
        String filename = "src/lab5/admin-units.csv";

        try {
            aul.read(filename);
            aul.fixAll();

            /*aul.list(System.out, 4, 3);
            System.out.println();
            aul.selectByName("Kraków", false).list(System.out);*/

            AdminUnit karo = aul.units.get(4454);
            AdminUnitList karoList = aul.getNeighbors(karo, 15);
            karoList.list(System.out);

            //czas wyszukiwania sasiadow
            double t1 = System.nanoTime()/1e6;
            // wywołanie funkcji
            double t2 = System.nanoTime()/1e6;
            System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);

        } catch (IOException e) {
            System.out.println("bububu");
        }
    }
}
