/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 11/02/2023
 * Time: 20:19
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: Transaction
 *
 * Description:
 *
 * *****************************************/

package lab06;

/**
 * A class that encapsulates one transaction. It contains the number of items
 * purchased in the transaction and the total cost.
 */
public class Transaction {
    /** Number of items in the transaction */
    private int numItems;

    /** Transaction total */
    private double totalCost;

    /**
     * A new transaction will have 0 item and 0 totalCost
     */
    public Transaction() {
        this.numItems = 0;
        this.totalCost = 0;
    }

    /**
     * Add a new item to the transaction.
     * Increment the total number of items in the transaction and increase the total cost by the amount of the item's price
     * @param price the new item's price
     */
    public void addItem(double price) {
        this.numItems ++;
        this.totalCost += price;
    }

    /**
     * @return the transaction's total cost
     */

    public double getTotalCost() {return this.totalCost;}


    /**
     * @return the transaction's total number of items
     */

    public double getNumItems() {return this.numItems;}

    /**
     * Print the current state of a Transaction object
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "numItems=" + numItems +
                ", totalCost=" + totalCost +
                '}';
    }
}
