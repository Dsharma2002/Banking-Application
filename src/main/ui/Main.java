package ui;

import java.io.FileNotFoundException;

/**
 * parts of the code were inspired and taken from JsonSerializationDemo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

// This is the entry point of the program. A new account is created and
// corresponding transactions are made to the account based on
// user inputs.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new AccountGUI();
    }
}
