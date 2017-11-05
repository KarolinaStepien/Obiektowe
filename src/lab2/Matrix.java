package lab2;
//ctrl+alt+l uładnia kod

import java.util.Random;

public class Matrix {
    private double[] data;
    private int rows;
    private int cols;

    //konstruktor
    private Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    //konstruktor
    Matrix(double[][] d) {
        this.rows = d.length;
        int maxRow = 0; //dlugosc najdluzszego rzedu
        for (double row[] : d) {
            if (row.length > maxRow) {
                maxRow = row.length;
            }
        }
        this.cols = maxRow;
        this.data = new double[rows * cols];

        int k = 0; //iterator

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                data[k] = d[i][j]; //wypelniam elementami
                k++;
            }
            if (d[i].length < maxRow) { //reszte uzupelniam zerami
                for (int l = 0; l < (maxRow - d[i].length); l++) {
                    data[k] = 0;
                    k++;
                }
            }
        }
    }

    //jako tablica dwuwymiarowa
    double[][] asArray() {
        double[][] array = new double[rows][cols];
        int k = 0; //i*cols+j
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = data[k];
                k++;
            }
        }
        return array;
    }

    //gettery i settery
    double get(int r, int c) {
        return data[r * cols + c];
    }

    private void set(int r, int c, double value) {
        data[r * cols + c] = value;
    }

    //toString
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < rows; i++) {
            buf.append("[");
            for (int j = 0; j < cols; j++) {
                buf.append(data[i*cols+j]);
                buf.append(", ");
            }
            buf.append(data[i*cols+rows]);
            buf.append("]");
        }
        buf.append("]");
        return buf.toString();
    }

    //reshape
    void reshape(int newRows, int newCols) {
        if (rows * cols != newRows * newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));
        else {
            rows = newRows;
            cols = newCols;
        }
    }

    //shape
    int[] shape() {
        int[] dimensions = new int[2];
        dimensions[0] = rows;
        dimensions[1] = cols;
        return dimensions;
    }

    //add
    Matrix add(Matrix m) {
        if (rows == m.rows && cols == m.cols) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i*cols+j] = this.data[i*cols+j] + m.data[i*cols+j];
                }
            }
        }
        else{
            throw new RuntimeException("A co to za wymiary takie brzydkie?");
        }
        return this;
    }

    //substract
    Matrix sub(Matrix m){
        if (rows == m.rows && cols == m.cols) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i*cols+j] = this.data[i*cols+j] - m.data[i*cols+j];
                }
            }
        }
        else{
            throw new RuntimeException("A co to za wymiary takie brzydkie?");
        }
        return this;
    }

    //multiply
    Matrix mul(Matrix m){
        if (rows == m.rows && cols == m.cols) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i*cols+j] = this.data[i*cols+j] * m.data[i*cols+j];
                }
            }
        }
        else{
            throw new RuntimeException("A co to za wymiary takie brzydkie?");
        }
        return this;
    }

    //divide
    Matrix div(Matrix m){
        if (rows == m.rows && cols == m.cols) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if(m.data[i*cols+j]!=0) {
                        data[i*cols+j] = this.data[i*cols+j] / m.data[i*cols+j];
                    }
                    else{
                        throw new RuntimeException("Halo halo nie dzielimy przez zero");
                    }
                }
            }
        }
        else{
            throw new RuntimeException("A co to za wymiary takie brzydkie?");
        }
        return this;
    }

    //real matrix multiply
    Matrix dot(Matrix m){
        if (rows == m.cols && cols == m.rows){
            Matrix newone = new Matrix(rows, m.cols);
            newone.data = new double [rows*m.cols];
            double[][] newarray = newone.asArray();
            double[][] firstarray = this.asArray();
            double[][] secondarray = m.asArray();
            for( int i = 0; i < newone.rows; i++ ) {
                for( int j = 0; j < newone.cols; j++ ) {
                    for( int k = 0; k < this.cols; k++ ) {
                        newarray[i][j] += firstarray[i][k] * secondarray[k][j];
                    }
                }
            }
            for( int i = 0; i < newone.rows; i++ ) {
                for (int j = 0; j < newone.cols; j++) {
                    newone.data[i*cols+j] = newarray[i][j];
                }
            }
            return newone;
        }
        else{
            System.out.println("Złe wymiary macierzy ");
            return this;
        }
    }

    //norma Frobeniusa
    double frobenius(Matrix m){
        double frob = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                frob += Math.pow(m.data[i*cols+j], 2);
            }
        }
        return Math.pow(frob, 1/2);
    }

    //metody statyczne budujace macierze
    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();
        m.set(0,0, r.nextDouble());
        //... wypełnij wartościami losowymi
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                m.data[i*cols+j] = r.nextInt(50);
            }
        }
        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        //... wypełnij jedynkami na przekątnej
        for(int i=0;i<m.rows;i++){
            for(int j=0;j<m.cols;j++){
                if(i == j){
                    m.data[i*m.cols+j] = 1;
                }
                else{
                    m.data[i*m.cols+j] = 0;
                }
            }
        }
        return m;
    }
}