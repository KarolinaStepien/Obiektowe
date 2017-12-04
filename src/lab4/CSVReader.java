package lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    String[] current;

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

    CSVReader(String filename,String delimiter) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        hasHeader = false;
    }

    CSVReader(String filename) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = ",";
        hasHeader = false;
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        reader = new BufferedReader(reader);
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
        String[] header = split(line);
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
        this.current = split(line);
        return true;
    }

    //zwraca etykiety kolumn
    List<String> getColumnLabels(){
        return columnLabels;
    }

    //zwraca długość bieżącego wczytanego rekordu
    int getRecordLength(){
        return current.length;
    }

    //czy wartość istnieje w bieżącym rekordzie
    boolean isMissing(int columnIndex){
        return columnIndex >= this.getRecordLength() || Objects.equals(current[columnIndex], "");
    }

    //analogiczny dostęp przez etykietę kolumny
    boolean isMissing(String columnLabel){
        return columnLabelsToInt.get(columnLabel) >= this.getRecordLength() || current[columnLabelsToInt.get(columnLabel)].equals("");
    }

    String get(int columnIndex){
        if(columnIndex>current.length || columnIndex<0 || Objects.equals(current[columnIndex], "")) {
            throw new RuntimeException("Coś nie tak z tymi kolumnami");
        }
        return current[columnIndex];
    }

    //zwraca wartość jako String, raczej pusty tekst, a nie null
    String get(String columnLabel){
        if(columnLabelsToInt.get(columnLabel)>current.length || columnLabelsToInt.get(columnLabel)<0 ||
                Objects.equals(current[columnLabelsToInt.get(columnLabel)], "")) {
            throw new RuntimeException("Coś nie tak z tymi kolumnami");
        }
        return current[columnLabelsToInt.get(columnLabel)];
    }

    //funkcja konwertuje wartość do int, użyj Integer.parseInt()
    //funkcja wygeneruje wyjątek, jeśli pole było puste
    int getInt(int columnIndex){
        if(columnIndex>current.length || columnIndex<0 || Objects.equals(current[columnIndex], "")) {
            throw new RuntimeException("Coś nie tak z tymi kolumnami");
        }
        return Integer.parseInt(current[columnIndex]);
    }

    int getInt(String columnLabel){
        if(columnLabelsToInt.get(columnLabel)>current.length || columnLabelsToInt.get(columnLabel)<0 ||
                Objects.equals(current[columnLabelsToInt.get(columnLabel)], "")) {
            throw new RuntimeException("Coś nie tak z tymi kolumnami");
        }
        return Integer.parseInt(this.get(columnLabel)); //Integer.parseInt(current[columnLabelsToInt.get(columnLabel)])
    }

    long getLong(int columnIndex){
        if(columnIndex>current.length || columnIndex<0 || Objects.equals(current[columnIndex], "")) {
            throw new RuntimeException("Coś nie tak z tymi kolumnami");
        }
        return Long.parseLong(current[columnIndex]);
    }

    long getLong(String columnLabel){
        if(columnLabelsToInt.get(columnLabel)>current.length || columnLabelsToInt.get(columnLabel)<0 ||
                Objects.equals(current[columnLabelsToInt.get(columnLabel)], "")) {
            throw new RuntimeException("Coś nie tak z tymi kolumnami");
        }
        return Long.parseLong(this.get(columnLabel));
    }

    double getDouble(int columnIndex){
        if(columnIndex>current.length || columnIndex<0 || Objects.equals(current[columnIndex], "")) {
            throw new RuntimeException("Coś nie tak z tymi kolumnami");
        }
        return Double.parseDouble(current[columnIndex]);
    }

    double getDouble(String columnLabel){
        if(columnLabelsToInt.get(columnLabel)>current.length || columnLabelsToInt.get(columnLabel)<0 ||
                Objects.equals(current[columnLabelsToInt.get(columnLabel)], "")) {
            throw new RuntimeException("Coś nie tak z tymi kolumnami");
        }
        return Double.parseDouble(this.get(columnLabel));
    }

    String[] split(String line) {
        String[] splitLine = line.split(this.delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        for (int i = 0; i < splitLine.length; i++) {
            splitLine[i] = this.trimQuotes(splitLine[i]);
        }
        return splitLine;
    }

    String trimQuotes(String str) {
        return str.replaceAll("^\"", "").replaceAll("\"$", "");
    }

    LocalDate getDate(int columnIndex, DateTimeFormatter format){
        return LocalDate.parse(current[columnIndex],format);
    }

    LocalDate getDate(String columnLabel, DateTimeFormatter format){
        return LocalDate.parse(current[columnLabelsToInt.get(columnLabel)], format);
    }

    LocalTime getTime(int columnIndex, DateTimeFormatter format){
        return LocalTime.parse(current[columnIndex],format);
    }

    LocalTime getTime(String columnLabel, DateTimeFormatter format){
        return LocalTime.parse(current[columnLabelsToInt.get(columnLabel)], format);
    }
}