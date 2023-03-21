/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 20/03/2023
 * Time: 07:58
 *
 * Project: csci205_labs
 * Package: lab10
 * Class: TempConverter
 *
 * Description:
 *
 * *****************************************/
package lab10.ex3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;

/**
 * A simple temperature converter
 */
public class TempConverter extends Application {
    private VBox root;

    /** Top pane containers to hold the controls to get the temp from user*/
    private FlowPane topPane;

    /** TextField to enter the temperature */
    private TextField textFieldTempInput;

    /** Label for result */
    private Label lblResult;

    /** Button to convert */
    private Button btnConvert;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initSceneGraph();
        initStyling();
        initEventHandlers();

        // Add the scene graph to the stage
        primaryStage.setScene(new Scene(root));

        // Automatically resize the window to show the content on the stage
        primaryStage.sizeToScene();

        // Set the title
        primaryStage.setTitle("F to C Converter");

        // Display the stage
        primaryStage.show();

    }

    /**
     * Initialize the scene graph for the app
     */
    public void initSceneGraph(){
        root = new VBox();

        topPane = new FlowPane();
        root.getChildren().add(topPane);
        topPane.getChildren().add(new Label("Temperature (F):"));

        textFieldTempInput = new TextField();
        topPane.getChildren().add(textFieldTempInput);

        lblResult = new Label();
        root.getChildren().add(lblResult);

        btnConvert= new Button("Convert!");
        root.getChildren().add(btnConvert);


    }

    /**
     * Initialize event handlers
     */
    public void initEventHandlers(){
        btnConvert.setOnAction(event -> {
            try{
                Double inputF = Double.parseDouble(textFieldTempInput.getText());
                Double inputC = (double) Math.round((inputF - 32) *(5.0/9));
                lblResult.setText(inputC.toString());
            }
            catch (NumberFormatException e ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect Input!");
                alert.setHeaderText("Incorrect input specified!");
                alert.setContentText(String.format("Cannot convert \"%s\"",textFieldTempInput.getText()));
                alert.show();
            }
        });

        // set up text field to event handlers
        textFieldTempInput.setOnAction(btnConvert.getOnAction());

    }

    /**
     * Initialize the styling for the content in the scene graph
     */
    public void initStyling(){
        // Set up root container styles
        root.setSpacing(5);
        root.setPrefWidth(250);   // set the preferred width of the root to 250
        root.setPadding(new Insets(10,5,10,5));
        root.setAlignment(Pos.CENTER);

        // Set up top pane styles
        topPane.setOrientation(Orientation.HORIZONTAL);
        topPane.setAlignment(Pos.CENTER);
        topPane.setHgap(10);

        // Align the temp input to be centered and leave space for 5 characters
        textFieldTempInput.setAlignment(Pos.CENTER);
        textFieldTempInput.setPrefColumnCount(5);

        // Set up styles for label
        lblResult.setPrefWidth(75);
        lblResult.setPrefHeight(25);
        lblResult.setBorder(new Border(new BorderStroke(null,
                BorderStrokeStyle.SOLID,
                new CornerRadii(4),
                BorderWidths.DEFAULT)));
        lblResult.setAlignment(Pos.CENTER);



    }
}
