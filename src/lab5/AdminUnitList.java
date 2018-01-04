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

            if(!reader.isMissing("x1") && !reader.isMissing("y1")){
                au.bbox.addPoint(reader.getDouble("x1"), reader.getDouble("y1"));
            }
            if(!reader.isMissing("x2") && !reader.isMissing("y2")){
                au.bbox.addPoint(reader.getDouble("x2"), reader.getDouble("y2"));
            }
            if(!reader.isMissing("x3") && !reader.isMissing("y3")){
                au.bbox.addPoint(reader.getDouble("x3"), reader.getDouble("y3"));
            }
            if(!reader.isMissing("x4") && !reader.isMissing("y4")){
                au.bbox.addPoint(reader.getDouble("x4"), reader.getDouble("y4"));
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

    /**
     * Zwraca listę jednostek sąsiadujących z jendostką unit na tym samym poziomie hierarchii admin_level.
     * Czyli sąsiadami wojweództw są województwa, powiatów - powiaty, gmin - gminy, miejscowości - inne miejscowości
     * @param unit - jednostka, której sąsiedzi mają być wyznaczeni
     * @param maxdistance - parametr stosowany wyłącznie dla miejscowości, maksymalny promień odległości od środka unit,
     *                    w którym mają sie znaleźć punkty środkowe BoundingBox sąsiadów
     * @return lista wypełniona sąsiadami
     */
    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        AdminUnitList neighborsList = new AdminUnitList();
        for(AdminUnit au : units){
            if(unit.adminLevel == au.adminLevel){
                if(unit.adminLevel == 8){
                    if(unit.bbox.distanceTo(au.bbox)<maxdistance){
                        neighborsList.units.add(au);
                    }
                }
                else{
                    if(unit.bbox.intersects(au.bbox)){
                        neighborsList.units.add(au);
                    }
                }
            }
        }
        return neighborsList;
    }
}
