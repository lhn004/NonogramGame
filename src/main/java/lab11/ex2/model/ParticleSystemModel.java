/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 10/04/2023
 * Time: 08:02
 *
 * Project: csci205_labs
 * Package: lab11.ex2.model
 * Class: ParticleSystemModel
 *
 * Description:
 *
 * *****************************************/
package lab11.ex2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class ParticleSystemModel {
    /** Default number of particles that will be generated */
    private static final int DEF_NUM_PARTICLES = 200;

    /** A shared random generator */
    private final Random rng = new Random();

    /** A list of emitters in our particle system */
    private List<Emitter> listOfEmitters;

    /** Construct a new particle system model */
    public ParticleSystemModel() {
        this.listOfEmitters = new ArrayList<>();
    }

    /** Return a Stream view of the list of Emitters */
    public Stream<Emitter> emitterStream(){
        return this.listOfEmitters.stream();
    }


    /**
     * Add a new emitter that will produce a specified number of particles
     * @param x the x-coordinate of the emitter
     * @param y the y-coordinate of the emitter
     * @param numParticles number of particles that will be emitted
     */
    public void addNewEmitter(int x, int y, int numParticles){
        Emitter emitter = new Emitter(numParticles,x,y);
        this.listOfEmitters.add(emitter);

    }


    /**
     * Generate an emitter at a random location
     * @param maxWidth - maximum x-coordinate to allow
     * @param maxHeight - maximum y-coordinate to allow
     * @param numParticles - number of particles to emit
     */
    public void generateRandomEmitter(int maxWidth, int maxHeight, int numParticles){
        int x = rng.nextInt(maxWidth);
        int y = rng.nextInt(maxHeight);
        this.addNewEmitter(x,y,numParticles);
    }



    /**
     * Generate an emitter with a default number of particles
     * @param maxWidth - maximum x-coordinate to allow
     * @param maxHeight - maximum y-coordinate to allow
     */
    public void generateRandomEmitter(int maxWidth, int maxHeight){
        this.generateRandomEmitter(maxWidth,maxHeight,DEF_NUM_PARTICLES);
    }


    /**
     * Start the timeline animation for each emitter in our system
     */
    public void play(){
        emitterStream().forEach(emitter -> emitter.play());
    }


    /**
     * Stop the animation for each emitter
     */
    public void stopAndReset(){
        emitterStream().forEach(emitter -> emitter.stopAndReset());
    }
}
