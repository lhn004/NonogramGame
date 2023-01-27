/* *****************************************
*CSCI205 -Software Engineering and Design
* Spring2023
* Instructor: Brian King
* Section: 9am
*
* Name: Linh Nguyen
* Date: 01/27/2023
*
*Lab / Assignment: Lab02
*
*Description:
* * *****************************************/

package lab02;
import java.util.Random;

public class Die {
    /** The minimum face value for our die */
    public static final int MIN_FACE_VALUE = 1;

    /** The minimum face value for our die */
    public static final int MAX_FACE_VALUE = 6;

    /** Store the face value of the last roll */
    private int valueOfLastRoll;

    /** A reference to a random number generator simulate rolling a die */
    private static Random rng = new Random(1234);

    /**
     * Contruct a new die
     */
    public Die() {
        this.valueOfLastRoll = 0;
    }

    /**
     * Return the last roll of the die
     */
    public int getValueOfLastRoll() {return this.valueOfLastRoll; }

    /**
     * Roll the die
     * 
     * @return the face value rolled
     */
    public int roll() {
        int spread = MAX_FACE_VALUE - MIN_FACE_VALUE + 1;
        this.valueOfLastRoll = Die.rng.nextInt(spread) + MIN_FACE_VALUE;
        return this.valueOfLastRoll;
    }

    /**
     * Return the last roll as a String
     */
    @Override
    public String toString() {
        return ""+ this.valueOfLastRoll;
    }


    /**
     * A simple main program to test out a Die object
     * @param args - program arguments (not used)
     */
    public static void main(String[] args){
        Die die = new Die();

        // Roll the die and print it to the display
        die.roll();
        System.out.println("I rolled: " + die);
        System.out.println("Goodbye"); 
    } 



}