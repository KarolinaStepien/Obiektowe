package lab5;

import java.util.ArrayList;
import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    //BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children = new ArrayList<>();

    public AdminUnit(){}

    public String toString(){
        StringBuilder sb = new StringBuilder(String.format(
                "Nazwa: %s, typ jednostki: %d, populacja: %f, powierzchnia: %f, gestosc zaludnienia: %f",
                this.name, this.adminLevel, this.population, this.area, this.density));
        //String s = "%d" + Integer.toString(this.adminLevel);
        //String s2 = "%d" + String.valueOf(this.adminLevel);
        return sb.toString();
    }

    //uzupelnienie brakujacych wartosci dla danego elementu
    protected void fixMissingValues(){
        if(this.density == 0 && this.parent != null){
            if(this.parent.density == 0) {
                this.parent.fixMissingValues();
            }
            this.density = this.parent.density;
        }
        if(this.population == 0){
            this.population = this.area*this.density;
        }
    }
}
