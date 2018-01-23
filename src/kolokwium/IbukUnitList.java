package kolokwium;

import lab4.CSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IbukUnitList {
    List<IbukUnit> units = new ArrayList<>();

    public IbukUnitList() {
    }

    public IbukUnitList(Stream<IbukUnit> adminUnitStream) {
        units = adminUnitStream.collect(Collectors.toList());
    }

    //czyta rekordy pliku i dodaje do listy
    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ";", true);

        while (reader.next()) {
            IbukUnit au = new IbukUnit();

            if (!reader.isMissing("Ibuk ID")) {
                au.id = reader.getDouble("Ibuk ID");
            }
            if (!reader.isMissing("Tytuł")) {
                au.tytul = reader.get("Tytuł");
            }
            if (!reader.isMissing("Autor")) {
                au.autor = reader.get("Autor");
            }
            if (!reader.isMissing("ISBN")) {
                au.isbn = reader.get("ISBN");
            }
            if (!reader.isMissing("Wydawnictwo")) {
                au.wydawnictwo = reader.get("Wydawnictwo");
            }
            if (!reader.isMissing("Rok wydania")) {
                au.rok = reader.getDouble("Rok wydania");
            }
            if (!reader.isMissing("Kategoria")) {
                au.kategoria = reader.get("Kategoria");
            }
            if (!reader.isMissing("Podkategoria")) {
                au.podkategoria = reader.get("Podkategoria");
            }
            if (!reader.isMissing("Link do książki")) {
                au.link = reader.get("Link do książki");
            }
            units.add(au);
        }
    }

    //FILTROWANIE
    //tylko te jednostki, dla których metoda test() zwraca true
    IbukUnitList filter(Predicate<IbukUnit> pred) { //referencja do interfejsu Predicate
        return new IbukUnitList(units.stream().filter(pred));
    }
}

