package ui;

import model.EventLog;
import model.Transaction;
import model.WorkRoom;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Event;

// This class represents the account changing information along with user input.
// It also forms the run of GUI that suits the application.
public class AccountGUI implements ActionListener {
    private static final String JSON_STORE = "./data/workroom.json";
    private WorkRoom workRoom;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    Account account = new Account();
    List<Transaction> format;
    JFrame frame;
    JPanel panel;
    JButton depositButton;
    JButton withdrawButton;
    JButton checkInterestButton;
    JButton saveButton;
    JButton loadButton;
    JButton quitButton;
    JButton historyButton;
    JTextField depositText;
    JTextField withdrawText;
    JTextField checkInterestText;
    JTextField checkLatestTransactionText;
    JTextField balanceText;
    JLabel balanceLabel;
    JLabel checkLatestTransactionLabel;
    JLabel interestYearLabel;
    JTextField interestRateText;
    JLabel interestLabel;


    //EFFECTS: Starts the application
    public AccountGUI() throws FileNotFoundException {
        frame = new JFrame("Banking application");
        frame.setPreferredSize(new Dimension(1200, 1000));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        run();
        frame.setVisible(true);

        addDepositListener();
        addWithdrawListener();
        addCheckInterestListener();
        addSaveListener();
        addLoadListener();
        addHistoryListener();
    }

    //EFFECTS: adds the panel to the frame; makes new workroom and jsonWriter and jsonReader
    public void run() {
        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.orange);
        workRoom = new WorkRoom("Divyansh's workroom");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        JSONObject jsonObject = new JSONObject();
        presentation();
    }

    //EFFECTS: adds the function buttons to JPanel
    public void presentation() {
        addDepositButton();
        addWithdrawButton();
        addCheckInterestButton();
        addSaveButton();
        addLoadButton();
        addQuitButton();
        addSupplementButtons();
        addHistory();
    }

    //EFFECTS: make a new deposit button, adds the deposit button to the panel
    public void addDepositButton() {
        depositButton = new JButton("Deposit");
        depositButton.setBounds(50, 120, 250, 50);
        panel.add(depositButton);

        depositText = new JTextField();
        depositText.setBounds(350, 120, 100, 50);
        panel.add(depositText);
    }

    //MODIFIES: balance text, the latest transaction text
    //EFFECTS: if integer value of deposit text is greater than 0, add to balance and reflect change to latest
    //         transaction text; else print default error message
    private void addDepositListener() {
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                account.deposit(Integer.parseInt(depositText.getText()));
                balanceText.setText(Double.toString(account.printStatement()));
                if (Integer.parseInt(depositText.getText()) > 0) {
                    checkLatestTransactionText.setText("Deposited : " + depositText.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "The entered an invalid amount");
                }
            }
        });
    }

    //EFFECTS: make a new withdrawal button, adds the withdrawal button to the panel
    public void addWithdrawButton() {
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(50, 220, 250, 50);
        panel.add(withdrawButton);

        withdrawText = new JTextField();
        withdrawText.setBounds(350, 220, 100, 50);
        panel.add(withdrawText);
    }

    //MODIFIES: balance text, the latest transaction text
    //EFFECTS: if integer value of withdraw text is less than user's current balance and greater than zero
    //         subtract amount from user balance, otherwise print error message.
    private void addWithdrawListener() {
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                account.withdraw(Integer.parseInt(withdrawText.getText()));     //<-
                balanceText.setText(Double.toString(account.printStatement())); //<-
                System.out.println(account.getBalance());
                if (Integer.parseInt(withdrawText.getText()) <= workRoom.getTransactions().get(
                        workRoom.getTransactions().size() - 1).getBalance()
                        && Integer.parseInt(withdrawText.getText()) > 0) {
                    checkLatestTransactionText.setText("Withdrew :"
                            + Integer.parseInt(withdrawText.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "You entered an invalid amount");
                }
            }
        });
    }

    //EFFECTS: makes a new checkInterest button, interest label, interest text, year label
    //         checkInterest text and adds them to the panel
    public void addCheckInterestButton() {
        checkInterestButton = new JButton("Check Interest Accumulated");
        checkInterestButton.setBounds(50, 320, 250, 50);
        panel.add(checkInterestButton);

        checkInterestText = new JTextField();
        checkInterestText.setBounds(500, 320, 100, 50);
        panel.add(checkInterestText);

        interestYearLabel = new JLabel("Enter number of years: ");
        interestYearLabel.setBounds(350, 320, 180, 50);
        panel.add(interestYearLabel);

        interestRateText = new JTextField();
        interestRateText.setBounds(850, 320, 200, 50);
        panel.add(interestRateText);

        interestLabel = new JLabel("Balance after interest: ");
        interestLabel.setBounds(710, 320, 150, 50);
        panel.add(interestLabel);
    }

    //EFFECTS: prints deposited if lastTransaction was positive,
    //         prints withdrew if lastTransaction was negative,
    //         else display default message
    private void addCheckInterestListener() {
        checkInterestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double balance = Double.parseDouble(balanceText.getText());
                double years = Double.parseDouble(checkInterestText.getText());
                double interest = 0.325;
                double newBal = (balance * years * interest) + balance;
                if (years > 0) {
                    interestRateText.setText(String.valueOf(newBal));
                } else {
                    interestRateText.setText("Default: 0");
                }
            }
        });
    }

    //EFFECTS: make a new save button, adds the save button to the panel
    public void addSaveButton() {
        saveButton = new JButton("Save");
        saveButton.setBounds(50, 420, 250, 50);
        panel.add(saveButton);
    }

    // EFFECTS: saves the workroom to file
    public void addSaveListener() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    workRoom.addTransaction(new Transaction("Div", 189,
                            Double.parseDouble(balanceText.getText())));
                    balanceText.getText();
                    jsonWriter.open();
                    jsonWriter.write(workRoom);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(null, "Your balance has been updated!");
