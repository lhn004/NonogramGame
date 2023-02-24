/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 23/02/2023
 * Time: 22:15
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: AccountTest
 *
 * Description:
 * A simple test for Account class
 * *****************************************/
package lab08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    /** Threshold for checking double values for equality */
    private static final double FLOAT_DELTA = 1.0E-10;

    /** Initial deposit */
    private static final double INIT_DEPOSIT = 2000.0;

    /** Initial deposit */
    private static final double INIT_HOURLY_WAGE = 30;

    /** A common Account object to use for all tests */
    private Account acct;

    /** A common employee for dealing with an example Payable */
    private Employee emp;

    @BeforeEach
    void setUp() {
        // Set up an account to have 1000 initial balance
        this.acct = new Account(INIT_DEPOSIT);

        // Set up the test employee to be paid $30/hour
        this.emp = new Employee(1, "brian", "King", 123459876,
                LocalDate.now(), INIT_HOURLY_WAGE * 40*52);

    }

    /**
     * A test for credit method in Account class
     */
    @Test
    void credit() {
        assertEquals(INIT_DEPOSIT, acct.getBalance(), FLOAT_DELTA);
        this.acct.credit(1000);
        assertEquals(INIT_DEPOSIT + 1000, acct.getBalance(), FLOAT_DELTA);
    }

    /**
     * A test for debit method in Account class
     */
    @Test
    void debit() throws InsufficientFundsException{
        assertEquals(INIT_DEPOSIT, acct.getBalance(), FLOAT_DELTA);
        this.acct.debit(250);
        assertEquals(INIT_DEPOSIT - 250.0, acct.getBalance(), FLOAT_DELTA);

    }

    /**
     * A test to check for an exception thrown for a bad debit
     */
    @Test
    @DisplayName("InsuffcientFundsException from a bad debit")
    void debitException() {
        assertThrows(InsufficientFundsException.class, () -> acct.debit(INIT_DEPOSIT+1));
    }

    /**
     * Verify that the check writing interface for {@link Payable} objects
     * is working. This will also verify the {@link Account#getBalance()} method
     */
    @Test
    void processPayment() throws InsufficientFundsException{
        int hoursWorked = 40;
        acct.processPayment(emp,hoursWorked);
        double expected = INIT_DEPOSIT - (INIT_HOURLY_WAGE * hoursWorked);
        assertEquals(expected, acct.getBalance(), FLOAT_DELTA);

        // Let's verify the check amount here
        assertEquals(INIT_HOURLY_WAGE*hoursWorked,acct.getCheckAmount(),FLOAT_DELTA);
    }


    @Test
    void writeCheck() throws InsufficientFundsException {
        acct.processPayment(emp, 40);
        String sResult = acct.writeCheck();
        assertTrue(sResult!=null);
        assertFalse(sResult.equals(""));
        System.out.println(acct.writeCheck());
    }
}