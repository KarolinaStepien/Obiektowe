package lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    /**
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }

    CSVReader(String filename,String delimiter) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
    }

    CSVReader(String filename) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(filename));
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(String.valueOf(reader)));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }

    void parseHeader() throws IOException {
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }
        // podziel na pola
        String[] header = line.split(delimiter);
        // przetwarzaj dane w wierszu
        for (int i = 0; i < header.length; i++) {
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }

    boolean next() throws IOException {
        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        String line = reader.readLine();
        if(line==null){
            return false;
        }
        String[]current = line.split(delimiter);
        return true;
    }

    //zwraca etykiety kolumn
    List<String> getColumnLabels(){
        return columnLabels;
    }

    //zwraca długość bieżącego wczytanego rekordu
    int getRecordLength() throws IOException {
    }

    //czy wartość istnieje w bieżącym rekordzie
    boolean isMissing(int columnIndex){
    }

    //analogiczny dostęp przez etykietę kolumny
    String get(int columnIndex){
    }

    //zwraca wartość jako String, raczej pusty tekst, a nie null
    String get(String columnLabel){
    }

    //funkcja konwertuje wartość do int, użyj Integer.parseInt()
    //funkcja wygeneruje wyjątek, jeśli pole było puste
    int getInt(int columnIndex){
    }

    int getInt(String columnLabel){
    }

    long getLong(int columnIndex){
    }

    long getLong(String columnLabel){
    }

    double getDouble(int columnIndex){
    }

    double getInt(String columnLabel){
    }

}