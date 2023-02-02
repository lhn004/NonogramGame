/* Copy banner here! */

package lab03;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


// import javax.sound.midi.Soundbank;


 /**
  * A simple enumerated type to help distinguish between skew types that the user 
  * might specify.
  */
enum SkewType {
    SKEW_LEFT,
    SKEW_RIGHT,
    SKEW_NONE
}

/** 
 * A class that encapsulate some methods to aid in generating a simulated dataset with
 * a specified skew, then reporting the results to see if the RNG really did properly
 * skew the data.
 */
public class Skewness {
    /** mean of data to be generate */
    public static final double DEF_MEAN = 10.0;

    /** stdev of data to be generated */
    public static final double DEF_STDEV = 2.0;

    /** Scanner to use for input */
    private static Scanner scnr;

    /**
     * This is an example main program you may use to complete this assignment.
     * 
     * @param args
     */
    public static void main(String[] args) {

        scnr = new Scanner(System.in);

        System.out.println("Welcome to the skewed data calculator!");
        int numsToGenerate = askUserForQuantityData();
        SkewType skewType = askUserForSkewType();

        // Initialize an array of random integers
        double[] numArray = generateSkewedData(numsToGenerate, skewType);

        // Sort the array and compute the mean and median
        Arrays.sort(numArray);
        double mean = computeMean(numArray);
        double median = computeMedian(numArray);

        // Report the results
        reportResults(mean, median);
    }

    /**
     * Create and initialize an array of <code>size</code> with
     * random integers between 0 and <code>MAX_INT</code> inclusive.
     *
     * @param sizeOfArray the size of the array to fill
     * @param skewType the specified {@link SkewType} 
     * @return an int array of size random numbers between 0 and MAX_INT
     */
    public static double[] generateSkewedData(int sizeOfArray, SkewType skewType) {
        double[] result = new double[sizeOfArray];

        Random rng = new Random(1000);

        // Fill in the array of random integers
        for (int i = 0; i < sizeOfArray; i++) {
            result[i] = rng.nextGaussian() * DEF_STDEV + DEF_MEAN;
        }

        // Let's create some intentional skew in our data if requested
        for(int i = 0; i < sizeOfArray/20; i++) {
            switch (skewType) {
                case SKEW_LEFT:
                    result[i] = (rng.nextGaussian() * DEF_STDEV + DEF_MEAN) * 0.25;
                    break;
                case SKEW_RIGHT:
                    result[i] = (rng.nextGaussian() * DEF_STDEV + DEF_MEAN) * 4;
                    break;
            }
        }

        return result;
    }

    /**
     * Ask user for the size of the array
     * @param scnr - a Scanner object
     * @return an integer which is the number of data generated
     */
    private static int askUserForQuantityData() {
        System.out.println("Please enter the quantity of numbers to generate: ");
        int quantity = scnr.nextInt();
        return quantity;

    }

    /**
     * Ask user for the skewness of the data
     * @param scnr - a Scanner object
     * @return a SkewType, which is the type of skewness
     */
    private static SkewType askUserForSkewType() {
        int skew_num = -1;

        // Check for valid input
        while (skew_num < 0 || skew_num > 2)
        {
            System.out.println("Please choose one of the following:");
            System.out.println("0 - NO skew");
            System.out.println("1 - LEFT skew");
            System.out.println("2 - RIGHT skew");
            if (!scnr.hasNextInt())
            { System.out.println("Please enter a number.");}
            else {
                skew_num = scnr.nextInt();
                if (skew_num < 0 || skew_num > 2) {
                    System.out.println("ERROR. Invalid input.");
                }
            }
            scnr.nextLine();
        }

        // Return the skewness of the data
        switch(skew_num) {
            case 0:
                return SkewType.SKEW_NONE;
            case 1:
                return SkewType.SKEW_LEFT;
            default:
                return SkewType.SKEW_RIGHT;
        } 
    }


    /**
     * Compute the mean of the array
     * @param array - the sorted array
     * @return the mean of the array
     */

    private static double computeMean(double[] array) {
        double sum = 0;
        for (double num:array) {
            sum += num;
        }
        return sum/array.length;
    }



    /**
     * Compute the median of the array
     * @param array - the sorted array 
     * @return the median of the array
     */
    private static double computeMedian(double[] array) {
        int length = array.length;
        double median = 0;
        if (length % 2 != 0) {
            median = array[length/2];
        }
        else {
            median = (array[length/2] + array[(length/2)-1])/2.0;
        }
        return median;
        }

    

    /**
     * Report results: determine whether data is skewed left, skewed right, or has no skew.
     * @param mean - mean of the data
     * @param median - median of the data
     */
    private static void reportResults(double mean, double median) {
        System.out.println("Mean: " + mean);
        System.out.println("Median: " + median);

        // Calculate the difference between the mean and median
        double diff = Math.abs(mean-median);

        // Check if mean < median and difference > threshold, then the data is right skewed
        if (mean < median) {
            if (diff > 0.01*mean) {
                System.out.println("Observed Skew: LEFT");
            }
            else {
                System.out.println("Observed Skew: NONE");
            }
        }

        // Check if mean > median and difference > threshold, then the data is left skewed
        else if (mean > median) {
            if (diff > 0.01*mean) {
                System.out.println("Observed Skew: RIGHT");
            }
            else {
                System.out.println("Observed Skew: NONE");
            }            
        }
        else {
            System.out.println("Observed Skew: NONE");
        }
        }
}


