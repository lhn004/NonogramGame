/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 05/02/2023
 * Time: 22:00
 *
 * Project: csci205_labs
 * Package: lab05
 * Class: BadCoder
 *
 * Description:
 *
 * *****************************************/
package lab05;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BadCoder {

    public static final int NUM_INTS = 10;
    public static final int MAX_RANDOM_INT = 100;

    public static void main(String[] args) {
        // Greet the user and ask for their name
        String name = greetUserAndGetName();

        // Let's fill up an array with random integers
        int[] x = generateArrayOfRandomInts(MAX_RANDOM_INT);

        System.out.println(name + ", our array is: " + Arrays.toString(x));
    }

    /**
     * Generate an array of random integers
     * @param bound the maximum value int to randomly generate (exclusive, meaning all integers
     *              from 0 (inclusive) to {@code bound} (exclusive)
     * @return the array of generated integers
     *
     */
    private static int[] generateArrayOfRandomInts(int bound) {
        Random rand = new Random();
        int[] x = new int[NUM_INTS];
        for (int i = 0; i < NUM_INTS; i++) {
            x[i] = rand.nextInt(bound);
        }
        return x;
    }

    /**
     * Greet user and ask for their name
     * @return user's name
     */
    private static String greetUserAndGetName() {
        // Greet the user and ask for their name
        Scanner scnr = new Scanner(System.in);
        System.out.println("Greetings. What's your name? ");
        String name = scnr.next();
        return name;
    }
}
