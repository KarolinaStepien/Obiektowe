package lab5;

import lab4.CSVReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();

    //czyta rekordy pliku i dodaje do listy
    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ",", true);

        while (reader.next()){
            AdminUnit au = new AdminUnit();

            if(!reader.isMissing("name")) {
                au.name = reader.get("name");
            }
            if(!reader.isMissing("admin_level")) {
                au.adminLevel = reader.getInt("admin_level");
            }
            if(!reader.isMissing("population")) {
                au.population = reader.getDouble("population");
            }
            if(!reader.isMissing("area")) {
                au.area = reader.getDouble("area");
            }
            if(!reader.isMissing("density")) {
                au.density = reader.getDouble("density");
            }

            units.add(au);
        }
    }

    //wypisuje zawartość korzystając z AdminUnit.toString()
    public void list(PrintStream out){
        for(AdminUnit a : units){
            out.println(a.toString());
        }
        //this.units.forEach(out::println);
    }

    //wypisuje co najwyżej limit elementów począwszy od elementu o indeksie offset
    public void list(PrintStream out, int offset, int limit ){
        for(int i=offset; i<(offset+limit); i++){
            out.println(units.get(i).toString());
        }
    }

    //zwraca nową listę zawierającą te obiekty AdminUnit, których nazwa pasuje do wzorca
    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for(AdminUnit a : units){
            if(regex){
                if(a.name.matches(pattern)) ret.units.add(a);
            }
            else{
                if(a.name.contains(pattern)) ret.units.add(a);
            }
        }
        return ret;
    }
}
