/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 10/04/2023
 * Time: 07:43
 *
 * Project: csci205_labs
 * Package: lab11.ex2.model
 * Class: Emitter
 *
 * Description:
 *
 * *****************************************/
package lab11.ex2.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * An object that emits multiple particles in random directions.
 */
public class Emitter {
    /** Maximum velocity allowed in pixels per second */
    public static final double MAX_VELOCITY_PER_SEC = 150.0;

    /** Maximum duration in seconds */
    public static final double MAX_DURATION = 5.0;

    /** Generate random number */
    private static Random rng = new Random();

    /** List of particles being emitted */
    private List<Particle> listOfParticles;

    /** Number of particles that will be emitted */
    private int numParticles;

    /** x-coordinate of the emitter */
    private double x;

    /** y-coordinate of the emitter */
    private double y;

    /**
     * Initialize a new Emitter object
     * @param numParticles Number of particles that will be emitted
     * @param x x-coordinate of the emitter
     * @param y y-coordinate of the emitter
     */
    public Emitter(int numParticles, double x, double y) {
        this.numParticles = numParticles;
        this.x = x;
        this.y = y;
        this.listOfParticles = new ArrayList<>();
        this.initParticles();
    }

    /**
     * Initialize the set of particles that will be emitted from this emitter
     */
    private void initParticles(){
        // Generate a random duration for the particle to be alive, and a random trajectory
        double durationInSec = rng.nextDouble() * MAX_DURATION;
        double xDeltaPerSec = rng.nextDouble() * MAX_VELOCITY_PER_SEC + -(MAX_VELOCITY_PER_SEC/2);
        double yDeltaPerSec = rng.nextDouble() * MAX_VELOCITY_PER_SEC + -(MAX_VELOCITY_PER_SEC/2);
        // Create our new Particle object, and add it to our internal list of particles
        Particle p = new Particle(x,y,durationInSec,xDeltaPerSec,yDeltaPerSec, Color.YELLOW);
        listOfParticles.add(p);
    }

    /**
     *
     * @return a {@link Stream} object representing a stream of particles
     */
    public Stream<Particle> particleStream(){
        return this.listOfParticles.stream();
    }

    /** Go through each particle and start the timeline */
    public void play(){
        particleStream().forEach(Particle::play);
    }

    /** Go through each particle and stop and reset each */
    public void stopAndReset(){
        particleStream().forEach(Particle::stopAndReset);
    }

}
