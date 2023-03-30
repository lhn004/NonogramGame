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
 * Class: CarPlot
 *
 * Description:
 *
 * *****************************************/
package lab10.ex4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import lab10.Car;


public class CarPlot extends Application {
    /** The name of the file used for the exercise */
    private static String CSV_FILE_NAME = "auto-mpg.csv";

    /** Our list of {@link Car} objects */
    private ArrayList<Car> cars;

    /** Root node of the scene graph */
    private BorderPane root;


    /** Status bar to show some stuff at the bottom */
    private Label lblStatusBar;

    /** CarScatterPlot object to plot the scatterplot */
    private CarScatterPlot carScatterPlot;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        File file = getFileFromUser(primaryStage);
        readAutoMpgFile(file);
        initSceneGraph();
        initStyling();

        root.getStylesheets().add(
                getClass().getResource("/lab10/CarPlot.css").toExternalForm()
        );

        // Add a line that sets the text in your lblStatusBar to show the number of cars read in.
        lblStatusBar.setText("Read in " + cars.size() + " cars");

        // Add the scene graph to the stage
        primaryStage.setScene(new Scene(root, 500,400));

        // Automatically resize the window to show the content on the stage
        primaryStage.sizeToScene();

        // Set the title
        primaryStage.setTitle("Car MPG Plotter");

        //Add data to the scatterplot and create the plot to be shown
        carScatterPlot.plot();

        // Display the stage
        primaryStage.show();
    }

    /**
     * Create scene graph for our app
     */
    private void initSceneGraph(){
        // Set up the root node
        root = new BorderPane();

        // Set up the status bar
        lblStatusBar = new Label();
        lblStatusBar.setId("lblStatusBar");
        root.setBottom(lblStatusBar);

        // Set up our CarScatterPlot
        carScatterPlot = new CarScatterPlot(cars);
        root.setCenter(carScatterPlot.getChart());



    }

    /**
     * Initialize the styling for the content in the scene graph
     */
    private void initStyling() {
        // Set up styles for root
        root.setPadding(new Insets(15));
    }



    private static File getFileFromUser(Stage stage){
        File fileChosen = null;

        // Initialize a FileChooser to where we want to open our file
        FileChooser fileChooser = new FileChooser();

        // The file is in our resources directory of our project folder, and we only want csv file
        fileChooser.setInitialDirectory(new File("src/main/resources"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files","*.csv"));

        // Display the file open dialog window
        fileChosen = fileChooser.showOpenDialog(stage);

        // Return the file chosen
        return fileChosen;
    }

    /**
     * Read auto-mpg.csv file into internal ArrayList of Car objects
     * @param file the file to be read
     */
    private void readAutoMpgFile(File file) {
        cars = new ArrayList<>();
        try (Scanner scnr = new Scanner(file)) {
            scnr.nextLine(); // Skip the first line
            while (scnr.hasNextLine()) {
                Car car = new Car(scnr.nextLine());
                cars.add(car);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException in readAutoMpgFile! This should never happen!");
            System.exit(1);
        }

    }


}
