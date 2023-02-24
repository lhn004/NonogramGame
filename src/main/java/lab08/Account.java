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
 * Class: Account
 *
 * Description:
 *
 * *****************************************/
package lab08;

/**
 * Checked exception representing any issues that might arise from the Account
 * class
 */
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

public class Account {
    /** the current balance of the account */
    private double balance;

    /** the last Payable object that was paid */
    private Payable lastPayee;

    /** the last amount that was paid to the lastPayee */
    private double lastAmountPaid;

    public Account(double balance) {
        this.balance = balance;
        this.lastAmountPaid = 0;
        this.lastPayee = null;
    }

    /**
     * Credit the account with {@code amount} money
     * @param amount - the money added
     */
    public void credit(double amount) {
        this.balance += amount;
    }



    /**
     * Debit the account with {@code amount} money
     * @param amount- the amount of money to remove from account
     * @throws InsufficientFundsException if the funds were not available to debit from the balance
     */
    public void debit(double amount) throws InsufficientFundsException {
        if (this.balance < amount) {
            throw new InsufficientFundsException((String.format(
                    "INSUFFICIENT FUNDS: Required: $%.2f, Available: $%.2f", amount,this.balance)));
        }
        this.balance -= amount;
    }




    /**
     * Given a Payableobject and hours worked (or billed for), returns the amount that the Payable object should be paid.
     * @param payee - the person to be paid
     * @param hoursBilled - hours worked
     */
    public void processPayment(Payable payee, double hoursBilled) {
        double amountPaid = payee.calculatePay(hoursBilled);
        try {
            this.debit(amountPaid);
        }
        catch (InsufficientFundsException e) {
            System.out.println(e);
        }
        this.lastPayee = payee;
        this.lastAmountPaid = amountPaid;

    }

    /**
     *  @return  the amount of the last check that was prepared.
     *  If no check was prepared, then just return 0
     */
    public double getCheckAmount() {
        return lastAmountPaid;
    }

    /**
     * @return the current account balance
     */
    public double getBalance(){ return this.balance;}


    /**
     *  @return a single String that resembles the fields on a check, i.e. the person being paid, the memo to be printed, and the amount paid.
     *  If no check was prepared, then just return an empty string.
     *
     */
    public String writeCheck(){
        if (this.lastPayee == null) {
            return "";
        }
        String s = "Pay to:       " + lastPayee.getPayTo() + "\n";
        s += "Pay Memo:     " + lastPayee.getPayMemo() + "\n";
        s += "Pay Amount:   $" + this.lastAmountPaid;
        return s;
    }


    /**
     * Print out the current account balance.
     */
    public String toString() {
        String s = "Current account balance: " + this.balance;
        return s;
    }



}

