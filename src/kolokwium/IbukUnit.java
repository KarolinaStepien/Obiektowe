package kolokwium;

public class IbukUnit {
    int id;
    String tytul;
    String autor;
    String isbn;
    String wydawnictwo;
    int rok;
    String kategoria;
    String podkategoria;
    String link;

    public IbukUnit(){}

    public String titleToString(){
        StringBuilder sb = new StringBuilder(String.format(
                "%s", this.tytul));
        return sb.toString();
    }
}

