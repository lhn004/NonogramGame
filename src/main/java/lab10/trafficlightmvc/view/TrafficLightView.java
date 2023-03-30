/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 23/03/2023
 * Time: 12:25
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc.view
 * Class: TrafficLightView
 *
 * Description:
 *
 * *****************************************/
package lab10.trafficlightmvc.view;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import lab10.trafficlightmvc.model.Light;
import lab10.trafficlightmvc.model.TrafficLightModel;

import java.util.ArrayList;

public class TrafficLightView {
    /** The model that contains the logic behind this view */
    private TrafficLightModel theModel;

    /** Root node for the scene graph */
    private VBox root;

    /** A Checkbox to allow lights to automatically turn off */
    private CheckBox checkBoxAutoOff;

    /** Circle objects for three Lights objects */
    private ArrayList<Circle> lights;

    public TrafficLightView(TrafficLightModel theModel){
        this.theModel = theModel;

        initSceneGraph();
        initStyling();
    }

    /**
     * Initialize the entire scene graph
     */
    public void initSceneGraph(){
        this.root = new VBox();

        this.checkBoxAutoOff = new CheckBox("Auto off");
        this.checkBoxAutoOff.setSelected(theModel.isIsAutoOff());

        this.lights = new ArrayList<>();
        for (Light modelLight: theModel.getLights()){
            // Set the initial size of the light to be 50
            Circle light = new Circle(50);

            // Set a style class so we can set additional styles in CSS later
            light.getStyleClass().add("light");

            // Set the fill color based on the model
            light.setFill(modelLight.getCurrentColor());

            // Add the light to our array
            lights.add(light);
        }

        // Add checkbox and lights array to the root
        this.root.getChildren().add(checkBoxAutoOff);
        this.root.getChildren().addAll(lights);
    }

    /**
     * Apply appropriate styles to all of the content in the scene graph
     * for this view
     */
    public void initStyling(){

    }

    /**
     * Return the root node of the scene graph for this view
     * @return
     */
    public VBox getRoot() {
        return root;
    }

    /**
     * Return the {@link CheckBox} that allows lights to automatically turn off
     * @return
     */
    public CheckBox getCheckBoxAutoOff() {
        return checkBoxAutoOff;
    }

    /**
     * Return the {@link Circle} for three Lights objects
     * @return
     */
    public ArrayList<Circle> getLights() {
        return lights;
    }


    /**
     * Retrieve individual Light Circle object
     * @param i - the position of the light
     * @return {@link Circle}
     */
    public Circle getLight(int i){
        return lights.get(i);
    }



}
