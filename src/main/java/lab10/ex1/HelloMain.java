/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 20/03/2023
 * Time: 12:48
 *
 * Project: csci205_labs
 * Package: lab10.ex1
 * Class: HelloMain
 *
 * Description:
 *
 * *****************************************/

package lab10.ex1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloMain extends Application {

    private Button btn;
    private Label lblTime;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        // Initialize the controls for our scene graph
        initSceneGraph(root);

        // Set up our scene and place the root of the scene graph on it
        Scene scene = new Scene(root, 400, 300);
        

        // Set the scene on the stage
        primaryStage.setScene(scene);

        // Set the title for the main window
        primaryStage.setTitle("Hello, JavaFX!");

        // Display the scene
        primaryStage.show();


    }

    /**
     * Initialize all controls we'll need for our app. Place them in scene graph {@code root}
     * @param root the root node container of the scene graph
     */
    private void initSceneGraph(VBox root) {
        // Create a button and set up the event handler to report the current time
        btn = new Button();
        btn.setText("Report the date and time");

        // A label to show the current time when clicked
        lblTime = new Label();

        //Add the controls to the scene graph
        root.getChildren().add(btn);
        root.getChildren().add(lblTime);

        // Set up event handlers
        btn.setOnAction(event -> {
            LocalDateTime ldt = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ssa MM/dd/yyyy");
//            System.out.println(ldt.format(formatter));
            lblTime.setText(ldt.format(formatter));
        });

    }
}
