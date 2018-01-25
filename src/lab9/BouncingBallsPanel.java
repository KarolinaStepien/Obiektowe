package lab9;

import javax.swing.*;
import java.awt.*;

public class BouncingBallsPanel extends JPanel {

    static class Ball{
        int x;
        int y;
        double vx;
        double vy;
        Color color;
    }

    //List<Ball> balls = new ArrayList<>();

    class AnimationThread extends Thread{
        public void run(){
            for(;;){
                //przesuń kulki
                //wykonaj odbicia od ściany
                //wywołaj repaint
                //uśpij
            }
        }
    }

    BouncingBallsPanel(){
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
    }

    void onStart(){
        System.out.println("Start or resume animation thread");
    }

    void onStop(){
        System.out.println("Suspend animation thread");
    }

    void onPlus(){
        System.out.println("Add a ball");
    }

    void onMinus(){
        System.out.println("Remove a ball");
    }
}