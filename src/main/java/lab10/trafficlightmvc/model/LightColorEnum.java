/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 23/03/2023
 * Time: 11:38
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc.model
 * Class: LightColorEnum
 *
 * Description:
 *
 * *****************************************/
package lab10.trafficlightmvc.model;

import javafx.scene.paint.Color;

/**
 * An enumeration of the different lights we'll use in our app.
 * Each enumeration will encapsulate a color representing an "on" state
 */
public enum LightColorEnum {
    RED(Color.RED),
    YELLOW(Color.YELLOW),
    GREEN(Color.LAWNGREEN);

    private Color color;

    private LightColorEnum(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

}
