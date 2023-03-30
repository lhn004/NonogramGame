/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 23/03/2023
 * Time: 11:41
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc.model
 * Class: TrafficLightModel
 *
 * Description:
 *
 * *****************************************/
package lab10.trafficlightmvc.model;

import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;

public class TrafficLightModel {
    /** Our array of {@link Light} objects for our traffic light */
    private ArrayList<Light> lights;

    /** Will our lights automatically shut off? */
    private SimpleBooleanProperty isAutoOff;

    public TrafficLightModel(){
        // A traffic light will utilize an ArrayList to store the lights
        this.lights = new ArrayList<>();

        // Let's initialize their colors and add them to the array
        for (LightColorEnum light: LightColorEnum.values())
            this.lights.add(new Light(light.getColor()));

        // Initialize the auto off properly
        this.isAutoOff = new SimpleBooleanProperty(false);
    }


    /**
     * @return true if the lights will automatically shut off
     */
    public boolean isIsAutoOff() {
        return isAutoOff.get();
    }

    /**
     * @return the property of isAutoOffProperty
     */
    public SimpleBooleanProperty isAutoOffProperty() {
        return isAutoOff;
    }

    /**
     * @return the list of Light objects
     */
    public ArrayList<Light> getLights() {
        return lights;
    }

    /**
     * Get a Light at a specified position
     * @param i the position of the Light in the list
     * @return the Light object
     */
    public Light getLight(int i){
        return this.lights.get(i);
    }



}
