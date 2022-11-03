package CheckbookGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // this and 2 previous imports are for GUI purposes
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // this and 2 previous imports are for text file purposes

public class Main extends JPanel {
    private static JFrame frame; // frame work of whole GUI
    private final JLabel amountLabel, startingBalanceLabel, transaction, balance; // labels for user inputs
    private static JButton credit, deposit; // buttons to input values into checkbook
    private static JTextField startingBalance, amount; // both user input text fields
    private final JButton enter, back, export, undo; // buttons to navigate GUI
    private static Checkbook checkbook; // the actual digital checkbook to keep track of all transactions

    public Main() { // adds all variables above to GUI
        setLayout(new FlowLayout());
        credit = new JButton("Credit");
        credit.addActionListener(new CreditListener());
        credit.setVisible(false);
        deposit = new JButton("Deposit");
        deposit.addActionListener(new DepositListener());
        deposit.setVisible(false);
        enter = new JButton("Enter");
        enter.addActionListener(new EnterListener());
        back = new JButton("Back");
        back.addActionListener(new BackListener());
        back.setVisible(false);
        undo = new JButton("Undo");
        undo.addActionListener(new UndoListener());
        undo.setVisible(false);
        export = new JButton("Export");
        export.addActionListener(new ExportListener());
        export.setVisible(false);
        add(startingBalanceLabel = new JLabel("Starting balance:"));
        add(startingBalance = new JTextField(10));
        add(amountLabel = new JLabel("Amount:"));
        amountLabel.setVisible(false);
        add(amount = new JTextField(8));
        amount.setVisible(false);
        add(credit);
        add(deposit);
        add(enter);
        add(back);
        add(undo);
        add(export);
        add(transaction = new JLabel());
        add(balance = new JLabel());
    }

    public static void main(String[] args) {
        frame = new JFrame("Checkbook");
        frame.setSize(250, 100);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // get user's screen size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation((screenSize.width / 2) - (frame.getWidth() / 2), (screenSize.height / 2) - (frame.getHeight() / 2)); // use screen size to center GUI
        frame.setContentPane(new Main());
        frame.setAlwaysOnTop(true); // keeps GUI on top to prevent it from being lost under other applications or software
        frame.setResizable(false); // this is to prevent the GUI's simplicity from being compromised
        frame.setVisible(true); // shows GUI
    }

    private class EnterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(isFloat(startingBalance.getText())) { // executes code if starting balance are float values
                if(checkbook == null) {
                    checkbook = new Checkbook(Float.parseFloat(startingBalance.getText())); // starts fresh checkbook from beginning
                } else {
                    checkbook.setStartingBalance(Float.parseFloat(startingBalance.getText()));
                }
                loadInputPage();
            } else {
                balance.setText("Input proper starting balance"); // prints error message
            }
        }
    }

    private class BackListener implements ActionListener { // allows user to go between input pages
        public void actionPerformed(ActionEvent e) {
            if(amount.isVisible()) { // goes back to starting balance input page
                loadBalancePage();
            } else {
                loadInputPage(); // goes back to checkbook input page
            }
        }
    }

    private class UndoListener implements ActionListener { // reprints previously inputted transaction and removes from arraylist
        public void actionPerformed(ActionEvent e) {
            amount.setText(String.valueOf(checkbook.getLastAndRemove())); // gets last transaction value and sets radio buttons accordingly
            setBottomText(); // resets bottom text
        }
    }

    private static class ExportListener implements ActionListener { // prints and opens checkbook text file
        public void actionPerformed(ActionEvent e) {
            try { // executes the following code until there is an Exception
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("LLL yyyy"); // for name of file for easy user organization
                File file = new File(LocalDate.now().format(dtf) + " Checkbook.txt"); // creates new text file
                FileOutputStream fos = new FileOutputStream(file); // creates new output stream for text file
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); // creates writing object for text file
                bw.write(checkbook.toString()); // writes out checkbook into text file
                bw.close(); // closes buffered writer
                Desktop.getDesktop().open(file); // opens checkbook text file with user's desktop
            } catch (Exception exception) { // catches Exception errors and prevents console error
                exception.printStackTrace(); // prints stack trace of error
            }
        }
    }

    private class CreditListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(isFloat(amount.getText())) { // executes code if amount input is proper float value
                checkbook.addCredit(Float.parseFloat(amount.getText())); // adds credit value to checkbook
                amount.setText(""); // resets input text field
                setBottomText(); // resets bottom text
            } else {
                transaction.setText("");
                balance.setText("Input proper number value");
            }
        }
    }

    private class DepositListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(isFloat(amount.getText())) { // executes code if amount input is proper float value
                checkbook.addDeposit(Float.parseFloat(amount.getText())); // adds credit value to checkbook
                amount.setText(""); // resets input text field
                setBottomText(); // resets bottom text
            } else {
                transaction.setText("");
                balance.setText("Input proper number value");
            }
        }
    }

    private void loadInputPage() { // loads the transaction input page
        startingBalanceLabel.setVisible(false);
        startingBalance.setVisible(false);
        enter.setVisible(false);
        amountLabel.setVisible(true);
        amount.setVisible(true);
        credit.setVisible(true);
        deposit.setVisible(true);
        undo.setVisible(true);
        export.setVisible(true);
        back.setVisible(true);
        setBottomText();
    }

    private void loadBalancePage() { // loads the starting balance input page
        amount.setVisible(false);
        transaction.setVisible(false);
        balance.setVisible(false);
        amountLabel.setVisible(false);
        credit.setVisible(false);
        deposit.setVisible(false);
        undo.setVisible(false);
        startingBalanceLabel.setVisible(true);
        startingBalance.setVisible(true);
        startingBalance.setText(String.valueOf(checkbook.getStartingBalance()));
        back.setVisible(true);
        enter.setVisible(true);
        frame.setSize(250, 100); // sets frame to proper dimensions for balance input page
    }

    private void setBottomText() { // resets text at bottom of GUI user input page
        transaction.setVisible(true); // makes transaction count visible if not already
        balance.setVisible(true); // makes end balance visible if not already
        transaction.setText("  Transactions: " + checkbook.getTransactionCount()); // sets transaction text
        balance.setText("Balance: " + Checkbook.currencyFormat(checkbook.getBalance())); // sets ending balance text
        frame.setSize(325, 125);
        if(balance.getText().length() >= 34) { // resizes GUI if there is too much text at bottom
            frame.setSize(325, 150);
        }
    }

    private boolean isFloat(String s) { // tests if a string input is a possible float value or not
        try {
            Float.parseFloat(s);
            return true; // returns true if Float class successfully parses string input
        } catch (NumberFormatException e) {
            return false; // returns false if Float class fails to parse string input
        }
    }
}