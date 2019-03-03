package sample;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class House extends Thread {

    Duration duration = Duration.seconds(4);

    SolarEnergy solar = new SolarEnergy();
    WindMill wind = new WindMill();
    Battery battery = new Battery();
    static Semaphore mutex;
    static Semaphore mutex1;
    static Semaphore mutex2;
    static Semaphore mutex3;
    int number;

    TranslateTransition transition1 = new TranslateTransition();

    public static PathTransition[] path = new PathTransition[19];
    public PathTransition path1 = new PathTransition();
    Polyline polyline;

    Polyline polyline1;
    private static ImageView point1;
    private static ImageView point2;
    private static ImageView point3;
    private static ImageView point4;
    private ImageView point5;
    private ImageView point6;
    private static boolean pvSolar = true;

    private static ImageView[] point = {
        point1,
        point2,
        point3,
        point4
    };

    public House(int number, ImageView point1, ImageView point2, ImageView point3, ImageView point4, ImageView point5, ImageView point6) {
        this.number = number;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;

    }

    @Override
    public void run() {
        try {
            mutex.acquire();
            int occupe = solar.disponible();
            if (SolarEnergy.sun == true && occupe != -1) {
                System.out.println(occupe + " | " + number);
                solar.changeState(true, occupe);
                path(number, occupe);
                System.out.println(occupe + " | " + number);
                mutex.release();
            } else {
                if (wind.isWind() && WindMill.windMill) {
                    path(number, 3);
                    wind.setWindMill(false);
                    mutex.release();
                } else {
                    mutex.release();
                    while (battery.courantCapacite >= 0) {
                        path(number, number + 10);
                        battery.decrementCapacite();
                    }
                    path(number, number + 3);
                    mutex.release();
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(House.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void addPoints(PathTransition path, double x0, double y0, double x1, double y1, double x2, double y2) {
        polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
            x0, y0,
            x1, y1,
            x2, y2
        });
        path.setPath(polyline);
    }

    void addPoints(PathTransition path, double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        polyline1 = new Polyline();
        polyline1.getPoints().addAll(new Double[]{
            x0, y0,
            x1, y1,
            x2, y2,
            x3, y3,
            x4, y4
        });
        path.setPath(polyline1);
    }

    public static void pathStop1() {

        path[0].stop();
        path[1].stop();
        path[2].stop();

        SolarEnergy.inialize();

        point1.setTranslateX(0);
        point1.setTranslateY(0);
        point2.setTranslateX(0);
        point2.setTranslateY(0);
        point3.setTranslateX(0);
        point3.setTranslateY(0);
    }

    public static void pathplay1() {
        path[0].play();
        path[1].play();
        path[2].play();
    }

    public static void pathStop2() {
        path[3].stop();
        point4.setTranslateX(0);
        point4.setTranslateY(0);
    }

    public static void pathStop() {
        for (int i = 0; i < path.length; i++) {
            path[i].stop();
        }

        SolarEnergy.inialize();

        point1.setTranslateX(0);
        point1.setTranslateY(0);
        point2.setTranslateX(0);
        point2.setTranslateY(0);
        point3.setTranslateX(0);
        point3.setTranslateY(0);
        point4.setTranslateX(0);
        point4.setTranslateY(0);
        WindMill.wind = false;
        WindMill.windMill = false;

    }

    public void setPoint5() {
        this.point5.setTranslateX(0);
        this.point5.setTranslateY(0);
        this.point6.setTranslateX(0);
        this.point6.setTranslateY(0);
    }

    public void house_path1(int house) {

        switch (house) {

            case 1:
                addPoints(path[0], point1.getX(), point1.getY(), 581, 0.0, 581, -20.0);
                //path(point, 0, 0, 581, 0.0, 581, -20.0);
                break;
            case 2:
                addPoints(path[0], point1.getX(), point1.getY(), 835, 0.0, 835, -20.0);
                //path(point, 0, 0, 835, 0.0, 835, -20.0);
                break;
            case 3:
                addPoints(path[0], point1.getX(), point1.getY(), 233.5, 0, 233.5, 187, 583, 187, 583, 140);
                // addPoints(path[], 0, 0, 233.5, 0, 233.5, 187, 583, 187, 583, 140);
                break;
            case 4:
                addPoints(path[0], point1.getX(), point1.getY(), 233.5, 0, 233.5, 187, 835, 187, 835, 140);
                //   addPoints(path[], 0, 0, 233.5, 0, 233.5, 187, 835, 187, 835, 140);
                break;
            case 5:
                addPoints(path[0], point1.getX(), point1.getY(), 233.5, 0, 233.5, 395, 583, 395, 583, 380);
                //path(point, 0, 0, 233.5, 0, 233.5, 395, 583, 395, 583, 380);
                break;
            case 6:
                addPoints(path[0], point1.getX(), point1.getY(), 233.5, 0, 233.5, 395, 836, 395, 836, 380);
                //addPoints(path[], 0, 0, 233.5, 0, 233.5, 395, 836, 395, 836, 380);
                break;

        }

    }

    public void house_path2(int house) {

        switch (house) {
            case 1:
                addPoints(path[1], point2.getX(), point2.getY(), 501, 0.0, 501, -20.0);
                //   addPoints(path[], 0, 0, 501, 0.0, 501, -20.0);
                break;
            case 2:
                addPoints(path[1], point2.getX(), point2.getY(), 755, 0.0, 755, -20.0);
                //  addPoints(path[], 0, 0, 755, 0.0, 755, -20.0);
                break;
            case 3:
                addPoints(path[1], point2.getX(), point2.getY(), 153.5, 0, 153.5, 187, 503, 187, 503, 140);
                //path(point, 0, 0, 153.5, 0, 153.5, 187, 503, 187, 503, 140);
                break;
            case 4:
                addPoints(path[1], point2.getX(), point2.getY(), 153.5, 0, 153.5, 187, 755, 187, 755, 140);
                //addPoints(path[], 0, 0, 153.5, 0, 153.5, 187, 755, 187, 755, 140);
                break;
            case 5:
                addPoints(path[1], point2.getX(), point2.getY(), 153.5, 0, 153.5, 395, 503, 395, 503, 380);
                //addPoints(path[], 0, 0, 153.5, 0, 153.5, 395, 503, 395, 503, 380);
                break;
            case 6:
                addPoints(path[1], point2.getX(), point2.getY(), 153.5, 0, 153.5, 395, 756, 395, 756, 380);
                //  addPoints(path[], 0, 0, 153.5, 0, 153.5, 395, 756, 395, 756, 380);
                break;

        }

    }

    public void house_path3(int house) {

        switch (house) {

            case 1:
                addPoints(path[2], point3.getX(), point3.getY(), 413, 0.0, 413, -20.0);
                break;
            case 2:
                addPoints(path[2], point3.getX(), point3.getY(), 667, 0.0, 667, -20.0);
                break;
            case 3:
                addPoints(path[2], point3.getX(), point3.getY(), 65.5, 0, 65.5, 187, 415, 187, 415, 140);
                break;
            case 4:
                addPoints(path[2], point3.getX(), point3.getY(), 65.5, 0, 65.5, 187, 667, 187, 667, 140);
                break;
            case 5:
                addPoints(path[2], point3.getX(), point3.getY(), 65.5, 0, 65.5, 395, 415, 395, 415, 380);
                break;
            case 6:
                addPoints(path[2], point3.getX(), point3.getY(), 65.5, 0, 65.5, 395, 668, 395, 668, 380);
                break;

        }

    }

    public void house_path4(int house) {
        switch (house) {

            case 1:
                addPoints(path[3], point4.getX(), point4.getY(), 153.5, 0, 153.5, -175, 503, -175, 503, -210);
                break;
            case 2:
                addPoints(path[3], point4.getX(), point4.getY(), 153.5, 0, 153.5, -175, 755, -175, 755, -210);
                break;
            case 3:
                addPoints(path[3], point4.getX(), point4.getY(), 501, 0.0, 501, -20.0);

                break;
            case 4:
                addPoints(path[3], point4.getX(), point4.getY(), 755, 0.0, 755, -20.0);

                break;
            case 5:
                addPoints(path[3], point4.getX(), point4.getY(), 153.5, 0, 153.5, 218, 503, 218, 503, 198);
                break;
            case 6:
                addPoints(path[3], point4.getX(), point4.getY(), 153.5, 0, 153.5, 218, 756, 218, 756, 198);
                break;

        }

    }

    public void house_path5(int house) {
        System.out.println(path[3 + house].getNode());
        switch (house) {

            case 1:
                addPoints(path[3 + house], 0, 0, 162, 0, 162, -390, 415, -390, 415, -440);
                break;
            case 2:
                addPoints(path[3 + house], point6.getX(), point6.getY(), 162, 0, 162, -390, 627, -390, 627, -440);
                break;
            case 3:
                addPoints(path[3 + house], point6.getX(), point6.getY(), 162, 0, 162, -213, 415, -213, 415, -260);
                break;
            case 4:
                addPoints(path[3 + house], point6.getX(), point6.getY(), 162, 0, 162, -213, 627, -213, 627, -260);
                break;
            case 5:
                addPoints(path[3 + house], point6.getX(), point6.getY(), 423, 0.0, 423, -20.0);
                break;
            case 6:
                addPoints(path[3 + house], point6.getX(), point6.getY(), 630, 0.0, 630, -20.0);
                break;

        }
    }

    public void battery_house(int house) {
        System.out.println(path[10 + house].getNode());
        switch (house) {

            case 1:
                addPoints(path[10 + house], point5.getX(), point5.getY(), 0, 30, -90, 30, -90, -10, -90, -10);
                break;
            case 2:
                addPoints(path[10 + house], point5.getX(), point5.getY(), 0, 30, -130, 30, -130, -10, -130, -10);
                break;
            case 3:
                addPoints(path[10 + house], point5.getX(), point5.getY(), 0, 45, -90, 45, -90, -10, -90, -10);
                break;
            case 4:
                addPoints(path[10 + house], point5.getX(), point5.getY(), 0, 35, -132, 35, -132, -10, -132, -10);
                break;
            case 5:
                addPoints(path[10 + house], point5.getX(), point5.getY(), 0, 30, -90, 30, -90, -10, -90, -10);
                break;
            case 6:
                addPoints(path[10 + house], point5.getX(), point5.getY(), 0, 30, -132, 30, -132, -10, -132, -10);
                break;

        }
    }

    public void path(int house, int pt) {

        switch (pt) {
            case 0:
                path[0] = new PathTransition();
                path[0].setNode(point1);
                house_path1(house);
                path[0].setDuration(duration);
                path[0].setCycleCount(PathTransition.INDEFINITE);
                path[0].play();
                break;
            case 1:
                path[1] = new PathTransition();
                path[1].setNode(point2);
                house_path2(house);
                path[1].setDuration(duration);
                path[1].setCycleCount(PathTransition.INDEFINITE);
                path[1].play();
                break;
            case 2:
                path[2] = new PathTransition();
                path[2].setNode(point3);
                house_path3(house);
                path[2].setDuration(duration);
                path[2].setCycleCount(PathTransition.INDEFINITE);
                path[2].play();
                break;
            case 3:
                path[3] = new PathTransition();
                path[3].setNode(point4);
                house_path4(house);
                path[3].setDuration(duration);
                path[3].setCycleCount(PathTransition.INDEFINITE);
                path[3].play();
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                System.out.println("House " + house + " Is using Gaz");
                path[3 + house] = new PathTransition();
                path[3 + house].setNode(point6);
                house_path5(house);
                System.out.println(path[3 + house].getPath());
                System.out.println("House " + house + " Is using Gaz");
                path[3 + house].setDuration(duration);
                path[3 + house].setCycleCount(PathTransition.INDEFINITE);
                path[3 + house].play();
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                path[10 + house] = new PathTransition();
                path[10 + house].setNode(point5);
                battery_house(house);
                path[10 + house].setDuration(Duration.seconds(1));
                path[10 + house].setCycleCount(1);
                path[10 + house].play();

                break;

        }
    }
    /*public void house_path5(int house) {
        switch (house) {

            case 1:
                addPoints(path[4], point11.getX(), point11.getY(), 150, 0, 150, -390, 518, -390, 518, -440);
                break;
            case 2:
                addPoints(path[4], point11.getX(), point11.getY(), 150, 0, 150, -390, 770, -390, 770, -440);
                break;
            case 3:
                addPoints(path[4], point11.getX(), point11.getY(), 150, 0, 150, -197, 518, -197, 518, -260);
                break;
            case 4:
                addPoints(path[4], point11.getX(), point11.getY(), 150, 0, 150, -197, 770, -197, 770, -260);
                break;
            case 5:
                addPoints(path[4], point11.getX(), point11.getY(), 515, 0.0, 515, -20.0);
                break;
            case 6:
                addPoints(path[4], point11.getX(), point11.getY(), 770, 0.0, 770, -20.0);
                break;

        }
    }*/
 /*
    public void path1 (int house , int pt){

        switch (pt) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("House " + house + " Is using Gaz");
                path1[house-1] = new PathTransition();
                path1[house-1].setNode(point6);
                house_path5(house);
                System.out.println("House " + house + " Is using Gaz");
                path1[house-1].setDuration(duration);
                path1[house-1].setCycleCount(PathTransition.INDEFINITE);
                path1[house-1].play();
                break;
        }
    }*/
}
