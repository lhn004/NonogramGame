/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2023
 *
 * Name: Linh Nguyen
 *
 * Project: csci205
 * Package: lab08
 * File: Employee
 * Description:
 * A very basic abstraction for an employee in a simple HR system
 * ****************************************
 */
package lab08;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * A basic representation for an Employee to be stored in an HR database system
 *
 * @author Brian King
 */

public class Employee implements Payable{

    /**
     * A factory to generate unique employee IDs in a safe way
     */
    private static class IDFactory {

        /**
         * Collection of unique employee IDs generated or assigned
         */
        private static HashSet<Integer> setOfAssignedIds = new HashSet<>();


        /**
         * Internal helper class method to generate a new ID that does not exist in our set of IDs
         *
         * @return a new ID as a {@link Integer}
         */
        private static Integer generateID() {
            int i = 1;
            while (true) {
                if (!setOfAssignedIds.contains(i)) {
                    return i;
                }
                i++;
            }
        }

        /**
         * check if the supplied empID is in setOfAssignedIDs.
         * If it is, OR if the id specified is <= 0, then call generateID() to generate the next available good ID to use.
         * @param empID - empID to check
         * @return good empID
         */
        private static int safeToUse(int empID) {
            if (setOfAssignedIds.contains(empID) || empID < 0) {
                empID = generateID();
            }
            setOfAssignedIds.add(empID);
            return empID;

        }
    }

    /** Employee id */
    private int empID;

    /** First name */
    private String firstName;

    /** Last name */
    private String lastName;

    /** Social Security number */
    private int ssNum;

    /** Date employee was hired */
    private LocalDate hireDate;

    /** Current salary of the employee */
    private double salary;

    /** Number of weeks in a year */
    private static final double WEEKS_YEAR = 52;

    /** Number of hours work in a week */
    private static final double HOURS_WORK = 40;

    /** Overtime pay rate */
    private static final double OVERTIME_RATE =1.5;

    /**
     * Explicit constructor to create new employee
     *
     * @param empID     Employee id
     * @param firstName First name
     * @param lastName  Last name
     * @param ssNum     Social Security Number
     * @param hireDate  Hire date (as {@link LocalDate} object
     * @param salary    Current employee salary
     */
    public Employee(int empID, String firstName, String lastName, int ssNum, LocalDate hireDate, double salary) {
        this.empID = IDFactory.safeToUse(empID);
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssNum = ssNum;
        this.hireDate = hireDate;
        this.salary = salary;
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
     * The pay amount that the employee should be paid, based on hours worked
     * @param hours - number of hours employee worked
     * @return the amount of money that should be paid
     */
    @Override
    public double calculatePay(double hours) {
        double payRate = this.salary/ WEEKS_YEAR/ HOURS_WORK;
        double amountPaid;
        if (hours > HOURS_WORK) {
            amountPaid = HOURS_WORK*payRate  + (hours-HOURS_WORK)*OVERTIME_RATE * payRate;
        }
        else {
            amountPaid = hours * payRate;
        }
        return amountPaid;
    }


    /**
     * @return a string that should be placed in the "Memo:" field of the check
     */
    @Override
    public String getPayMemo() {
        String s = "Employee ID: " + this.empID + ", " + "Pay Date: " + HRUtils.dateToStr(LocalDate.now());
        return s;
    }




    /**
     * @return the employee id
     */
    public int getEmpID() { return empID; }

    /**
     * @return employee first name
     */
    public String getFirstName() { return firstName; }

    /**
     * @return Last name
     */
    public String getLastName() { return lastName; }

    /**
     * @return Social Security number
     */
    public int getSsNum() {
        return ssNum;
    }

    /**
     * @return {@link LocalDate} employee was hired
     */
    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * @return current employee salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Change the name of the employee
     *
     * @param first - New first name
     * @param last - New last name
     */
    public void changeName(String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }

    /**
     * Raise the salary by <code>salaryAdj</code> dollars.
     *
     * @param salaryAdj - amount to add to the current salary
     * @return the new salary
     */
    public double raiseSalary(double salaryAdj) {
        this.salary += salaryAdj;
        return this.salary;
    }

    /**
     * Generate a simple string representing the id and name of the employee. The actual
     * class of the employee (i.e. Employee or any child of it) is printed using the
     * Java reflection API.
     *
     * @return the formatted string
     */
    public String toSimpleString() {
        String s = String.format("%4d: %s %s [%s]",
                                 this.getEmpID(),
                                 this.getFirstName(),
                                 this.getLastName(),
                                 this.getClass().getSimpleName());
        return s;
    }

    /**
     * Generate a comma-separated-values (CSV) version of the employee as
     * a String
     *
     * @return a CSV formatted string
     */
    public String toCSVString() {
        String s = this.empID + "," + this.lastName + "," + this.firstName;
        s += String.format(",%09d", this.ssNum);
        s += "," + HRUtils.dateToStr(this.hireDate);
        s += String.format(",%.2f", this.salary);
        s += "," + this.getClass().getSimpleName();
        return s;
    }

    /**
     * The standard toString to show a string representation of an Employee
     *
     * @return a String representing this Employee
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "empID=" + empID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssNum=" + ssNum +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                '}';
    }

    /**
     * Standard method to compare this object to any other arbitrary {@link Object}
     * 
     * @param obj the other {@link Object} to compare
     * @return True if this Employee has the same SS# as another employee
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Employee))
            return false;

        Employee employee = (Employee) obj;

        // if (getEmpID() != employee.getEmpID())
        //     return false;

        return getSsNum() == employee.getSsNum();
    }


}

