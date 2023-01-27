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
 * This is a simple program to help you understand wrapper classes and autoboxing,
 * and understand that object convenience may come at a computational cost.
 * *****************************************/

package lab02;

public class PrimitivePerf {

    /** The quantity of numbers to compute a sum for */
    private static final int MAX_NUMBERS = 10000000;

    /** MAIN PROGRAM */
    public static void main(String[] args) {
        // Our time durations we will use to store our results
        long primitiveDuration = 0L;
        long wrappedDuration = 0L;

        // Evaluate the test with primitive types
        long start_time1 = System.nanoTime();
        long primitiveResult = testPrimitive();
        primitiveDuration =  System.nanoTime() - start_time1;

        // Now, evaluate the test with the wrapper class type
        long start_time2 = System.nanoTime();
        Long wrappedResult = testWrapped();
        wrappedDuration = System.nanoTime() - start_time2;

        // TODO - Print results, timing, and pct difference between times
        System.out.println("Primitive:");
        System.out.print("Answer = " + primitiveResult);
        System.out.println(" Time = " + primitiveDuration + " ns");
        System.out.println();

        System.out.println("Wrapped:");
        System.out.print("Answer = " + wrappedResult);
        System.out.println(" Time = " + wrappedDuration + " ns");

        double pct_diff = ((double)(wrappedDuration-primitiveDuration)/wrappedDuration)*100;
        System.out.println(String.format("Primitive types used %.1f %% of the time of wrapper objects.", pct_diff));
        
    }

    /**
     * Simple function to evaluate the speed of adding numbers that are
     * primitive types
     */
    public static long testPrimitive() {
        // Store the result as a primitive type
        long sum = 0L;
        for (int i = 0; i < MAX_NUMBERS; i++) {
            sum = sum + i;
        }

        return sum;
    }

    /**
     * Simple function to evaluate the speed of adding numbers that are stored
     * as an object.
     */
    public static Long testWrapped() {
        // Store the result as an object Long, not the primitive type!
        Long objSum = 0L;
        for (int i = 0; i < MAX_NUMBERS; i++) {
            objSum = objSum + i;
        }

        return objSum;
    }
}
