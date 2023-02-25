/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 24/02/2023
 * Time: 14:15
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: Contractor
 *
 * Description:
 *
 * *****************************************/
package lab08;

import java.time.LocalDate;

public class Contractor implements Payable{
    /** Contractor id */
    private int conID;


    /** First name */
    private String firstName;


    /** Last name */
    private String lastName;


    /** Social Security number */
    private int ssNum;


    /** Hourly wage rate*/
    private double hourlyRate;


    public Contractor(int conID, String firstName, String lastName, int ssNum, double hourlyRate) {
        this.conID = conID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssNum = ssNum;
        this.hourlyRate = hourlyRate;
    }

    /**
     * @return contractor ID
     */
    public int getConID() {
        return conID;
    }

    /**
     * @return contractor first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return contractor last name
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * @return contractor hourly wage rate
     */
    public double getHourlyRate() {
        return hourlyRate;
    }


    /**
     * @return who the payable person should be
     */
    @Override
    public String getPayTo() {
        String s = this.firstName + " " + this.lastName;
        return s;
    }

    /**
     * @param hours worked
     * @return the amount of money that should be paid
     */
    @Override
    public double calculatePay(double hours) {
        return hours * this.hourlyRate;
    }

    /**
     * @return a string that should be placed in the "Memo:" field of the check
     */
    @Override
    public String getPayMemo() {
        String s = "Contractor ID: " + this.conID + ", " + "Pay Date: " + HRUtils.dateToStr(LocalDate.now());
        return s;
    }

    /**
     * Generate a simple string representing the id and name of the contractor. The actual
     * class of the contractor  is printed using the Java reflection API.
     *
     * @return the formatted string
     */
    public String toString() {
        String s = String.format("%4d: %s %s [%s]",
                this.getConID(),
                this.getFirstName(),
                this.getLastName(),
                this.getClass().getSimpleName());
        return s;
    }
}
