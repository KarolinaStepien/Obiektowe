package lab1;

import java.util.Scanner;

public class Fibo {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if(n>=1&n<=45) {
            int[] tab = new int[n];
            tab[0] = 1;
            tab[1] = 1;
            System.out.printf("%d %d ", tab[0], tab[1]);
            for(int i=2;i<n;i++) {
                tab[i] = tab[i-2]+tab[i-1];
                System.out.printf("%d ", tab[i]);
            }
        }
    }
}
