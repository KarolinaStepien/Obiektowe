package lab2;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {

    //fail("To nie działa");
    //assertTrue(1>2);
    //assertEquals(1,1);
    //assertNotEquals(2.0*2.0,4.0);
    //assertEquals(1.0,1.1,0.1); // testowanie równości wartości double z dokładnością 0.1
    /*double[][] first={{1,2},{3}};
    double[][] second={{1,2},{4}};
    assertArrayEquals(first[0],second[0],.1);*/
    /*double[][] first={{1,2},{3}};
    double[][] second={{1,2},{4}};
    assertArrayEquals(first[1],second[1],.1);*/

    private Matrix matrix1 = new Matrix(new double[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
    });

    private Matrix matrix2 = new Matrix(new double[][]{
            {13, 14, 15, 16},
            {17, 18, 19, 20},
            {21, 22, 23, 24},
    });

    @Test
    public void testArrayConstructor() throws Exception{
        assertArrayEquals(
                new double[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                },
                new Matrix(new double[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12}
                }).asArray()
        );
    }

    @Test
    public void testSizeConstructor() throws Exception{
        assertArrayEquals(
                new double[][]{
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                },
                new Matrix(3, 4).asArray()
        );
    }

    @Test
    public void asArray() throws Exception {
        assertArrayEquals(
                new double[][]{
                        {13, 14, 15, 16},
                        {17, 18, 19, 20},
                        {21, 22, 23, 24},
                },
                matrix2.asArray()
        );
    }

    @Test
    public void get() throws Exception {
        assertEquals(
                10,
                matrix1.get(2,1),
                1
        );
    }

    @Test
    public void set() throws Exception {
        matrix1.set(0,3,87);
        assertEquals(
                87,
                matrix1.get(0,3),
                1
        );
    }

    @Test
    public void testToString() throws Exception {
        //dedykowany
        //policz przecinki (nawiasy)
        String napis = matrix1.toString();
        char kolejny_znak;
        char przecinek = ',';
        int ile_przecinkow = 0;
        for(int i=0; i<napis.length(); i++){
            kolejny_znak = napis.charAt(i);
            if(kolejny_znak == przecinek){
                ile_przecinkow++;
            }
        }
        assertEquals(
                ile_przecinkow,
                matrix1.rows*matrix1.cols
        );
    }

    @Test
    public void reshape() throws Exception {
        matrix1.reshape(2,6);
        assertEquals(
                2,
                matrix1.asArray().length
        );
    }

    @Test
    public void shape() throws Exception {
        assertArrayEquals(
                new int[]{3, 4},
                matrix1.shape()
        );
        assertArrayEquals(
                new int[]{1, 1},
                new Matrix(1, 1).shape());
    }

    @Test
    public void add() throws Exception {
        assertArrayEquals(
                new double[][]{{14, 16, 18, 20}, {22, 24, 26, 28}, {30, 32, 34, 36}},
                matrix1.add(matrix2).asArray()
        );
        assertArrayEquals(
                (matrix1.add(matrix2)).asArray(),
                (matrix2.add(matrix1)).asArray()
        );
    }

    @Test
    public void sub() throws Exception {
        assertArrayEquals(
                new double[][]{{-12, -12, -12, -12}, {-12, -12, -12, -12}, {-12, -12, -12, -12}},
                matrix1.sub(matrix2).asArray()
        );
        assertArrayEquals(
                new double[][]{{12, 12, 12, 12}, {12, 12, 12, 12}, {12, 12, 12, 12}},
                matrix2.sub(matrix1).asArray()
        );
        assertEquals(
                (matrix1.sub(matrix1)).frobenius(),
                0,
                1
        );
    }

    @Test
    public void mul() throws Exception {
        //dedykowane
        //pomnóż przez -1 i dodaj (nie mam funkcji mnozacej przez liczbe)
    }

    @Test
    public void div() throws Exception {
        //dedykowane
        //podziel macierz przez samą siebie, oblicz normę frobeniusa
        //i sprawdź czy jest równa pierwiastkowi z iloczynu wierszy i kolumn
        assertEquals(
                matrix1.div(matrix1).frobenius(),
                Math.pow(matrix1.rows*matrix1.cols, 1/2),
                0.1
        );
    }

    @Test
    public void dot() throws Exception {
        Matrix matrix3 = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        });
        assertArrayEquals(
                new double[][]{
                        {30, 36, 42},
                        {66, 81, 96},
                        {102, 126, 150}},
                matrix3.dot(matrix3).asArray()
        );
    }

    @Test
    public void frobenius() throws Exception {
        //dedykowane
        //podobny test w sub
    }

    @Test
    public void random() throws Exception {
        //sprawdzanie random?
    }

    @Test
    public void eye() throws Exception {
        assertArrayEquals(
                new double[][]{
                        {1, 0, 0},
                        {0, 1, 0},
                        {0, 0, 1}
                },
                Matrix.eye(3).asArray()
        );
        assertEquals(
                Math.pow(3,1/2),
                Matrix.eye(3).frobenius(),
                0.1
        );
    }
}