package lab5;

import lab4.CSVReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> idToUnit = new HashMap<>();
    Map<AdminUnit, Long> unitToParentId = new HashMap<>();

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
            if(!reader.isMissing("id")) {
                idToUnit.put(reader.getLong("id"), au);
            }
            if(!reader.isMissing("parent")) {
                unitToParentId.put(au, reader.getLong("parent"));
            }
            else { //if(reader.isMissing("parent")) {
                unitToParentId.put(au, null);
            }
            units.add(au);
        }
        for(AdminUnit a : units){
            if(unitToParentId.containsKey(a)) {
                Long parentId = unitToParentId.get(a);
                if (idToUnit.containsKey(parentId)) {
                    a.parent = idToUnit.get(parentId);
                    a.parent.children.add(a);
                }
            }
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
    public AdminUnitList selectByName(String pattern, boolean regex){
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

    //uzupelnienie brakujacych wartosci dla wszystkich jednostek
    protected void fixAll(){
        for(AdminUnit u : units){
            u.fixMissingValues();
        }
    }
}
