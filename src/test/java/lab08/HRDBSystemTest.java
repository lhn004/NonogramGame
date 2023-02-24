/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2023
 *
 * Name: Linh Nguyen
 *
 * Project: csci205
 * Package: lab08
 * File: HRBDSystemTest
 * Description:
 * A very basic test for HR system
 * ****************************************
 */
package lab08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HRDBSystemTest {

    private HRDBSystem hr;

    @BeforeEach
    void setUp() {
        // Instantiate a new HR object for every test.
        hr = new HRDBSystem();
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Make sure that adding an {@ink Employee} to the {@link HRDBSystem} indeed
     * does add the employee to the list
     */
    @Test
    void addNewEmployee() {
        // Instantiate couple employees to add
        Employee emp1 = new Employee(1, "Brian","King", 123456789, HRUtils.strToDate("2010-08-20"),
                70000);
        Employee emp2 = new Employee(2, "Grace", "Hopper", 402043002, HRUtils.strToDate("2015-07-02"),
                65000);

        //First, the HR object should have no employee
        assertEquals(0,hr.getNumEmployees());

        //Test if HR object has the correct number of employees after adding a couple of employees.
        hr.addNewEmployee(emp1);
        hr.addNewEmployee(emp2);
        assertEquals(2,hr.getNumEmployees());


    }
}