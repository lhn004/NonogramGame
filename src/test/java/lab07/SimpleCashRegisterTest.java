/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2023
 *
 * Name: Linh Nguyen
 * Date: 2/14/23
 * Time: 2:40 PM
 *
 * Project: csci205_labs
 * Package: lab07
 * Class: SimpleCashRegisterTest
 * Description:
 * A test for SimpleCashRegister
 * ****************************************
 */
package lab07;

import lab06.CashRegister;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCashRegisterTest {
    /**
     * Delta constant for checking floating point assertion
     */
    private static final double FLOAT_DELTA = 1.0E-12;

    /**
     * Cash register used in every test
     */
    private SimpleCashRegister register;

    @BeforeEach
    void setUp() {
        this.register = new SimpleCashRegister();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("getPurchaseCount() - a test for getPurchaseCount()")
    void getPurchaseCount() {
        // A new register should have no purchase
        assertEquals(0, register.getPurchaseCount());

        //Purchase two items and verify it to be correct
        register.scanItem(5);
        register.scanItem(9.75);
        assertEquals(2, register.getPurchaseCount());
    }

    @Test
    @DisplayName("getListOfPurchases() - a test for getListOfPurchases()")
    void getListOfPurchases() {
        // Make sure a new register has no purchase in the transaction
        assertEquals(0, register.getListOfPurchases().size());

        // Check items in purchase list after adding 2 items
        register.scanItem(2.5);
        register.scanItem(3.8);
        LinkedList<Double> expected = new LinkedList<>();
        expected.add(2.5);
        expected.add(3.8);
        assertEquals(expected, register.getListOfPurchases());
    }

    @DisplayName("getTransactionTotal() - a test for getTransaction()")
    @Test
    void getTransactionTotal() {
        //Make sure the transaction total is 0
        assertEquals(0.0, register.getTransactionTotal(), FLOAT_DELTA);

        //Scan 2 items and check transaction total
        register.scanItem(0.55);
        register.scanItem(1.27);
        assertEquals(1.82, register.getTransactionTotal(), FLOAT_DELTA);
    }

    /**
     * A test to make sure exceptions are thrown if a bad scan value is passed
     * to the register, and NOT thrown as long as we scan valid items
     */
    @Test
    @DisplayName("Scan item() - exception for bad value")
    void scanItemException() {
        // Scan for a negative price
        assertThrows(IllegalArgumentException.class, () -> register.scanItem(-0.5));

        // Scan for a large price
        assertThrows(IllegalArgumentException.class, () -> register.scanItem(1000000));

        // Make sure a good scan does NOT throw any exception
        assertDoesNotThrow(() -> register.scanItem(10.0));
    }

    /**
     * A test to make sure exceptions are thrown if the a bad input is scanned,
     * and NOT thrown as long as it is valid
     */

    @DisplayName("collectPayment() - exception for negative unit count")
    @Test
    void collectPaymentTest() {
        //Scan for a negative unit count
        assertThrows(IllegalArgumentException.class, () -> register.collectPayment(Money.QUARTER, -5));

        // Make sure a good scan does NOT throw any exception
        assertDoesNotThrow(() -> register.collectPayment(Money.PENNY, 10));
    }


    @Test
    @DisplayName("collectPayment() - a test for collectPayment()")
    void collectPayment() {
        // Make sure a new register has 0 collectedPaymeny
        assertEquals(0, register.getPaymentCollected());

        // Check if 3 units of quarter is 0.75
        register.collectPayment(Money.QUARTER, 3);
        assertEquals(0.75, register.getPaymentCollected());

        // Check if after adding 5 units of penny, the collectedPayment is 0.8
        register.collectPayment(Money.PENNY, 5);
        assertEquals(0.8, register.getPaymentCollected());


    }

    /**
     * A test to make sure exceptions are thrown if the register tries
     * to close the transaction while the transaction is not fully paid,
     * and NOT thrown as long as it is fully paid
     */
    @Test
    @DisplayName("giveChange() - exception for not fully paid transaction")
    void giveChangeTest() {
        // Check if an exception is thrown when the transaction is not fully paid but the register tries to close the transaction
        register.scanItem(0.55);
        register.scanItem(1.27);
        register.collectPayment(Money.DOLLAR, 1);
        assertThrows(ChangeException.class, () -> register.giveChange());

        // Check fully paid transaction doesn't throw an exception
        register.collectPayment(Money.QUARTER, 3);
        register.collectPayment(Money.NICKEL, 2);
        assertDoesNotThrow(() -> register.giveChange());

    }

    @Test
    @DisplayName("giveChange() - a test for giveChange()")
    void giveChange() throws ChangeException {
        register.scanItem(0.5);
        register.scanItem(1.25);
        register.collectPayment(Money.DOLLAR, 1);
        register.collectPayment(Money.QUARTER, 1);
        register.collectPayment(Money.NICKEL, 10);
        //Check for fully paid transaction
        assertEquals(0, register.giveChange());

        //Check when customer overpays
        register.collectPayment(Money.NICKEL, 10);
        assertEquals(0.5, register.giveChange());



    }

    @Test
    @DisplayName("testEquals() - a test to compare two registers")
    void testEquals() {
        SimpleCashRegister register1 = new SimpleCashRegister();

        // Two new registers should be equal
        assertEquals(register, register1);

        // Scan two new items and collect payment for one register
        register.scanItem(0.55);
        register.scanItem(1.27);
        register.collectPayment(Money.FIVE,2);
        assertNotEquals(register, register1);

        // Scan two new items and collect payment for the other register
        register1.scanItem(0.55);
        register1.scanItem(1.27);
        register1.collectPayment(Money.FIVE,2);
        assertEquals(register, register1);

    }
}
