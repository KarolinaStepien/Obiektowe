package lab4;

import java.io.*;
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
        this.current = line.split(delimiter);
        return true;
    }

    //zwraca etykiety kolumn
    List<String> getColumnLabels(){
        return columnLabels;
    }

    //zwraca długość bieżącego wczytanego rekordu
    int getRecordLength() throws IOException {
        return current.length;
    }

    //czy wartość istnieje w bieżącym rekordzie
    boolean isMissing(int columnIndex) throws IOException {
        return columnIndex >= this.getRecordLength();
    }

    //analogiczny dostęp przez etykietę kolumny
    boolean isMissing(String columnLabel){
        return current[columnLabelsToInt.get(columnLabel)].equals("");
    }

    String get(int columnIndex){ //wyjatki
        return current[columnIndex];
    }

    //zwraca wartość jako String, raczej pusty tekst, a nie null
    String get(String columnLabel){ //wyjatki
        return current[columnLabelsToInt.get(columnLabel)];
    }

    //funkcja konwertuje wartość do int, użyj Integer.parseInt()
    //funkcja wygeneruje wyjątek, jeśli pole było puste
    int getInt(int columnIndex) throws IndexOutOfBoundsException, EmptyFieldException { //wyjatki
        if(Objects.equals(this.get(columnIndex), "")){
            throw new EmptyFieldException(columnIndex);
        }
        if (columnIndex >= columnLabels.size() || columnIndex < 0) {
            throw new IndexOutOfBoundsException();
        }
        return Integer.parseInt(current[columnIndex]);
    }

    int getInt(String columnLabel) throws IndexOutOfBoundsException, EmptyFieldException {
        if(Objects.equals(this.get(columnLabel), "")){
            throw new EmptyFieldException(columnLabelsToInt.get(columnLabel));
        }
        if (columnLabelsToInt.get(columnLabel) >= columnLabels.size() || (columnLabelsToInt.get(columnLabel) < 0)) {
            throw new IndexOutOfBoundsException();
        }
        return Integer.parseInt(this.get(columnLabel));
    }

    long getLong(int columnIndex) throws IndexOutOfBoundsException, EmptyFieldException { //wyjatki
        if(Objects.equals(this.get(columnIndex), "")){
            throw new EmptyFieldException(columnIndex);
        }
        if (columnIndex >= columnLabels.size() || columnIndex < 0) {
            throw new IndexOutOfBoundsException();
        }
        return Long.parseLong(current[columnIndex]);
    }

    long getLong(String columnLabel) throws IndexOutOfBoundsException, EmptyFieldException {
        if(Objects.equals(this.get(columnLabel), "")){
            throw new EmptyFieldException(columnLabelsToInt.get(columnLabel));
        }
        if (columnLabelsToInt.get(columnLabel) >= columnLabels.size() || columnLabelsToInt.get(columnLabel) < 0) {
            throw new IndexOutOfBoundsException();
        }
        return Long.parseLong(this.get(columnLabel));
    }

    double getDouble(int columnIndex) throws IndexOutOfBoundsException, EmptyFieldException { //wyjatki
        if(Objects.equals(this.get(columnIndex), "")){
            throw new EmptyFieldException(columnIndex);
        }
        if (columnIndex >= columnLabels.size() || columnIndex < 0) {
            throw new IndexOutOfBoundsException();
        }
        return Double.parseDouble(current[columnIndex]);
    }

    double getDouble(String columnLabel) throws IndexOutOfBoundsException, EmptyFieldException {
        if(Objects.equals(this.get(columnLabel), "")){
            throw new EmptyFieldException(columnLabelsToInt.get(columnLabel));
        }
        if (columnLabelsToInt.get(columnLabel) >= columnLabels.size() || columnLabelsToInt.get(columnLabel) < 0) {
            throw new IndexOutOfBoundsException();
        }
        return Double.parseDouble(this.get(columnLabel));
    }

}