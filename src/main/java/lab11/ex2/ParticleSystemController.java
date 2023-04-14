/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 10/04/2023
 * Time: 23:02
 *
 * Project: csci205_labs
 * Package: lab11.ex2
 * Class: ParticleSystemController
 *
 * Description:
 *
 * *****************************************/
package lab11.ex2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import lab11.ex2.model.ParticleSystemModel;

public class ParticleSystemController {
    /** A reference to the model this controller must work with */
    private ParticleSystemModel theModel;

    /** The Graphics Context of the canvas */
    private GraphicsContext gc;

    /** AnimationTimer object that will keep the canvas updated */
    private AnimationTimer animationTimer;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGenerate;

    @FXML
    private Button btnStop;

    @FXML
    private Button btnstart;

    @FXML
    private Canvas canvas;

    @FXML
    private CheckBox checkboxContinuous;

    @FXML
    void initialize() {
        assert btnGenerate != null : "fx:id=\"btnGenerate\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert btnStop != null : "fx:id=\"btnStop\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert btnstart != null : "fx:id=\"btnstart\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert checkboxContinuous != null : "fx:id=\"checkboxContinuous\" was not injected: check your FXML file 'particlesim.fxml'.";

        this.gc = canvas.getGraphicsContext2D();

    }


    /**
     * Set the model for this controller
     * @param theModel the {@link ParticleSystemModel} connected to this model
     */
    public void setModel(ParticleSystemModel theModel){
        this.theModel = theModel;
        initEventHandlers();
    }

    /**
     * Initialize event handlers for the app. There is an implied assumption that
     * the model has already been set if this method is called
     */
    private void initEventHandlers(){
        // Generate one new emitter in the model
        this.btnGenerate.setOnAction(event -> {
            this.theModel.generateRandomEmitter((int) this.canvas.getWidth(), (int)this.canvas.getHeight());
        });

        // Set the start button event handlers
        this.btnstart.setOnAction(event -> {
            // If we already have an animation timer, then just stop the timeline and reset it to the beginning
            if(this.animationTimer != null){
                this.theModel.stopAndReset();
            }
            else{
                // We don't have a timer, so create it
                this.animationTimer = createAnimationTimer();
            }

            // Update the timer to start playing
            this.theModel.play();

            // Start the animationTimer
            this.animationTimer.start();
        });

    }

    /**
     * Construct an instance of an {@link AnimationTimer} that will update every particle from every emitter
     * @return an animationTimer
     */
    private AnimationTimer createAnimationTimer() {
        return (new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = ParticleSystemController.this.gc;
                ParticleSystemModel theModel = ParticleSystemController.this.theModel;

                //Clear the background for every update
                gc.setFill(Color.BLACK);
                gc.fillRect(0,0,
                        ParticleSystemController.this.canvas.getWidth(),
                        ParticleSystemController.this.canvas.getHeight());

                // Go through every particle of every emitter and draw an updated oval based on its current position
                theModel.emitterStream()
                        .forEach(e -> e.particleStream()
                                .forEach(p->{
                                    gc.setFill(p.getColor());
                                    gc.fillOval(p.getX(),p.getY(),3,3);
                                }));
            }
        });
    }


}
