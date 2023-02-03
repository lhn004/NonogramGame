/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 02/02/2023
 * Time: 15:31
 *
 * Project: csci205_labs
 * Package: lab04
 * Class: HelloIntelliJ
 *
 * Description:
 *
 * *****************************************/
package lab04;

import java.util.Scanner;

/**
 * A simple program to ask the user for their names and do some basic analysis with it
 *
 * @param args - command line arguments (not used)
 */
public class HelloIntelliJ {

    private static String sFirst;
    private static String sLast;
    private static String sFullName;

    /**
     * Our main program
     * @param args - command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Ask for user name
        askUserForName(scnr);

        // Count vowels in the name
        int countVowels = countVowelsInName();

        // Print results
        printResults(countVowels);


    }

    /**
     * Print the results of our vowel analysis to {@link System#out}
     *
     * @param countVowels the number of vowels counted
     */

    private static void printResults(int countVowels) {
        System.out.printf("You have %d letters in your name.\n", sFullName.length()-1);
        System.out.printf("You have %d vowels.\n", countVowels);
        double pct_vowels = ((double) countVowels /(sFullName.length()-1)) * 100;
        System.out.printf("%.1f%% of our name consists of vowels.\n", pct_vowels);
    }


    /**
     * Count the vowels in the name that was entered
     * @return the number of vowels in the first and last name
     */
    private static int countVowelsInName() {
        int countVowels = 0;
        for (char chr: sFullName.toLowerCase().toCharArray()) {
            switch (chr) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    countVowels++;
                    break;
            }
        }
        return countVowels;
    }

    /**
     * Ask users for their first and last name. The results are stored in class variables
     * {@link  #sFirst} and {@link  #sLast}. The complete name with a space between first and
     * last name will be stored in {@link  #sFullName}
     *
     * @param scnr the {@link Scanner} object used to read input from user
     */
    private static void askUserForName(Scanner scnr) {
        // Ask the user for their first name
        System.out.print("Please enter your first name: ");
        sFirst = scnr.nextLine().strip();

        // Ask the user for their last name
        System.out.print("Now, enter your last name: ");
        sLast = scnr.nextLine().strip();

        // Combine them into a full name
        sFullName = sFirst + " " + sLast;
        System.out.println("Your full name is " + sFullName);
    }
}
