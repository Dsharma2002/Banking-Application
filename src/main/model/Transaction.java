package model;

import org.json.JSONObject;
import persistence.Writable;

/**
 * parts of the code were inspired and taken from JsonSerializationDemo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */


// This class represents the simple transaction from user account
public class Transaction implements Writable {
    protected String name;
    protected double balance;
    protected int bankId;
    protected int latestTransaction;

    //CONSTRUCTOR
    //EFFECTS: constructs a transaction status with name, bank id and starting balance
    public Transaction(String customerName, int uniqueNumber, double currentBalance) {
        name = customerName;
        bankId = uniqueNumber;
        if (currentBalance > 0) {
            balance = currentBalance;
        } else {
            balance = 0;
        }
    }

    //balance = currentBalance;
    //REQUIRES: latest transaction
    //EFFECTS: returns latest transaction
    public int getLatestTransaction() {
        return this.latestTransaction;
    }

    //MODIFIES: this
    //EFFECTS: assigns this.balance to balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    //REQUIRES: latest transaction
    //MODIFIES: this
    //EFFECTS: sets the latest transaction
    public void setLatestTransaction(int latestTransaction) {
        this.latestTransaction = latestTransaction;
    }

    //REQUIRES: name
    //EFFECTS: return name
    public String getName() {
        return name;
    }

    //REQUIRES: balance
    //EFFECTS: return balance
    public double getBalance() {
        return balance;
    }

    //REQUIRES: bank id
    //EFFECTS: return bank id
    public int getBankId() {
        return bankId;
    }

    //REQUIRES: amount > 0
    //MODIFIES: latest transaction
    //EFFECTS: adds amount to balance and returns balance, sets the latest transaction to amount
    public double simpleDeposit(int amount) {
        setLatestTransaction(amount);
        EventLog.getInstance().logEvent(new Event("Added money to account!"));
        return balance = balance + amount;
    }

    //REQUIRES: amount <= balance && amount > 0
    //MODIFIES: latest transaction
    //EFFECTS: subtracts amount from balance and returns balance, sets the latest transaction to -amount
    public double simpleWithdraw(int amount) {
        setLatestTransaction(-amount);
        EventLog.getInstance().logEvent(new Event("Withdrew money from account!"));
        return balance = balance - amount;
    }

    //EFFECTS: return 1 if latest transaction > 0, return -1 if latest transaction < 0, return 0 otherwise
    public int simpleRecentTransaction() {
        EventLog.getInstance().logEvent(new Event("Recent Transaction checked from account!"));
        return Integer.compare(getLatestTransaction(), 0);

    }

    //REQUIRES: years > 0
    //MODIFIES: balance
    //EFFECTS: return the balance + interest after years
    public double simpleKnowBalance(int years) {
        double interest = 0.325;
        EventLog.getInstance().logEvent(new Event("Checked interest to account!"));
        return (balance * years * interest) + balance;
    }

    // EFFECTS: returns string representation of this transaction
    public String toString() {
        return "balance" + ": " + balance;
    }

    //EFFECTS: creates new JSON object, puts balance and returns the object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("balance", getBalance());
        return json;
    }
}
