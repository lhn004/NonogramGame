/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 20/03/2023
 * Time: 07:58
 *
 * Project: csci205_labs
 * Package: lab10
 * Class: Car
 *
 * Description:
 *
 * *****************************************/
package lab10;

import java.util.Scanner;

/**
 * A class that encapsulates the characteristics of a Car
 */
public class Car {
    /** fuel efficiency measured in miles per gallon (mpg) */
    private double mpg;
    /** number of cylinders in the engine */
    private int cylinders;
    /** engine displacement (in cubic inches) */
    private double displacement;
    /** engine horsepower */
    private double horsepower;
    /** vehicle weight (in pounds) */
    private double weight;
    /** time to accelerate from O to 60 mph (in seconds) */
    private double accel;
    /** model year */
    private int modelYear;
    /** origin of car */
    private int origin;
    /** car name */
    private String carName;

    public Car(String csvRecord) {
        // Replace comma & missing values
        csvRecord = csvRecord.replaceAll(",", " ");
        csvRecord = csvRecord.replace("?", "0");

        Scanner scnr = new Scanner(csvRecord);
        this.mpg = Double.parseDouble(scnr.next());
        this.cylinders = Integer.parseInt(scnr.next());
        this.displacement = Double.parseDouble(scnr.next());
        this.horsepower = Double.parseDouble(scnr.next());
        this.weight = Double.parseDouble(scnr.next());
        this.accel = Double.parseDouble(scnr.next());
        this.modelYear = Integer.parseInt(scnr.next());
        this.origin = Integer.parseInt(scnr.next());
        this.carName = scnr.nextLine();

    }

    /**
     * @return fuel efficiency measured in miles per gallon (mpg)
     */
    public double getMpg() {
        return mpg;
    }


    /**
     * @return number of cylinders in the engine
     */
    public int getCylinders() {
        return cylinders;
    }


    /**
     * @return engine displacement (in cubic inches)
     */
    public double getDisplacement() {
        return displacement;
    }

    /**
     * @return engine horsepower
     */
    public double getHorsepower() {
        return horsepower;
    }

    /**
     * @return vehicle weight (in pounds)
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @return time to accelerate from O to 60 mph (in seconds)
     */
    public double getAccel() {
        return accel;
    }

    /**
     * @return model year
     */
    public int getModelYear() {
        return modelYear;
    }

    /**
     * @return origin of car
     */
    public int getOrigin() {
        return origin;
    }

    /**
     * @return car name
     */
    public String getCarName() {
        return carName;
    }




}
