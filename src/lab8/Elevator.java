package lab8;

public class Elevator {
    // tworzymy 3 wątki
    static ElevatorCar car = new ElevatorCar();
    static ExternalPanelsAgent externalPanelAgent = new ExternalPanelsAgent(car);
    static InternalPanelAgent internalPanelAgent = new InternalPanelAgent(car);

    // symulacja przywołania windy z panelu zewnętrznego
    static void makeExternalCall(int atFloor, boolean directionUp) {
        try {
            externalPanelAgent.input.put(new ExternalPanelsAgent.ExternalCall(atFloor, directionUp));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // symulacja wyboru pietra w panelu wewnętrznym
    static void makeInternalCall(int toFloor) {
        try {
            internalPanelAgent.input.put(new InternalPanelAgent.InternalCall(toFloor));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // uruchomienie wątków
    static void init() {
        car.start();
        externalPanelAgent.start();
        internalPanelAgent.start();
    }

    // miesjce na kod testowy
    public static void main(String[] args) throws InterruptedException {
        init();

        //SCENARIUSZ I
        /*makeExternalCall(2, true);
        Thread.currentThread().sleep(100);
        makeInternalCall(3);
        Thread.currentThread().sleep(100);
        makeExternalCall(5,true);*/

        //SCENARIUSZ II
        /*makeExternalCall(2, true);
        Thread.currentThread().sleep(100);
        makeInternalCall(7);
        Thread.currentThread().sleep(100);
        makeExternalCall(5,true);
        makeExternalCall(6,false);*/

        //SCENARIUSZ III
        /*makeInternalCall(3);
        makeInternalCall(8);
        makeInternalCall(4);*/

        //SCENARIUSZ IV
        /*makeExternalCall(5,false);
        Thread.currentThread().sleep(5000);
        makeInternalCall(1);
        makeInternalCall(2);
        Thread.currentThread().sleep(1000);
        makeExternalCall(3,false);*/

        //SCENARIUSZ V
        /*makeInternalCall(4);
        Thread.currentThread().sleep(1000);
        makeExternalCall(8,false);
        Thread.currentThread().sleep(5000);
        makeInternalCall(0);*/
    }
}
