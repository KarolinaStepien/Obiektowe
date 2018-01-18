package kolokwium;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Max {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);

    static void initArray(int size) {
        array = new double[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt(100);
        }
    }

    static class MaxCalc extends Thread {
        private final int start;
        private final int end;
        double max = 0;

        MaxCalc(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            double maxEl = 0;
            for (int i = start; i < end; i++) {
                if(array[i]>maxEl){
                    maxEl = array[i];
                }
            }
            try {
                results.put(maxEl);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static void parallelMax(int cnt) throws InterruptedException {
        MaxCalc threads[] = new MaxCalc[cnt];
        int equal = array.length/cnt;
        for(int i=0; i<cnt; i++){
            threads[i] = new MaxCalc(i*equal, (i+1)*equal-1);
        }
        double t1 = System.nanoTime() / 1e6;
        for(MaxCalc mc : threads){
            mc.run();
        }
        double t2 = System.nanoTime() / 1e6;
        /*for (MaxCalc mc : threads) {
            results.put(mc.max);
        }*/
        double max = 0;
        for (MaxCalc mc : threads) {
            double a = results.take();
            if(a > max){
                max = a;
            }
        }
        double t3 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "size = %d cnt=%d >  t2-t1=%f t3-t1=%f max=%f\n",
                array.length,
                cnt,
                t2 - t1,
                t3 - t1,
                max);
    }

    public static void main(String[] args) {
        initArray(10000000);
        //new MaxCalc(2, 8).run();
        try {
            parallelMax(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
