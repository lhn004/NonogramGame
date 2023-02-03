/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 02/02/2023
 * Time: 19:43
 *
 * Project: csci205_labs
 * Package: lab04
 * Class: BaseConverter
 *
 * Description:
 *
 * *****************************************/
package lab04;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A class to convert from base 3 string to base 10 number
 * @param args - command line arguments (not used)
 */
public class BaseConverter {

    // Base 3 string input from user
    private static String sResult;


    /**
     * Our main program
     * @param args - command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome to the base-3 converter.");
        sResult = "";

        // Ask for user's input and if it is valid
        checkValidInput(scnr);

        boolean isDone = false;
        do {
            // Convert from base 3 to base 10 number
            base3StringToDecimal();

            System.out.print("Try again? [Y|N] ");
            if (scnr.nextLine().strip().equalsIgnoreCase("n")) {
                isDone = true;
                System.out.println("Goodbye!");
            }
            else {
                checkValidInput(scnr);
            }
        } while (!isDone);

    }

    /**
     * A method to convert from base 3 string to base 10 number and
     * print base 10 number
     */

    private static void base3StringToDecimal() {
        long Base10Num = 0 ;
        for (int i = sResult.length()-1; i >=0; i--) {
            switch (sResult.charAt(i)) {
                case '1':
                    Base10Num += Math.pow(3,sResult.length()-1-i);
                    break;
                case '2':
                    Base10Num += (2*Math.pow(3,sResult.length()-1-i));
                    break;
                default:
                    break;
            }
        }
        System.out.println(Base10Num);
    }


    /**
     * A method to ask for user's input and check if it is a valid base 3 number
     * @param scnr - Scanner object to read user input
     */
    private static void checkValidInput(Scanner scnr) {
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Enter a base-3 number: ");
            sResult = scnr.nextLine().strip();
            if (sResult.matches("^[0-2]+$")) {
                isValid = true;
            }
            else {
                System.out.print("Try again. ");
            }
        }
    }

}

