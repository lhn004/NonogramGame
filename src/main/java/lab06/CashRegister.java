/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 11/02/2023
 * Time: 20:20
 *
 * Project: csci205_labs
 * Package: lab06
 * Class: CashRegister
 *
 * Description:
 *
 * *****************************************/
package lab06;

/**
 * The different states that a cash register can be in
 *
 */
enum CashReisterState {
    NOT_READY,      // Not ready for a shift
    READY,          // Register ready for a transaction
    SCANNING,       // Processing a transaction
    RECEIVE_PMT,    // Receiving payment for a transaction

}

/**
 * A simple class to encapsulate a cash register
 */
public class CashRegister {
    /** The name of the cash register */
    private String registerName;

    /** The amount of cash the register is currently holding */
    private double cashInDrawer;

    /** The current transaction, or null if there is no transaction taking place */
    private Transaction currentTransaction;

    /** The total amount of money collected toward the transaction */
    private double paymentReceived;

    /** The current state of the cash register */
    private CashReisterState state;


    /**
     * Initialize a default, empty cash register
     */
    public CashRegister() {
        this.cashInDrawer = 0;
        this.currentTransaction = null;
        this.paymentReceived = 0;
        this.registerName = "Default";
        this.state = CashReisterState.NOT_READY;
    }

    public CashRegister(String register_name) {
        this.cashInDrawer = 0;
        this.currentTransaction = null;
        this.paymentReceived = 0;
        this.registerName = register_name;
        this.state = CashReisterState.NOT_READY;
    }

    /**
     * @return the cash register's name
     */
    public String getName() {return this.registerName;}

    /**
     * Set the cash register's new name
     * @param name the name of the cash register
     */
    public void setName(String name) {
        this.registerName = name;
    }

    /**
     * @return the cash register's current state
     */
    public CashReisterState getState() { return this.state;}

    /**
     * @return the amount of cash in the drawer
     */
    public double getCashInDrawer() {return this.cashInDrawer;}

    /**
     * Verify if the cash register is not ready
     * @return true if the state of the cash register is NOT READY. Otherwise, false
     */
    public boolean isNotReady() {
        return this.state == CashReisterState.NOT_READY;
    }


    /**
     * Verify if the cash register is ready
     * @return true if the state of the cash register is READY. Otherwise, false
     */
    public boolean isReady() {
        return this.state == CashReisterState.READY;
    }


    /**
     * Verify if the cash register is scanning items
     * @return true if the state of the cash register is SCANNING. Otherwise, false
     */
    public boolean isScanning() {
        return this.state == CashReisterState.SCANNING;
    }

    /**
     * Verify if the cash register is receiving payment
     * @return true if the state of the cash register is RECEIVE_PMT. Otherwise, false
     */
    public boolean isReceivingPmt() {
        return this.state == CashReisterState.RECEIVE_PMT;
    }

    /**
     * Initialize the amount of cash to be placed in the drawer
     * @param initCash amount of cash
     * @return true if the cash register successfully signed in. Otherwise, return false
     */
    public boolean signIn(double initCash) {
        if (this.isNotReady()) {
            this.cashInDrawer = initCash;
            this.state = CashReisterState.READY;
            return true;
        }
        return false;
    }


    /**
     * Let cashier sign out and reset the transaction.
     * If the cashier is not ready to sign out, return -1
     * @return the amount of cash in drawer
     */
    public double signOut() {
        // Check if the cash register finishes the transaction
        if (this.isReady()) {
            double cash = this.getCashInDrawer();

            // Reset the transaction
            this.state = CashReisterState.NOT_READY;
            this.cashInDrawer = 0;
            this.currentTransaction = new Transaction();
            return cash;
        }
        else {
            return -1;
        }
    }

    /**
     * Update the current transaction if the customer adds a new item
     * @param price the price of the item
     * @return true if the current is successfully updated. Otherwise, return false
     */
    public boolean scanItem(double price) {
        if (this.isReady()) {
            this.state = CashReisterState.SCANNING;
            this.currentTransaction = new Transaction();
            this.currentTransaction.addItem(price);
            return true;
            }

        else if (this.isScanning()) {
                this.currentTransaction.addItem(price);
                return true;
            }

        return false;
    }



    /**
     * Collect payment from customer.
     * @param payment the amount that the customer is payingtoward the transaction
     * @return:
     * A positive value indicating the amount overpaid if change is due
     * 0 if the exact amount was paid
     * A negative value indicating how much the customer still owes to make a full payment
     * The constant Double.MIN_VALUEif the register is in a state where collecting a payment is not allowed
     */
    public double collectPayment(double payment){
        if (this.isScanning()) {
            // Check if the customer has scanned any item. If not, then the customer is not allowed to pay
            if (this.currentTransaction.getTotalCost() != 0) {
                this.state = CashReisterState.RECEIVE_PMT;
            }
        }

        if (this.isReceivingPmt()) {
            this.paymentReceived += payment;
            this.cashInDrawer += payment;

            // If the amount of payment received equals to the amount of the transaction, set the state to READY (ends a transaction)
            if (this.paymentReceived == this.currentTransaction.getTotalCost()) {
                this.state = CashReisterState.READY;

            // If the amount of payment received is greater than the amount of transaction, change needs to be returned to customer
            } else if (this.paymentReceived > this.currentTransaction.getTotalCost()) {
                this.cashInDrawer -= this.getAmountOwed();
                this.state = CashReisterState.READY;
            }

            // If the customer paid the transaction completely, reset the payment received to 0
            if (this.getAmountOwed() == 0) {
                this.paymentReceived = 0;
            }

            return this.getAmountOwed();
        }

        return Double.MIN_VALUE;
    }


    /**
     * @return the amount the customer owes
     */
    public double getAmountOwed(){
        // If the transaction has not been completed, return the amount customer owes
        if (this.isReceivingPmt()) {
            return this.paymentReceived - this.currentTransaction.getTotalCost();
        }
        // If the customer is in the middle of the transaction, return the amount of the current transaction
        else if (this.isScanning()) {
            return this.currentTransaction.getTotalCost();
        }
        return 0;
    }


    /**
     * Print the current state of a CashRegister object
     */
    @Override
    public String toString() {
        return "CashRegister{" +
                "registerName='" + registerName + '\'' +
                ", cashInDrawer=" + cashInDrawer +
                ", currentTransaction=" + currentTransaction +
                ", paymentReceived=" + paymentReceived +
                ", state=" + state +
                '}';
    }
}
