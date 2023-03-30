/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 23/03/2023
 * Time: 11:23
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc.model
 * Class: Light
 *
 * Description:
 *
 * *****************************************/
package lab10.trafficlightmvc.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

/*** A simple abstraction for a basic light that can turn on and off.
 * Our light will also have a color which the class will manage * to simulate darkening
 */

public class Light {

    /** Is the light on? */
    private SimpleBooleanProperty isOn;

    /** The current color of the light */
    private SimpleObjectProperty<Color> currentColor;
    /** The light's on color */
    private Color onColor;
    /** The light's off color */
    private Color offColor;

    /**
     * Construct a new Light instance that is currently set to
     * the light's off color
     * @param color represents the "on" color for the light
     */
    public Light(Color color) {
        this.isOn = new SimpleBooleanProperty(true);
        this.onColor = color;
        this.offColor = color.darker();
        this.currentColor = new SimpleObjectProperty<>();
        this.currentColor.set(this.offColor);

    }

    /**
     * @return the current color of the Light
     */
    public Color getCurrentColor() {
        return currentColor.get();
    }

    public SimpleObjectProperty<Color> currentColorProperty() {
        return currentColor;
    }

    /**
     * @return true if the Light is on
     */
    public boolean isIsOn() {
        return isOn.get();
    }

    /**
     * @return the property of the Light is on
     */
    public SimpleBooleanProperty isOnProperty() {
        return isOn;
    }

    /**
     * Toggle the state of the Light to be either on or off
     */
    public void toggle(){
        this.isOn.set(!this.isOn.get());
        if (this.isIsOn())
            this.currentColor.set(this.onColor);
        else
            this.currentColor.set(this.offColor);
    }

    /**
     * Turn our light on for a specified time. We do this in a separate
     * thread as to not cause delays in the GUI
     * @param ms amount of time to turn on in milliseconds
     */
    public void turnOnForMe(long ms){
        Runnable r = () -> {
            try{
                // if we're not on, then turn on
                if(!this.isIsOn())
                    toggle();
                Thread.sleep(ms);
            }
            catch (InterruptedException e){
            }
            finally {
                // If we're on, then turn off
                if (this.isIsOn())
                    toggle();
            }
        };

        //Encapsulate our Runnable in a thread and start it!
        Thread t = new Thread(r);
        t.start();
    }


}
