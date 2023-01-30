/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2023
 * Instructor: Brian King
 * Section: 9am
 *
 * Name: Linh Nguyen
 * Date: 01/27/2023
 *
 * Lab / Assignment: lab02
 *
 * Description:
 * An exercise to compare different algorithms to solve
 * a simple problem
 * *****************************************/

package lab02;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * A simple class designed to give the user an opportunity to test a recursive
 * and non-recursive version of fibonacci, as well as using BigInteger so you
 * can
 * compute very large values
 *
 * @author BRK 2023-spring
 */
public class Fibonacci {

    /**
     * Compute the nth fibonacci number recursively
     *
     * @param n - the nth number to find
     * @return the nth number in the fibonacci sequence
     */
    public static int recFib(int n) {
        // TODO - Complete this method
        if (n<=2)
        {
            return 1;
        }
        return recFib(n-1) + recFib(n-2);
    }

    /**
     * Compute the nth fibonacci number non-recursively, using a while loop.
     *
     * @param n - the nth number to find
     * @return the nth number in the fibonacci sequence
     */
    public static int nonRecFib(int n) {
        // TODO - Complete this method
        int prev1 = 0;
        int prev2 = 1;
        int temp;

        while (n >=2)
        {
            temp = prev1 + prev2;
            prev1 = prev2;
            prev2 = temp;
            n--;

        };
        return prev2;
    }

    /**
     * Compute the nth fibonacci number non-recursively, using BigInteger.
     * 
     * @param n    - the nth number to find
     * 
     * @param args
     */
    public static BigInteger nonRecFibBigInteger(int n) {
        // TODO - Complete this method
        BigInteger prev1 = BigInteger.ZERO;
        BigInteger prev2 = BigInteger.ONE;
    
        while (n>=2)
        {
            BigInteger temp = prev1.add(prev2);
            prev1 = prev2;
            prev2 = temp;
            n--;

        }
        return prev2;
    }

    /**
     * Main program to test all fibonacci methods
     */
    public static void main(String[] args) {
        final int MAX_RECURSIVE_N = 40;

        System.out.println("Fibonacci number to compute:");
        Scanner scnr = new Scanner(System.in);
        int n = scnr.nextInt();

        // Store the result from the different ways to compute fib(n)
        double duration1 = 0;
        int recResult = 0;
        if (n <= MAX_RECURSIVE_N) 
        {
            long start1 = System.nanoTime();
            recResult = recFib(n);
            duration1 = (System.nanoTime() - start1)/(double)1e6;
        } 

        
        long start2 = System.nanoTime();
        int nonRecResult = nonRecFib(n);
        double duration2 = (System.nanoTime() - start2)/ (double) 1e6;

        long start3 = System.nanoTime();
        BigInteger nonRecFibBigIntegerResult = nonRecFibBigInteger(n);
        double duration3 = (System.nanoTime() - start3)/ (double) 1e6;

        // Print the results
        if (n <= MAX_RECURSIVE_N) 
        {
            System.out.print("Recursive fib: " + recResult);
            System.out.printf( " Time %.4f ms. \n", duration1);
        } 
        else 
        {
            System.out.println("Recursive fib: COULD NOT COMPUTE");
        }
        
        System.out.print("Non-recursive fib: " + nonRecResult);
        System.out.printf( " Time %.4f ms. \n", duration2);

        System.out.print("Non-recursive fib with BigInteger: " + nonRecFibBigIntegerResult);
        System.out.printf( " Time %.4f ms. \n", duration3);
    }

}
