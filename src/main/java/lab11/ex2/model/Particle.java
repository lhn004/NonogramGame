/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 09/04/2023
 * Time: 22:06
 *
 * Project: csci205_labs
 * Package: lab11.ex2.model
 * Class: Particle
 *
 * Description:
 *
 * *****************************************/
package lab11.ex2.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * A model for a single particle that will travel in some trajectory
 * and speed over a short duration. We will use the JavaFX {@link Timeline} class to manage modeling
 * and position changes over time.
 */
public class Particle {

    /** The current x coordinate of the particle */
    private final DoubleProperty x;

    /** The current y coordinate of the particle */
    private final DoubleProperty y;

    /** The color of the particle */
    private final Color color;

    /** The Timeline object that JavaFX provides to help auto-update
     * the particle position */
    private Timeline timeline;


    /**
     * Initiazlize a new Paricle object
     * @param startX the starting x-coordinate
     * @param startY the starting y-coordinate
     * @param duration how long the particle should stay alive in seconds
     * @param xDeltaPerSec change in x per second
     * @param yDeltaPerSec change in y per second
     * @param color color of the particle
     */
    public Particle(double startX, double startY, double duration, double xDeltaPerSec, double yDeltaPerSec, Color color) {
        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();
        this.color = color;
        this.initTimeline(startX,startY,duration,xDeltaPerSec,yDeltaPerSec);
    }


    /**
     * This initiates a new {@link Timeline} to allow a particle to move over time
     * @param startX the starting x-coordinate
     * @param startY the starting y-coordinate
     * @param duration how long the particle should stay alive in seconds
     * @param xDeltaPerSec change in x per second
     * @param yDeltaPerSec change in y per second
     */
    private void initTimeline(double startX, double startY, double duration, double xDeltaPerSec, double yDeltaPerSec){
        this.timeline = new Timeline(
                new KeyFrame(Duration.ZERO, //Starting point for the particle
                        new KeyValue(x, startX),
                        new KeyValue(y, startY)
                ),
                new KeyFrame(Duration.seconds(duration), //End point for the particle
                        new KeyValue(x, startX + (xDeltaPerSec*duration)),
                        new KeyValue(y, startY + (yDeltaPerSec * duration)))
        );

    }


    /** Start the timeline animation */
    public void play(){
        this.timeline.play();
    }

    /** Pause the timeline animation */
    public void pause(){
        this.timeline.pause();
    }


    /** Stop the current animation and reset the timeline back to the beginning */
    public void stopAndReset(){
        this.timeline.stop();
    }


    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public Color getColor() {
        return color;
    }




}
