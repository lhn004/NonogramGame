/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 23/03/2023
 * Time: 16:39
 *
 * Project: csci205_labs
 * Package: lab10.trafficlightmvc
 * Class: TrafficLightController
 *
 * Description:
 *
 * *****************************************/
package lab10.trafficlightmvc;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.shape.Circle;
import lab10.trafficlightmvc.model.Light;
import lab10.trafficlightmvc.model.TrafficLightModel;
import lab10.trafficlightmvc.view.TrafficLightView;

public class TrafficLightController {
    private TrafficLightModel theModel;
    private TrafficLightView theView;

    public TrafficLightController(TrafficLightModel theModel, TrafficLightView theView) {
        this.theModel = theModel;
        this.theView = theView;

        initBindings();
        initEventHandlers();
    }

    // Bind isAutoOff from the Model to the View
    private void initBindings(){
        theModel.isAutoOffProperty().bind(theView.getCheckBoxAutoOff().selectedProperty());

        // Create a binding that based on whatever dimension of our window is the largest
        NumberBinding radiusBinding = Bindings.max(theView.getRoot().heightProperty(),
                                                    theView.getRoot().widthProperty())
                                                .divide(6)
                                                .add(-15);
        //Bind each of our radiusBinding of each circle
        for (Circle c: theView.getLights()){
            c.radiusProperty().bind(radiusBinding);
        }

        // Bind the color of the light in the view to the model light color
        for (int i =0; i < theModel.getLights().size(); i ++){
            Light lightModel = theModel.getLight(i);
            Circle lightView = theView.getLight(i);
            lightView.onMouseClickedProperty().setValue(event -> {
                lightModel.toggle();
                if (theView.getCheckBoxAutoOff().isSelected()){
                    lightModel.turnOnForMe(1000);
            }});
            lightView.fillProperty().bind(lightModel.currentColorProperty());
        }

    }


    private void initEventHandlers(){
//        this.theView.getCheckBoxAutoOff().setOnAction(event -> theModel.getLight());

    }
}

