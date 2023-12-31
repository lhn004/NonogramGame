/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 20/03/2023
 * Time: 14:27
 *
 * Project: csci205_labs
 * Package: lab10.ex2
 * Class: HelloMe
 *
 * Description:
 *
 * *****************************************/
package lab10.ex2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * Hello Me program to print user's name
 */
public class HelloMe extends Application {
    private VBox root;
    /** Top pane containers to hold the controls to get the name from user */
    private HBox topPane;

    /** TextField to enter the name */
    private TextField textFieldInputName;

    /** Button to show name */
    private Button btnHello;

    /** Text name for output */
    private Text textNameOutput;

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Initialize the scene graph for the app
     */
    public void initSceneGraph(){
        root = new VBox();

        topPane = new HBox();
        root.getChildren().add(topPane);
        topPane.getChildren().add(new Label("Your name:"));

        textFieldInputName = new TextField();
        topPane.getChildren().add(textFieldInputName);

        // Set up the button
        btnHello= new Button("Show my name!");
        root.getChildren().add(btnHello);

    }

    /**
     * Initialize an event handler for the button so that it will print, "Hello, name"
     * with the name entered in the text box replaced with name.
     */
    private void initEventHandlers() {
        //Set up the event handler
        btnHello.setOnAction(event -> {
            System.out.println("Hello " + textFieldInputName.getText() + "!");
            textNameOutput.setText(textFieldInputName.getText());
        });
    }

    /**
     * Initialize the styling for the content in the scene graph
     */
    private void initStyling() {
        // Set up styles for root container
        root.setSpacing(10);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);

        // Set up styles for top pane
        topPane.setSpacing(10);
        topPane.setAlignment(Pos.CENTER);

        // Add color and make the input name bold
        root.getChildren().add(new Separator());
        textNameOutput = new Text();
        root.getChildren().add(textNameOutput);
        textNameOutput.setFont(Font.font(null, FontWeight.BOLD,30));
        textNameOutput.setFill(Color.FUCHSIA);

        // Add reflection
        Reflection reflection = new Reflection();
        reflection.setFraction(0.9);
        textNameOutput.setEffect(reflection);

        // Add dropshadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        dropShadow.setHeight(5);
        dropShadow.setRadius(2);
        dropShadow.setColor(Color.DARKGRAY);
        reflection.setInput(dropShadow);

        // Reposition the shadow depending on where the mouse is moving
        root.setOnMouseMoved(event -> {
            dropShadow.setOffsetX(event.getX()-root.getWidth()/3);
            dropShadow.setOffsetY(event.getY()-root.getHeight()/3);
        });
    }


    @Override
    public void start(Stage primaryStage){
        initSceneGraph();
        initStyling();
        initEventHandlers();

        // Add the scene graph to the stage
        primaryStage.setScene(new Scene(root));

        // Automatically resize the window to show the content on the stage
        primaryStage.sizeToScene();

        // Set the title
        primaryStage.setTitle("Hello, Me!");

        // Display the stage
        primaryStage.show();
    }
}

