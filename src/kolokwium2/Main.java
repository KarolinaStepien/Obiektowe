package kolokwium2;

import java.io.IOException;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws IOException {
        AdminUnitList aul = new AdminUnitList();
        String filename = "src/lab5/admin-units.csv";

        aul.read(filename);
        aul.fixAll();
        aul.sortInplaceByName();

        for (int i = 0; i < aul.units.size(); i++) {
            AdminUnit unit = aul.units.get(i);
            PrintStream p = new PrintStream("src/kolokwium2/files/" + i + ".html", "UTF-8");
            p.println("<html><head><meta charset=\"utf-8\"><title>" + unit.name + "</title></head><body>");
            p.println("<h1>" + unit.name + "</h1>");
            p.println("<ul>");
            p.printf("<li>Obszar: %f</li>", unit.area);
            p.printf("<li>Liczba mieszkańców: %f</li>", unit.population);
            p.printf("<li>Gęstość zaludnienia: %f</li>", unit.density);
            p.println("</ul>");
            if (unit.parent != null) {
                p.printf("<p>Rodzic: <a href=\"%d.html\">%s</a></p>", aul.units.indexOf(unit.parent), unit.parent.name);
            }
            if (unit.children != null) {
                p.println("<p>Children:<ul>");
                for (AdminUnit child : unit.children) {
                    p.printf("<li><a href=\"%d.html\">%s</a></p></li>", aul.units.indexOf(child), child.name);
                }
                p.println("</ul></p>");
            }
            p.println("<p>Neighbours:<ul>");
            for (AdminUnit neighbour : aul.getNeighbors(unit, 20).units) {
                p.printf("<li><a href=\"%d.html\">%s</a></p></li>", aul.units.indexOf(neighbour), neighbour.name);
            }
            p.println("</ul></p>");


            p.println("</body></html>");
        }

        PrintStream p = new PrintStream("src/kolokwium2/files/roots.html", "UTF-8");
        p.println("<html><head><meta charset=\"utf-8\"><title>All units</title></head><body>");
        p.println("<h1>Admin Units</h1><ul>");
        for (int i = 0; i < aul.units.size(); i++) {
            AdminUnit unit = aul.units.get(i);
            p.printf("<li><a href=\"%d.html\">%s</a></li>\n", i, unit.name);
        }
        p.println("</ul></body></html>");
    }
}