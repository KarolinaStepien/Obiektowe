package lab7;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadExample {
    //licznik pobranych plikow
    static AtomicInteger count = new AtomicInteger(0);
    /*Semafor to zmienna całkowita (licznik), na której można wykonać dwie atomowe (=niepodzielne) operacje:
    release() – zwiększa licznik o 1
    acquire(int cnt) - zawiesza wątek w oczekiwaniu, aż licznik semafora będzie większy lub równy cnt,
    następnie zmniejsza licznik o cnt i odblokowuje oczekujący wątek*/
    static Semaphore sem = new Semaphore(0);
    // lista plików do pobrania
    private static String[] toDownload = {
            "http://home.agh.edu.pl/pszwed/wyklad-c/01-jezyk-c-intro.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/02-jezyk-c-podstawy-skladni.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/03-jezyk-c-instrukcje.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/04-jezyk-c-funkcje.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/05-jezyk-c-deklaracje-typy.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/06-jezyk-c-wskazniki.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/07-jezyk-c-operatory.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/08-jezyk-c-lancuchy-znakow.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/09-jezyk-c-struktura-programow.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/10-jezyk-c-dynamiczna-alokacja-pamieci.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/11-jezyk-c-biblioteka-we-wy.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/preprocesor-make-funkcje-biblioteczne.pdf",
    };

    static class Downloader implements Runnable {
        private final String url;

        Downloader(String url) {
            this.url = url;
        }

        public void run() {
            String[] wholeName = url.split("/");
            String justName = wholeName[wholeName.length - 1];
            String fileName = "D:\\III semestr\\obiektowe\\laboratoria\\src\\lab7\\" + justName;

            try (InputStream in = new URL(url).openStream(); FileOutputStream out = new FileOutputStream(fileName)) {
                for (; ; ) {
                    int c = in.read();
                    if (c < 0) break;
                    out.write((char) c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done: " + fileName);
            count.getAndAdd(1);
            sem.release();
        }
    }

    //pobieranie sekwencyjne
    static void sequentialDownload() {
        double t1 = System.nanoTime() / 1e6;
        for (String url : toDownload) {
            new Downloader(url).run();
        }
        double t2 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "t2-t1=%f\n", t2 - t1);
    }

    //pobieranie współbieżne
    static void concurrentDownload() {
        double t1 = System.nanoTime() / 1e6;
        for (String url : toDownload) {
            (new Thread(new Downloader(url))).start(); //jako wątek
        }
        double t2 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "t2-t1=%f\n", t2 - t1);
    }

    static void concurrentDownload2() {
        double t1 = System.nanoTime() / 1e6;
        for (String url : toDownload) {
            (new Thread(new Downloader(url))).start();
        }
        while(count.get()!=toDownload.length){
            Thread.yield();
        }
        double t2 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "t2-t1=%f\n", t2 - t1);
    }

    static void concurrentDownload3() throws InterruptedException {
        double t1 = System.nanoTime() / 1e6;
        for (String url : toDownload) {
            (new Thread(new Downloader(url))).start();
        }
        sem.acquire(toDownload.length);
        double t2 = System.nanoTime() / 1e6;
        System.out.printf(Locale.US, "t2-t1=%f\n", t2 - t1);
    }

    public static void main(String[] args) {
        //sequentialDownload(); //t2-t1=61101.299970
        //concurrentDownload(); //t2-t1=7.545527 zly czas, bo mierzony przed zakonczeniem wszystkich watkow
        //concurrentDownload2(); //t2-t1=16378.156704
       /* try {
            concurrentDownload3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/ //t2-t1=10337.329284
    }
}
