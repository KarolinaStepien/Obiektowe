package kolokwium;

public class IbukUnit {
    double id;
    String tytul;
    String autor;
    String isbn;
    String wydawnictwo;
    double rok;
    String kategoria;
    String podkategoria;
    String link;

    public IbukUnit(){}

    public String toString(){
        StringBuilder sb = new StringBuilder(String.format(
                "Ibuk ID: %f, Tytuł: %s, Autor: %s, ISBN: %s, Wydawnictwo: %s, Rok wydania: %f," +
                        " Kategoria: %s, Podkategoria: %s, Link do książki: %s ",
                this.id, this.tytul, this.autor, this.isbn, this.wydawnictwo, this.rok, this.kategoria, this.podkategoria, this.link));
        return sb.toString();
    }
}

