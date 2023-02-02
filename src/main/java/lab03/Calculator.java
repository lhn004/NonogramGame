/* *****************************************
*CSCI205 -Software Engineering and Design
* Spring2023
* Instructor: Brian King
* Section: 9am
*
* Name: Linh Nguyen
* Date: 01/31/2023
*
*Lab / Assignment: lab03
*
*Description: Calculator Class
* * *****************************************/

package lab03;
import java.util.Scanner;

public class Calculator {
    //** Common Scanner object used throughout my program */
    public static Scanner scnr;

    //** The first operand in the expression */
    private static double num1;

    //** The operator in the expression */
    private static String strOp;
    
    //** The second operand in the expression */
    private static double num2;



    /**
     * Main calculator program
     * 
     * @param args - command line args, not used
     */
    public static void main(String[] args) {
        // Set up our Scanner
        Scanner scnr = new Scanner(System.in);

        // Greet the user with instructions
        displayGreeting();

        // Check if user's input is valid
        checkInput(scnr);


        //Helper variable to control when the user wants to exit the program
        boolean isDone = false;
        
        do {
            // Perform the requested operation and print the result
            OperatorPerform(strOp);

            // Check to see if the user wants to try again
            System.out.println("Try again? [Y | N] ");
            if (scnr.next().strip().equalsIgnoreCase("n"))
                isDone = true;

            else {
                scnr.nextLine(); // Move scanner to next line
                checkInput(scnr);
            }
        
        } while (!isDone);
    }



    /**
     * Check if user's input is valid
     * @param scnr - a scanner object
     */
    private static void checkInput(Scanner scnr) {
        boolean isGoodExpression = false;

        while (!isGoodExpression) {
            // Ask the user for good input
            System.out.println("Enter a simple arithmetic expression with spacing: ");

            // Read the entire string and use another Scanner to parse it
            String inputFromUser = scnr.nextLine();
            Scanner strScnr = new Scanner(inputFromUser);

            // If we don't have a number, output an error and jump back to the top
            if (!strScnr.hasNextDouble()) {
                System.out.println("ERROR: Cannot parse operand 1 - Try again!");
                continue;
            }
            num1 = strScnr.nextDouble();

            if (!strScnr.hasNext()) {
                System.out.println("ERROR: Bad input - Try again!");
                continue;                
            }
            strOp = strScnr.next();

            if (!strScnr.hasNextDouble()) {
                System.out.println("ERROR: Cannot parse operand 2 - Try again!");
                continue;
            }
            num2 = strScnr.nextDouble();

            // If we made it here, we're good
            isGoodExpression = true;
        }
    }




    /**
     * Perform the requested operator and print the result
     * @param strOp - an operator
     */
    private static void OperatorPerform(String strOp) {
        switch(strOp) {
            case "+":
                System.out.println("The sum is " + (num1+num2));
                break;
            case "-":
                System.out.println("The difference is " + (num1-num2));
                break;
            case "*":
                System.out.println("The product is " + (num1*num2));
                break;              
            case "/":
                System.out.println("The quotient is " + (num1/num2));
                break;  
            case "^":
                System.out.println("The power is " + (Math.pow(num1, num2)));
                break;
            case "%":
                System.out.println("The remainder is " + (num1%num2));
                break;
            default:
                System.out.println("ERROR: " + strOp + " is not a valid operator.");
        }
    }




    /**
     * Read a simple mathematical expression from user. The method does not return until the user has entered 
     * a valid expression in the form of
     * num1 operator num2
     * 
    */
    private static void readExpressionFromUser(Scanner scnr) {
        num1 = scnr.nextDouble();
        strOp = scnr.next();
        num2 = scnr.nextDouble();
    }



    /** 
     * Display a greeting to user
     */
    private static void displayGreeting() {
        System.out.println("Welcome to the Calculator.");
        System.out.println("Enter expressions with two numeric operands");
        System.out.println("and a single operator from +, -, *, , % or ^");
    }

}