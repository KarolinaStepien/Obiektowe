package lab2;

public class Main {
    public static void main(String[] args) { //ctrl+j psvm
        double[][] nana = {{1,2,3}, {4,5}, {6,7,8,9}};
        Matrix lala = new Matrix(nana);
        for(double el : lala.data){
            System.out.println(el);
        }
        // Matrix r = Matrix.random(2,3);
    }
}
