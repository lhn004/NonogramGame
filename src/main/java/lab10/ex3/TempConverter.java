
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
        primaryStage.setScene(new Scene(this.root));

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
        this.root = new VBox();

        this.topPane = new FlowPane();
        root.getChildren().add(this.topPane);
        this.topPane.getChildren().add(new Label("Temperature (F):"));

        this.textFieldTempInput = new TextField();
        this.topPane.getChildren().add(this.textFieldTempInput);

        this.lblResult = new Label();
        root.getChildren().add(this.lblResult);

        this.btnConvert= new Button("Convert!");
        root.getChildren().add(this.btnConvert);


    }

    /**
     * Initialize event handlers
     */
    public void initEventHandlers(){
        this.btnConvert.setOnAction(event -> {
            try{
                Double inputF = Double.parseDouble(this.textFieldTempInput.getText());
                Double inputC = (double) Math.round((inputF - 32) *(5.0/9));
                this.lblResult.setText(inputC.toString());
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
        this.textFieldTempInput.setOnAction(this.btnConvert.getOnAction());

    }

    /**
     * Initialize the styling for the content in the scene graph
     */
    public void initStyling(){
        // Set up root container styles
        this.root.setSpacing(5);
        this.root.setPrefWidth(250);   // set the preferred width of the root to 250
        this.root.setPadding(new Insets(10,5,10,5));
        this.root.setAlignment(Pos.CENTER);

        // Set up top pane styles
        this.topPane.setOrientation(Orientation.HORIZONTAL);
        this.topPane.setAlignment(Pos.CENTER);
        this.topPane.setHgap(10);

        // Align the temp input to be centered and leave space for 5 characters
        this.textFieldTempInput.setAlignment(Pos.CENTER);
        this.textFieldTempInput.setPrefColumnCount(5);

        // Set up styles for label
        this.lblResult.setPrefWidth(75);
        this.lblResult.setPrefHeight(25);
        this.lblResult.setBorder(new Border(new BorderStroke(null,
                BorderStrokeStyle.SOLID,
                new CornerRadii(4),
                BorderWidths.DEFAULT)));
        this.lblResult.setAlignment(Pos.CENTER);



    }
}
