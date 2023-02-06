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
    public static void main(String[] args) {
        // Greet the user and ask for their name
        Scanner scnr = new Scanner(System.in);
        System.out.println("Greetings. What's your name? ");
        String name = scnr.next();

        // Let's fill up an array with random integers
        Random rand = new Random();
        int[] x = new int[10];
        for (int i = 0;  i < 10; i++) {
            x[i] = rand.nextInt(100);
        }
        System.out.println(name + ", our array is: " + Arrays.toString(x));
    }
}
