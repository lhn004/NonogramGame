/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2023
 *
 * Name: Linh Nguyen
 *
 * Project: csci205
 * Package: lab08
 * File: Payable
 * Description:
 * A Payable Interface
 * ****************************************
 */
package lab08;

/**
 * The Payable Interface. Any entity that implements this class
 * provides a uniform interface so that an account can be used
 * to make payments to Payable object.
 */
public interface Payable {
    /**
     * @return who the payable person should be
     */
    public String getPayTo();

    /**
     * @param number of hours
     * @return the amount of money that should be paid
     */
    public double calculatePay(double hours);

    /**
     * @return a string that should be placed in the "Memo:" field of the check
     */
    public String getPayMemo();
}
