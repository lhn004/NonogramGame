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
 * Class: HelloFXMLMain
 *
 * Description:
 *
 * *****************************************/
package lab11.ex1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloFXMLMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the FXML file. Obtain the root of the scene graph
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/lab11/hellofxml.fxml"));
        Parent root = loader.load();

        // Set up the stage and show it
        primaryStage.setTitle("Hello FXML!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
