/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2023
 *
 * Name: Linh Nguyen
 *
 * Project: csci205
 * Package: lab08
 * File: ManagerTest
 * Description:
 * A very basic test for a Manager in a simple HR system
 * ****************************************
 */
package lab08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private Manager mgr;
    private Employee emp1;
    private Employee emp2;

    @BeforeEach
    void setUp() {
        mgr = new Manager(1,"Brad","Putnam", 123456789, HRUtils.strToDate("2022-07-15"),
                10000.0,"ENGINEERING");
        emp1 = new Employee(1, "Brian","King", 123456789, HRUtils.strToDate("2010-08-20"),
                70000);
        emp2 = new Employee(2, "Grace", "Hopper", 402043002, HRUtils.strToDate("2015-07-02"),
                65000);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Make sure that adding an {@ink Employee} to the {@link Manager} indeed
     * does add the employee to the list
     * @throws ManagerException
     */
    @Test
    void addEmployee() throws ManagerException{
        //Set up a list of what we expected
        ArrayList<Employee> expectedList = new ArrayList<>();

        // First, verify that both lists are empty
        assertEquals(expectedList,mgr.getListOfManagedEmps());

        // Add a couple employees to manager
        mgr.addEmployee(emp1);
        mgr.addEmployee(emp2);

        // Add the same employees to our own list copy
        expectedList.add(emp1);
        expectedList.add(emp2);

        // Both lists should still be equivalent
        assertEquals(expectedList,mgr.getListOfManagedEmps());
    }

    /**
     * Make sure that removing an {@ink Employee} to the {@link Manager} indeed
     * does remove the employee to the list
     * @throws ManagerException
     */
    @Test
    void removeEmployee() throws ManagerException{
        //Set up a list of what we expected
        ArrayList<Employee> expectedList = new ArrayList<>();
        expectedList.add(emp1);
        expectedList.add(emp2);

        // Add a couple employees to manager
        mgr.addEmployee(emp1);
        mgr.addEmployee(emp2);

        // First, verify that both lists are equal
        assertEquals(expectedList,mgr.getListOfManagedEmps());

        //Remove an employee from expectedList and actualList
        expectedList.remove(emp1);
        mgr.removeEmployee(emp1);

        //Both lists should remain equal
        assertEquals(expectedList,mgr.getListOfManagedEmps());
    }

    /**
     * A test to make sure exceptions are thrown if the employee to be added is already in the list
     * and NOT thrown if the employee is not in the list
     * @throws ManagerException
     */
    @Test
    void addEmployeeTest() throws ManagerException{
        // Check if an exception is thrown when the employee is already in the list
        mgr.addEmployee(emp1);
        assertThrows(ManagerException.class, () -> mgr.addEmployee(emp1));

        // Check that an exception is not thrown if the employee is not in the list
        assertDoesNotThrow(() -> mgr.addEmployee(emp2));
    }


    /**
     * A test to make sure exceptions are thrown if the employee to be removed is not in the list
     * and NOT thrown if the employee is in the list
     * @throws ManagerException
     */
    @Test
    void removeEmployeeTest() throws ManagerException {
        // Check if an exception is thrown when the employee is not in the list
        mgr.addEmployee(emp1);
        assertThrows(ManagerException.class, () -> mgr.removeEmployee(emp2));

        // Check that an exception is not thrown if the employee is in the list
        assertDoesNotThrow(() -> mgr.removeEmployee(emp1));

    }
}