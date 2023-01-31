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
import java.util.Scanner;

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
        // Initiate 2 Die objects 
        Die die1 = new Die();
        Die die2 = new Die();

        // Greet the user with a prompt, asking them for the sum of the dice to check for
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the dice simulator!");
        System.out.println("I'm going to roll 2 dice 1000000 times");
        int sum = -1;
        

        // If the value they enter is outside of the range 2 –12, then show an error and ask the user for the sum to check again.
        while (sum < 2 || sum > 12) {
            System.out.print("What dice sum do you want to check for? ");
            if (!in.hasNextInt()) {
                System.out.print("Please enter a number. ");
                
            }
            else {
                sum = in.nextInt();
                if (sum <2 || sum > 12) {
                    System.out.println("ERROR! Invalid value entered! 2-12 only. Try again.");
                }
            }
            in.nextLine();
        }
        
        // Roll the die, start the simulation, and compute the actual time
        int count = 0;
        long start_time = System.nanoTime();
        for (int i = 0; i<1000000; i++)
        {
            die1.roll();
            die2.roll();
            if (die1.getValueOfLastRoll() + die2.getValueOfLastRoll() == sum)
            { count ++; }
        }
        double duration = (double)(System.nanoTime() - start_time)/1000000;

        double pct = ((double)count/1000000)*100;

        // Report result
        System.out.printf("The roll value %d appeared %d times, or %.3f %% of all rolls.\n", sum, count, pct);
        System.out.printf("1000000 rolls took %.3f ms.\n", duration);
        System.out.println("Goodbye"); 
    } 



}