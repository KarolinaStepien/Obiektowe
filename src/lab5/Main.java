package lab5;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AdminUnitList aul = new AdminUnitList();
        String filename = "src/lab5/admin-units.csv";

        try {
            aul.read(filename);
            aul.list(System.out, 4, 3);
            System.out.println();
            aul.selectByName("Kraków", false).list(System.out);
        } catch (IOException e) {
            System.out.println("bububu");
        }
    }
}
