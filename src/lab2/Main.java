package lab2;

public class Main {
    public static void main(String[] args) { //ctrl+j psvm
        /*double[][] nana = {{1,2,3}, {4,5}, {6,7,8,9}};
        Matrix lala = new Matrix(nana);
        for(double el : lala.data){
            System.out.println(el);
        }
        // Matrix r = Matrix.random(2,3);*/

        Matrix matrix = new Matrix(new double[][]{
                {1, 2, 3},
                {1},
                {1, 2, 3, 4},
        });

        System.out.println(matrix.toString());

        Matrix matrix1 = new Matrix(new double[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
        });

        Matrix matrix2 = new Matrix(new double[][]{
                {13, 14, 15, 16},
                {17, 18, 19, 20},
                {21, 22, 23, 24},
        });

        int[] m = matrix1.shape();
        System.out.printf("%d, %d", m[0], m[1]);
        System.out.println(matrix1.add(matrix2).toString());
        System.out.println(matrix1.sub(matrix2).toString());
        System.out.println(matrix1.mul(matrix2).toString());
        System.out.println(matrix1.div(matrix2).toString());
        System.out.println(matrix1.dot(matrix2).toString());
        System.out.println(matrix1.frobenius());
    }
}
