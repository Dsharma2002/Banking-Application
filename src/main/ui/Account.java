package ui;

import model.Transaction;
import org.json.JSONObject;
import java.io.FileNotFoundException;

/**
 * parts of the code were inspired and taken from JsonSerializationDemo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

// This class represents the account changing information along with user input.
public class Account {
    private static final String JSON_STORE = "./data/workroom.json";
    Transaction transaction = new Transaction("Div", 11, 0);

    //EFFECTS: starts the application
    public Account() throws FileNotFoundException {
        JSONObject jsonObject = new JSONObject();
    }

    //MODIFIES: balance, the latest transaction
    //EFFECTS: if user input is greater than 0, add to balance and return true
    //         else print default error message and return false
    public void deposit(int num) {
        if (num >= 0) {
            transaction.simpleDeposit(num);
            transaction.setBalance(transaction.getBalance());
            transaction.setLatestTransaction(num);
        } else {
            System.out.println("You did not enter a valid amount to deposit.");
        }
    }

    //EFFECTS: sets the user balance with provided num
    public void setBalance(double num) {
        transaction.setBalance(num);
    }

    //EFFECTS: gets the user balance
    public double getBalance() {
        return transaction.getBalance();
    }

    //MODIFIES: balance, the latest transaction
    //EFFECTS: if user input is less than user's current balance, print error
    //         message, otherwise subtract amount from user balance
    public void withdraw(int num) {
        if (transaction.getBalance() >= num && num >= 0) {
            transaction.simpleWithdraw(num);
            transaction.setLatestTransaction(-num);
        }
    }

    //EFFECTS: prints the current balance statement
    public double printStatement() {
        return transaction.getBalance();
    }

}
