package sample;

public class SolarEnergy {

    public double solarEnergy = 4000;
    public static boolean[] pvSolar = new boolean[3];
    public static boolean sun = true;

    public synchronized void decrement(double x) {
        x--;
    }

    public synchronized void increment(double x) {
        x++;
    }

    public void changeState(boolean occupied, int position) {
        pvSolar[position] = occupied;
    }

    public int disponible() {
        int i;
        for (i = 0; i < 3; i++) {
            if (pvSolar[i] == false) {
                return i;
            }
        }
        return -1;
    }

    public static void inialize() {
        System.out.println("Inialisation .... ");
        for (int i = 0; i < 3; i++) {
            pvSolar[i] = false;
        }

    }

}
