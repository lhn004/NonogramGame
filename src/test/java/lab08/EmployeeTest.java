/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2023
 *
 * Name: Linh Nguyen
 *
 * Project: csci205
 * Package: lab08
 * File: EmployeeTest
 * Description:
 * A very basic test for an employee in a simple HR system
 * ****************************************
 */
package lab08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private static final double FLOAT_DELTA = 1.0E-10;

    private Employee emp;

    @BeforeEach
    void setUp() {
        // Instantiate a new Employee object for every test.
        emp = new Employee(10, "Bryan", "Kyng", 123456788,
                LocalDate.parse("2022-09-18"),70000);
    }

    @AfterEach
    void tearDown() {

    }


    /**
     * A simple test to verify that {@link Employee#changeName} indeed
     * works for both the first and last name
     */
    @Test
    void changeName() {
        //Check before changing name
        assertEquals("Bryan", emp.getFirstName());
        assertEquals("Kyng", emp.getLastName());
        //Check after changing name
        emp.changeName("Brian", "King");
        assertEquals("Brian", emp.getFirstName());
        assertEquals("King", emp.getLastName());
    }

    /**
     * A simple test to verify that {@link Employee#raiseSalary} indeed works
     */
    @Test
    void raiseSalary() {
        //Check before raising salary
        assertEquals(70000,emp.getSalary());
        //Check after raising salary
        emp.raiseSalary(900);
        assertEquals(70900,emp.getSalary());

    }

    /**
     * A simple test to verify that {@link Employee#equals} indeed works
     */
    @Test
    void testEquals() {
        //Check two equal Employee instances
        Employee empTest = new Employee(20, "Linh", "Nguyen", 123456788,
                LocalDate.parse("2022-09-18"),70000);
        assertEquals(true,emp.equals(empTest));

        //Check two unequals Employee instances
        empTest = new Employee(20, "Linh", "Nguyen", 123400000,
                LocalDate.parse("2022-09-18"),70000);
        assertEquals(false,emp.equals(empTest));
    }


    /**
     * A simple test to verify that IDFactory indeed works
     */
    @Test
    void testIDFactory(){
        Employee emp1 = new Employee(100,"A","B",100000000, LocalDate.now(), 10000);
        Employee emp2 = new Employee(101,"A","B",100000000, LocalDate.now(), 10000);

        //Create a new Employee with the same id
        Employee emp3 = new Employee(101,"A","B",100000000, LocalDate.now(), 10000);

        assertEquals(100,emp1.getEmpID());
        assertEquals(101,emp2.getEmpID());
        assertNotEquals(101, emp3.getEmpID());
    }



}