package sample;

public class WindMill {

    public static boolean wind = true;
    public static boolean windMill = true;
    public double widEnergy;
    public double millSpeed = 200;

    public void ChangeMillSpeed(double addition) {
        millSpeed += addition;
    }

    public boolean isWind() {
        return wind;
    }

    public boolean isWindMill() {
        return windMill;
    }

    public void setWindMill(boolean disponible) {
        windMill = disponible;
    }

}
