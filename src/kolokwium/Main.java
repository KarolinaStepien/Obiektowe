package kolokwium;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        IbukUnitList iul = new IbukUnitList();
        String filename = "src/kolokwium/ibuk_wykaz_pozycji.csv";
        try {
            iul.read(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //wydawnictwo
        System.out.println("Wydawnictwo Naukowe PWN:");
        iul.filter(a -> a.wydawnictwo.equals("Wydawnictwo Naukowe PWN")).list(System.out);

        //kategoria
        Map<String, Integer> kategoria_ilosc = new TreeMap<>();
        for (IbukUnit u : iul.units){
            if (!kategoria_ilosc.containsKey(u.kategoria)){
                kategoria_ilosc.put(u.kategoria, 1);
            }
            else {
                kategoria_ilosc.replace(u.kategoria, kategoria_ilosc.get(u.kategoria)+1);
            }
        }
        System.out.println("Kategorie: ");
        System.out.println(kategoria_ilosc.toString());

        //rok
        Map<Integer, Integer> rok_ilosc = new TreeMap<>();
        for (IbukUnit u : iul.units){
            if (!rok_ilosc.containsKey(u.rok)){
                rok_ilosc.put(u.rok, 1);
            }
            else {
                rok_ilosc.replace(u.rok, rok_ilosc.get(u.rok)+1);
            }
        }
        System.out.println("Lata: ");
        System.out.println(rok_ilosc.toString());
    }
}
