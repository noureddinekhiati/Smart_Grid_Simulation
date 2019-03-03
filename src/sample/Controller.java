package sample;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

public class Controller implements Initializable {

    Duration duration = Duration.seconds(4);

    @FXML
    private AnchorPane anchor;
    @FXML
    public ImageView point1;
    @FXML
    public ImageView point2;
    @FXML
    public ImageView point3;
    @FXML
    private ImageView point4;
    @FXML
    private ImageView point51;
    @FXML
    private ImageView point52;
    @FXML
    private ImageView point53;
    @FXML
    private ImageView point54;
    @FXML
    private ImageView point55;
    @FXML
    private ImageView point56;
    @FXML
    private ImageView point61;
    @FXML
    private ImageView point62;
    @FXML
    private ImageView point63;
    @FXML
    private ImageView point64;
    @FXML
    private ImageView point65;
    @FXML
    private ImageView point66;

    @FXML
    public ImageView sun;
    @FXML
    private Pane pane1;

    @FXML
    private ImageView wind;

    @FXML
    private ImageView battery;
    @FXML
    private Button btnPause;
    @FXML
    private RadioButton houseRadio1;
    @FXML
    private RadioButton houseRadio2;
    @FXML
    private RadioButton houseRadio3;
    @FXML
    private RadioButton houseRadio4;
    @FXML
    private RadioButton houseRadio5;
    @FXML
    private RadioButton houseRadio6;
    @FXML
    private Button dayButton;
    @FXML
    private Button nightButton;
    @FXML
    private Button onButton;
    @FXML
    private Button offButton;

    @FXML
    private Button play;
    @FXML
    private Button pause;
    @FXML
    private Button stop;

    WindMill windMill = new WindMill();
    RotateTransition transition = new RotateTransition();
    House house1;
    House house2;
    House house3;
    House house4;
    House house5;
    House house6;

    public void dayOnClick(ActionEvent event) {

        dayButton.setDisable(true);
        nightButton.setDisable(false);
        SolarEnergy.inialize();
        SolarEnergy.sun = true;
        sun.setImage(new Image("/image/solar-energy.png"));

    }

    public void nightOnClick(ActionEvent event) {
        nightButton.setDisable(true);
        dayButton.setDisable(false);
        SolarEnergy.sun = false;
        SolarEnergy.inialize();
        sun.setImage(new Image("/image/solar-energy (1).png"));
    }

    public void onClick(ActionEvent event) {
        onButton.setDisable(true);
        offButton.setDisable(false);
        WindMill.windMill = true;
        WindMill.wind = true;
        transition.play();

    }

    public void offClick(ActionEvent event) {
        offButton.setDisable(true);
        onButton.setDisable(false);
        WindMill.windMill = false;
        WindMill.wind = false;
        transition.stop();

    }

    public void playOnClick(ActionEvent event) {

        pane1.setDisable(true);
        if (houseRadio1.isSelected()) {
            house1.start();
        }
        if (houseRadio2.isSelected()) {
            house2.start();
        }
        if (houseRadio3.isSelected()) {
            house3.start();
        }
        if (houseRadio4.isSelected()) {
            house4.start();
        }
        if (houseRadio5.isSelected()) {
            house5.start();
        }
        if (houseRadio6.isSelected()) {
            house6.start();
        }

    }

   /* public void stopOnClick(ActionEvent event) {
        stop.setDisable(true);
        play.setDisable(false);
       
    }*/

    public void pathStop() {
       house1.setPoint5();
       house2.setPoint5();
       house3.setPoint5();
       house4.setPoint5();
       house5.setPoint5();
       house6.setPoint5();
       

    }

    /*public void dayOnClick (ActionEvent event){
        sun.setImage(new Image("/image/solar-energy.png"));
        SolarEnergy.sun = true ;
        House.pathplay1();

        house2.start();
        house41.start();
        house3.start();
        house1.start();

    }
    public void nightOnClick (ActionEvent event ){
        SolarEnergy.sun = false ;
        sun.setImage(new Image("/image/solar-energy (1).png"));
        House.pathStop1();

    }*/
 /*  public void onClick (ActionEvent event) {

        WindMill.windMill=true ;

    }
    public void offClick (ActionEvent event) {
        WindMill.windMill = false ;
        House.pathStop2();
        transition.stop();


    }*/
    public void HouseSelected(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        House.mutex = new Semaphore(1);
        House.mutex1 = new Semaphore(1);
        House.mutex2 = new Semaphore(1);
        House.mutex3 = new Semaphore(1);

        house1 = new House(1, point1, point2, point3, point4, point51, point61);
        house2 = new House(2, point1, point2, point3, point4, point52, point62);
        house3 = new House(3, point1, point2, point3, point4, point53, point63);
        house4 = new House(4, point1, point2, point3, point4, point54, point64);
        house5 = new House(5, point1, point2, point3, point4, point55, point65);
        house6 = new House(6, point1, point2, point3, point4, point56, point66);

        transition.setNode(wind);
        transition.setDuration(Duration.millis(1000));
        transition.setByAngle(360);
        transition.setCycleCount(RotateTransition.INDEFINITE);

        //path_point1_house(4);
        //path1(point1,point1.getX(),point1.getY(),233.5,0,233.5,187,700,187,700,140);
        //path(point2,point2.getX(),point2.getY(),505.0,0.0,505.0,-20.0);
        //path(point1,point1.getX(),point1.getY(),505.0,0.0,505.0,-20.0);
    }

    public boolean radioIsSelected() {
        return houseRadio1.isSelected() || houseRadio2.isSelected() || houseRadio3.isSelected() || houseRadio4.isSelected() || houseRadio5.isSelected() || houseRadio6.isSelected();
    }

}
