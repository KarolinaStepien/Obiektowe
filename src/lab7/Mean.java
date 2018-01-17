package lab7;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);

    static void initArray(int size) {
        array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random() * size / (i + 1);
        }
    }

    static class MeanCalc extends Thread {
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            //średnia
            double elSum = 0;
            int elNum = end - start;
            for (int i = start; i < end; i++) {
                elSum += array[i];
            }
            mean = elSum / elNum;
            System.out.printf(Locale.US, "%d-%d mean=%f\n", start, end, mean);
        }
    }

     /*Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     Wypisuje czasy operacji
     @param cnt - liczba wątków*/
    static void parallelMean(int cnt) throws InterruptedException {
        // utwórz tablicę wątków
        MeanCalc threads[] = new MeanCalc[cnt];
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        // załóż, że array.length dzieli się przez cnt
        int equal = array.length/cnt;
        for(int i=0; i<cnt; i++){
            threads[i] = new MeanCalc(i*equal, (i+1)*equal-1);
        }
        double t1 = System.nanoTime() / 1e6;
        //uruchom wątki
        for(MeanCalc mc : threads){
            mc.run();
        }
        double t2 = System.nanoTime() / 1e6;
        //czekaj na ich zakończenie używając metody ''join''
        for (MeanCalc mc : threads) {
            //mc.join();
            results.put(mc.mean);
        }
        // oblicz średnią ze średnich
        double mean = 0;
        for (MeanCalc mc : threads) {
            //mean += mc.mean;
            mean += results.take();
        }
        double t3 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2 - t1,
                t3 - t1,
                mean);
    }

    public static void main(String[] args) {
        /*initArray(100000000);
        //new MeanCalc(2, 8).run();
        try {
            parallelMean(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        initArray(128000000);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
            try {
                parallelMean(cnt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
