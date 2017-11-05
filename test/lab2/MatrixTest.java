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
    public void asArray() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void set() throws Exception {
    }

    @Test
    public void testToString() throws Exception {
    }

    @Test
    public void reshape() throws Exception {
    }

    @Test
    public void shape() throws Exception {
    }

    @Test
    public void add() throws Exception {
        assertArrayEquals(
                matrix1.add(matrix2).asArray(),
                matrix2.add(matrix1).asArray()
        );
        assertArrayEquals(
                new double[][] {{14, 16, 18, 20}, {22, 24, 26, 28}, {30, 32, 34, 36}},
                matrix1.add(matrix2).asArray()
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
        Matrix m = new Matrix(matrix1.sub(matrix1).asArray());
        assertEquals(
                m.frobenius(m),
                0
        );
    }

    @Test
    public void mul() throws Exception {
    }

    @Test
    public void div() throws Exception {
    }

    @Test
    public void dot() throws Exception {
    }

    @Test
    public void frobenius() throws Exception {
    }

    @Test
    public void random() throws Exception {
    }

    @Test
    public void eye() throws Exception {
    }
}