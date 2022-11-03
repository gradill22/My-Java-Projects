package Checkbook;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main extends JPanel {
    private static JFrame frame;
    private static JLabel transaction, balance;
    private static  JButton back, export;
    private static JTextField startingBalance, amount, description;
    private static JSpinner day, month, year;
    private static Checkbook checkbook;
    private static boolean inputPageShown;
    private static Dimension screenSize;
    private static ArrayList<String> pastStartBalances;
    private static final File startBalances = new File("Starting_Balances.txt");
    private static final String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };

    public Main() {
        setLayout(new FlowLayout());
        add(new JLabel("Starting balance:"));
        add(startingBalance = new JTextField(10));
        JButton enter = new JButton("Enter");
        enter.addActionListener(new EnterListener());
        add(enter);
        if(inputPageShown) {
            add(back = new JButton("Back"));
            back.addActionListener(new BackListener());
            add(export = new JButton("Export"));
            export.addActionListener(new ExportListener());
            startingBalance.setText(String.valueOf(checkbook.getStartingBalance()));
        }
        frame.setSize(250, 100);
        frame.setLocation((screenSize.width / 2) - (frame.getWidth() / 2), (screenSize.height / 2) - (frame.getHeight() / 2));
    }

    private static class BalancePage extends JPanel implements ChangeListener {
        public BalancePage() {
            setLayout(new FlowLayout());
            add(new JLabel("Amount:"));
            add(amount = new JTextField(8));
            add(new JLabel("Description:"));
            add(description = new JTextField(11));
            add(new JLabel("Month:"));
            add(month = new JSpinner(new SpinnerListModel(months)));
            if(LocalDate.now().getMonthValue() == 1) {
                month.setValue(months[11]);
            } else {
                month.setValue(months[LocalDate.now().getMonthValue() - 2]);
            }
            month.addChangeListener(this);
            add(new JLabel("Day:"));
            add(day = new JSpinner(new SpinnerNumberModel(LocalDate.now().getDayOfMonth(), 1, 31, 1)));
            add(new JLabel("Year:"));
            add(year = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(), LocalDate.now().getYear() - 1, LocalDate.now().getYear(), 1)));
            JButton credit, deposit;
            add(credit = new JButton("Credit"));
            credit.addActionListener(new CreditListener());
            add(deposit = new JButton("Deposit"));
            deposit.addActionListener(new DepositListener());
            add(back = new JButton("Back"));
            back.addActionListener(new BackListener());
            JButton undo;
            add(undo = new JButton("Undo"));
            undo.addActionListener(new UndoListener());
            add(export = new JButton("Export"));
            export.addActionListener(new ExportListener());
            add(transaction = new JLabel());
            add(balance = new JLabel());
            frame.setSize(383, 150);
            frame.setLocation((screenSize.width / 2) - (frame.getWidth() / 2), (screenSize.height / 2) - (frame.getHeight() / 2));
            setBottomText();
            inputPageShown = true;
        }

        public void stateChanged(ChangeEvent e) {
            int n = month.getValue().toString().length() - LocalDate.now().getMonth().toString().length();
            frame.setSize(383 + n, 150);
            frame.setLocation((screenSize.width / 2) - (frame.getWidth() / 2), (screenSize.height / 2) - (frame.getHeight() / 2));
        }
    }

    public static void main(String[] args) {
        frame = new JFrame("Checkbook");
        frame.setSize(250, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.addWindowListener(new Close());
        inputPageShown = false;
        try {
            if(startBalances.createNewFile()) {
                System.out.println("File created!");
            }
            FileInputStream fis = new FileInputStream(startBalances);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            pastStartBalances = new ArrayList<>();
            while(line != null) {
                System.out.println(line);
                pastStartBalances.add(line);
                line = br.readLine();
            }
            if(pastStartBalances.size() > 0) {
                if(checkbook == null) {
                    checkbook = new Checkbook(Float.parseFloat(pastStartBalances.get(pastStartBalances.size() - 1).substring(11)));
                } else {
                    checkbook.setStartingBalance(Float.parseFloat(pastStartBalances.get(pastStartBalances.size() - 1).substring(11)));
                }
                frame.setContentPane(new BalancePage());
                month.setValue(months[Integer.parseInt(pastStartBalances.get(pastStartBalances.size() - 1).substring(0, 2)) - 1]);
                day.setValue(Integer.parseInt(pastStartBalances.get(pastStartBalances.size() - 1).substring(3, 5)));
                year.setValue(Integer.parseInt(pastStartBalances.get(pastStartBalances.size() - 1).substring(6, 10)));
            } else {
                frame.setContentPane(new Main());
            }
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class EnterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(isFloat(startingBalance.getText())) {
                if(checkbook == null) {
                    checkbook = new Checkbook(Float.parseFloat(startingBalance.getText()));
                } else {
                    checkbook.setStartingBalance(Float.parseFloat(startingBalance.getText()));
                }
                frame.setContentPane(new BalancePage());
            } else {
                balance.setText("Input proper starting balance");
            }
        }
    }

    private static class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(amount.isShowing()) {
                frame.setContentPane(new Main());
            } else {
                frame.setContentPane(new BalancePage());
            }
        }
    }

    private static class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Transaction lastTransaction = checkbook.getLastAndRemove();
            amount.setText(String.valueOf(Math.abs(lastTransaction.getAmount())));
            description.setText(lastTransaction.getDescription());
            month.setValue(lastTransaction.getMonth());
            day.setValue(lastTransaction.getDay());
            year.setValue(lastTransaction.getYear());
            setBottomText();
        }
    }

    private static class ExportListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(checkbook != null) {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("LLL yyyy");
                    File file = new File(LocalDate.now().format(dtf) + " Checkbook.txt");
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    bw.write(checkbook.toString());
                    bw.close();
                    Desktop.getDesktop().open(file);
                    fos = new FileOutputStream(startBalances);
                    bw = new BufferedWriter(new OutputStreamWriter(fos));
                    String months, days;
                    if(monthValue(month.getValue().toString()) < 10) {
                        months = "0" + monthValue(month.getValue().toString());
                    } else {
                        months = String.valueOf(monthValue(month.getValue().toString()));
                    }
                    if(day.getValue().toString().length() < 2) {
                        days = "0" + day.getValue().toString();
                    } else {
                        days = day.getValue().toString();
                    }
                    pastStartBalances.add(months + "/" + days + "/" + year.getValue().toString() + " " + checkbook.getBalance());
                    for(String s : pastStartBalances) {
                        bw.write(s + "\n");
                    }
                    bw.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    private static class CreditListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(isFloat(amount.getText())) {
                checkbook.addCredit(Float.parseFloat(amount.getText()), description.getText(), month.getValue().toString(),
                        Integer.parseInt(String.valueOf(day.getValue())), Integer.parseInt(String.valueOf(year.getValue())));
                amount.setText("");
                description.setText("");
                setBottomText();
            } else {
                transaction.setText("");
                balance.setText("Input proper number value");
            }
        }
    }

    private static class DepositListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(isFloat(amount.getText())) {
                checkbook.addDeposit(Float.parseFloat(amount.getText()), description.getText(), month.getValue().toString(),
                        Integer.parseInt(String.valueOf(day.getValue())), Integer.parseInt(String.valueOf(year.getValue())));
                amount.setText("");
                description.setText("");
                setBottomText();
            } else {
                transaction.setText("");
                balance.setText("Input proper number value");
            }
        }
    }

    private static class Close implements WindowListener {
        public void windowOpened(WindowEvent e) {

        }

        public void windowClosing(WindowEvent e) {
            if(checkbook != null && !export.isSelected()) {
                export.doClick();
            }
        }

        public void windowClosed(WindowEvent e) {

        }

        public void windowIconified(WindowEvent e) {

        }

        public void windowDeiconified(WindowEvent e) {

        }

        public void windowActivated(WindowEvent e) {

        }

        public void windowDeactivated(WindowEvent e) {

        }
    }

    private static void setBottomText() {
        transaction.setVisible(true);
        balance.setVisible(true);
        transaction.setText("Transactions: " + checkbook.getTransactionCount());
        balance.setText("Balance: " + Checkbook.currencyFormat(checkbook.getBalance()));
    }

    private static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int monthValue(String s) {
        switch(s) {
            case "January" -> { return 1; }
            case "February" -> { return 2; }
            case "March" -> { return 3; }
            case "April" -> { return 4; }
            case "May" -> { return 5; }
            case "June" -> { return 6; }
            case "July" -> { return 7; }
            case "August" -> { return 8; }
            case "September" -> { return 9; }
            case "October" -> { return 10; }
            case "November" -> { return 11; }
            case "December" -> { return 12; }
        }
        return -1;
    }
}