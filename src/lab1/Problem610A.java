package lab1;

import java.util.Scanner;

public class Problem610A {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        int output = 0;
        if(input%2==0) {
            int half = input/2;
            if(half%2 == 0){
                int halfhalf = half/2;
                for(int i=1; i<halfhalf; i++) {
                    if(half-i!=i){
                        output++;
                    }
                }
            }
            else{
                int halfhalf = (half/2+1);
                for(int i=1; i<halfhalf; i++) {
                    if(half-i!=i){
                        output++;
                    }
                }
            }
        }
        System.out.printf("%d", output);
    }
}
