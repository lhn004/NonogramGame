package lab01;
import java.util.Scanner;


public class CircleCalc 
{
    private static final double EPSILON = 1.0E-10;
    private static Scanner scnr;
    public static void main(String[] args) 
    {
        // Set up a common scanner to be used
        scnr = new Scanner(System.in);
        // Get the input values required
        double angleInRadians = getAngleFromUser();
        double radius = getRadiusFromUser();
        
        // Report the results
        reportQuadrant(angleInRadians);
        reportXYCoordinates(angleInRadians, radius);
        System.out.println("Goodbye!");
    }



    public static double getRadiusFromUser() {
        double radius = -1.0;

        while (radius <= 0) {
            System.out.print("Please enter the radius of the circle: (x>0): ");
            if (!scnr.hasNextDouble()) {
                System.out.println("Please enter a number: ");
            }
            else {
                radius = scnr.nextDouble();
                if (radius <= 0) {
                    System.out.println("Invalid raidus. Please try again!");
                }
            }
            scnr.nextLine();

        }
        return radius;
    }



    public static double getAngleFromUser() {
        double angle = -1.0;

        while (angle <= 0) {
            System.out.print("Please enter angle in degree [0,360): ");
            if (!scnr.hasNextDouble()) {
                System.out.println("Please enter a number: ");
            }
            else {
                angle = scnr.nextDouble();
                if (angle <= 0) {
                    System.out.println("Invalid angle. Please try again!");
                }
            }
            scnr.nextLine();

        }
        return Math.toRadians(angle);
    } 
    
    
    static void reportQuadrant(double radian) {
        if ((Math.toDegrees(radian) % 90) < EPSILON) {
            System.out.println("Quadrant: IN BETWEEN QUADRANTS");
        }
        else if (radian > 0 && radian < 1/2) {
            System.out.println("Quadrant: 1"); 
        }
        else if (radian > 1/2 && radian < 1) {
            System.out.println("Quadrant: 2"); 
        
        }
        else if (radian > 1 && radian < 3/2) {
            System.out.println("Quadrant: 3"); 
        }
        else if (radian > 3/2 && radian < 1){
            System.out.println("Quadrant: 4"); 
        }
    }


    static void reportXYCoordinates(double radian, double radius) {
        double y = Math.sin(radian) * radius;
        double x = Math.cos(radian) * radius;
        System.out.println(String.format("Your coordinates are: (%.2f, %.2f)", x,y));

    }




}

