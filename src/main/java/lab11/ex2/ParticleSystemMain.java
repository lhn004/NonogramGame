/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 09/04/2023
 * Time: 14:57
 *
 * Project: csci205_labs
 * Package: lab11.ex1
 * Class: ParticleSystemMain
 *
 * Description:
 *
 * *****************************************/
package lab11.ex2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab11.ex2.ParticleSystemController;
import lab11.ex2.model.ParticleSystemModel;

import java.io.IOException;

public class ParticleSystemMain extends Application {
    private ParticleSystemModel theModel;
    private ParticleSystemController theController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.theModel = new ParticleSystemModel();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/lab11/particlesim.fxml"));
        Parent root = loader.load();
        this.theController = loader.getController();
        this.theController.setModel(theModel);

        // Set up our stage
        primaryStage.setTitle("Particle Simulator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