//                    System.out.println("Saved " + workRoom.getName() + " to " + JSON_STORE);
                } catch (FileNotFoundException es) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
            }
        });
    }

    //EFFECTS: make a new load button, adds the load button to the panel
    public void addLoadButton() {
        loadButton = new JButton("Load");
        loadButton.setBounds(50, 520, 250, 50);
        panel.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void addLoadListener() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAction();
            }
        });
    }

    //EFFECTS: helper method for addLoadListener
    //         loads workroom from file
    @SuppressWarnings("methodlength")
    public void loadAction() {
        try {
            workRoom = jsonReader.read();
            balanceText.setText(String.valueOf(workRoom.getTransactions().get(
                    workRoom.getTransactions().size() - 1).getBalance()));
            account.setBalance(workRoom.getTransactions().get(
                    workRoom.getTransactions().size() - 1).getBalance());
            JOptionPane.showMessageDialog(null, "Load was successful!");

            if (workRoom.numTransaction() == 1) {
                checkLatestTransactionText.setText("Deposited :"
                        + String.valueOf((int) workRoom.getTransactions().get(
                        workRoom.getTransactions().size() - 1).getBalance()));
            } else {
                if (((int) workRoom.getTransactions().get(workRoom.getTransactions().size() - 1).getBalance()
                        - (int)
                        workRoom.getTransactions().get(workRoom.getTransactions().size() - 2).getBalance()) > 0) {
                    checkLatestTransactionText.setText("Deposited :"
                            + String.valueOf((int) workRoom.getTransactions().get(
                            workRoom.getTransactions().size() - 1).getBalance()
                            - (int)
                            workRoom.getTransactions().get(workRoom.getTransactions().size() - 2).getBalance()));
                } else {
                    checkLatestTransactionText.setText("Withdrew :"
                            + String.valueOf(Math.abs((int) workRoom.getTransactions().get(
                            workRoom.getTransactions().size() - 1).getBalance()
                            - (int)
                            workRoom.getTransactions().get(workRoom.getTransactions().size() - 2).getBalance())));
                }
            }
            System.out.println("Loaded " + workRoom.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: make a new history button, adds the history button to the panel
    public void addHistory() {
        historyButton = new JButton("History");
        historyButton.setBounds(50, 720, 250, 50);
        panel.add(historyButton);
    }

    // EFFECTS: prints all the transactions in workroom to a new frame
    public void addHistoryListener() {
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame historyFrame = new JFrame();
                JPanel historyPanel = new JPanel();
                JLabel historyLabel = new JLabel("Your banking history : ");
                historyFrame.add(historyPanel);

                historyPanel.add(BorderLayout.NORTH, historyLabel);
                historyFrame.setVisible(true);

                format = new ArrayList<>();
                List<Transaction> transaction = workRoom.getTransactions();
                format.addAll(transaction);

                JList<Object> listObj = new JList<>(format.toArray());
                historyPanel.add(listObj);

                historyFrame.pack();
            }
        });
    }

    //EFFECTS: make a new quit button, adds the quit button to the panel
    public void addQuitButton() {
        quitButton = new JButton("Quit");
        quitButton.setBounds(50, 620, 250, 50);
        panel.add(quitButton);
        quitButton.addActionListener(this);
    }

    //EFFECTS: make a new latestTransaction label, checkLatestTransaction text, balance label, balance text
    //         and adds them to the panel
    public void addSupplementButtons() {
        checkLatestTransactionLabel = new JLabel("Your latest transaction was: ");
        checkLatestTransactionLabel.setBounds(680, 220, 200, 50);
        panel.add(checkLatestTransactionLabel);

        checkLatestTransactionText = new JTextField();
        checkLatestTransactionText.setBounds(850, 220, 200, 50);
        panel.add(checkLatestTransactionText);

        balanceLabel = new JLabel("Your current balance is: ");
        balanceLabel.setBounds(700, 120, 200, 50);
        panel.add(balanceLabel);

        balanceText = new JTextField();
        balanceText.setBounds(850, 120, 200, 50);
        panel.add(balanceText);
    }

    //EFFECTS: opens new frame when quit button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame quitFrame = new JFrame();
        JPanel quitPanel = new JPanel();
        JLabel quitLabel = new JLabel("Thank you for Banking With Us!");
        JLabel label = new JLabel(new ImageIcon("./data/mono.jpg"));

        quitFrame.setPreferredSize(new Dimension(1500, 1400));

        quitFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                EventLog el =  EventLog.getInstance();
                for (Event next : el) {
                    System.out.println(next.toString() + "\n");
                }
                System.exit(0);
            }
        });

        quitFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        quitFrame.pack();

        quitPanel.setLayout(null);
        quitFrame.add(quitPanel);
        quitFrame.add(label);

        quitLabel.setBounds(10, 30, 300, 45);
        quitPanel.add(quitLabel);
        quitFrame.setVisible(true);
    }
}
