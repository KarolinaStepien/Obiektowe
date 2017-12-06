package lab5;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();

    public AdminUnit(){}

    /*public AdminUnit(String name, int adminLevel, double population, double area, double density){
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = population;
        this.area = area;
        this.density = density;
    }*/

    public String toString(){
        StringBuilder sb = new StringBuilder(String.format(
                "Nazwa: %s, typ jednostki: %d, populacja: %f, powierzchnia: %f, gestosc zaludnienia: %f",
                this.name, this.adminLevel, this.population, this.area, this.density));
        //String s = "%d" + Integer.toString(this.adminLevel);
        //String s2 = "%d" + String.valueOf(this.adminLevel);
        return sb.toString();
    }
}
