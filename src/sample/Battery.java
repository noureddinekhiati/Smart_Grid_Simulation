package sample;

import java.util.concurrent.TimeUnit;

public class Battery {

    private static final double capacite = 5000;
    public double courantCapacite = capacite;

    public static double getCapacite() {
        return capacite;
    }

    public  void decrementCapacite() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        courantCapacite -= 1000;
    }

}
