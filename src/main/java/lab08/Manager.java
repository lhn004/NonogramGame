/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2023
 *
 * Name: Linh Nguyen
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: Manager
 * Description:
 * A very simple class representing Manager, a specific type of Employee
 * ****************************************
 */

package lab08;

import java.time.LocalDate;
import java.util.LinkedList;


public class Manager extends Employee {

    /** Department Name */
    private String deptName;

    /** List of managed employee */
    private LinkedList<Employee> listOfManagedEmps;

    /**
     * Construct a new Manager of a specified department
     *
     * @param empID     Employee id
     * @param firstName First name of Manager
     * @param lastName  Last name of Manager
     * @param ssNum     Manager Social Security Number
     * @param hireDate  Hire date (as {@link LocalDate} object
     * @param salary    Current employee salary
     * @param deptName  Department name managed
     */
    public Manager(int empID, String firstName, String lastName, int ssNum, LocalDate hireDate, double salary, String deptName) {
        super(empID, firstName, lastName, ssNum, hireDate, salary);
        this.deptName = deptName;
        this.listOfManagedEmps = new LinkedList<>();
    }

    /**
     * @return department name
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @return list of managed employees
     */
     public LinkedList<Employee> getListOfManagedEmps() {
        return listOfManagedEmps;
    }

    /**
     * Set department name
     * @param deptName
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * add a new Employee instance into the listof managed employees.
     * @param newEmployee - new Employee instance
     * @throws ManagerException if employee is already in the list
     */

    public void addEmployee(Employee newEmployee) throws ManagerException {
        for (Employee emp: this.listOfManagedEmps) {
            if (emp.equals(newEmployee)) {
                throw new ManagerException("Already exist");
            }
        }
        this.listOfManagedEmps.add(newEmployee);

    }

    /**
     * emove an Employeefrom the list of managed employees.
     * @param rmvEmployee - Employee to be removed
     * @throws ManagerException if employee was not found in the list
     */

    public void removeEmployee(Employee rmvEmployee) throws ManagerException {
        boolean isFound = false;
        for (Employee emp: this.listOfManagedEmps) {
            if (emp.equals(rmvEmployee)) {
                this.listOfManagedEmps.remove();
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new ManagerException("Not found");
        }
    }

    /**
     * Override the toSimpleString method so that it concatenates the super class toSimpleString method
     * with the string "of" and the actual name of the department.
     * @return the formatted String
     */

    @Override
    public String toSimpleString() {
        return super.toSimpleString() + " of " + this.deptName;
    }
}

