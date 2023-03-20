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

public class HelloMe extends Application {
    private VBox root;
    private HBox topPane;
    private TextField textFieldInputName;

    private Button btnHello;

    private Text textNameOutput;

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Initialize the scene graph for the app
     */
    public void initSceneGraph(){
        this.root = new VBox();
        this.root.setSpacing(10);
        this.root.setPadding(new Insets(15));
        this.root.setAlignment(Pos.CENTER);

        this.topPane = new HBox();
        this.topPane.setSpacing(10);
        this.topPane.setAlignment(Pos.CENTER);
        root.getChildren().add(this.topPane);
        this.topPane.getChildren().add(new Label("Your name:"));

        this.textFieldInputName = new TextField();
        this.topPane.getChildren().add(this.textFieldInputName);

        // Set up the button
        this.btnHello= new Button("Show my name!");
        root.getChildren().add(this.btnHello);

    }

    /**
     * Initialize an event handler for the button so that it will print, "Hello, name"
     * with the name entered in the text box replaced with name.
     */
    private void initEventHandlers() {
        //Set up the event handler
        this.btnHello.setOnAction(event -> {
            System.out.println("Hello " + this.textFieldInputName.getText() + "!");
            this.textNameOutput.setText(this.textFieldInputName.getText());
        });
    }

    /**
     * Initialize styling effects
     */
    private void initStyling() {
        // Add color and make the input name bold
        root.getChildren().add(new Separator());
        this.textNameOutput = new Text();
        root.getChildren().add(this.textNameOutput);
        this.textNameOutput.setFont(Font.font(null, FontWeight.BOLD,30));
        this.textNameOutput.setFill(Color.FUCHSIA);

        // Add reflection
        Reflection reflection = new Reflection();
        reflection.setFraction(0.9);
        this.textNameOutput.setEffect(reflection);

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
        primaryStage.setScene(new Scene(this.root));

        // Automatically resize the window to show the content on the stage
        primaryStage.sizeToScene();

        // Set the title
        primaryStage.setTitle("Hello, Me!");

        // Display the stage
        primaryStage.show();
    }
}

