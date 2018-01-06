package lab5;

import java.io.IOException;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        AdminUnitList aul = new AdminUnitList();
        String filename = "src/lab5/admin-units.csv";

        try {
            //READ, FIX
            aul.read(filename);
            aul.fixAll();

            //LIST, SELECT
            //aul.list(System.out, 4, 3);
            //aul.selectByName("Kraków", false).list(System.out);

            //NEIGHBORS
            /*AdminUnit karo = aul.units.get(4454);
            AdminUnitList karoList = aul.getNeighbors(karo, 15);
            karoList.list(System.out);*/

            //czas wyszukiwania sasiadow
            //double t1 = System.nanoTime()/1e6;
            // wywołanie funkcji
            /*double t2 = System.nanoTime()/1e6;
            System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);*/

            //SORTOWANIE, FILTROWANIE
            //aul.filter(a -> a.name.startsWith("K")).sortInplaceByName().list(System.out);
            //aul.filter(a -> a.adminLevel == 6).list(System.out);
            //aul.filter(a -> a.population < 1).filter(a -> a.name.startsWith("H")).list(System.out);
            //aul.filter(a -> a.name.startsWith("W") || a.name.endsWith("o")).list(System.out);
            //aul.filter(a -> a.name.startsWith("Żu") && a.name.endsWith("e"), 1, 3).list(System.out);

            //QUERY
            AdminUnitQuery query = new AdminUnitQuery()
                    .selectFrom(aul)
                    .where(a->a.area>1000)
                    .or(a->a.name.startsWith("Sz"))
                    .sort(Comparator.comparingDouble(a -> a.area))
                    .limit(100);
            query.execute().list(System.out);

        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
}
