package Checkbook;

import java.util.ArrayList;
import java.text.NumberFormat;

public class Checkbook {
    private final ArrayList<Transaction> allTransactions;
    private float startingBalance;

    public Checkbook(float startingBalance) {
        allTransactions = new ArrayList<>();
        this.startingBalance = startingBalance;
    }

    public float getBalance() {
        return startingBalance + sumOfArrayList(allTransactions);
    }

    public int getTransactionCount() {
        return allTransactions.size();
    }

    public void addCredit(float credit, String str, String month, int day, int year) {
        allTransactions.add(new Transaction(-credit, str, month, day, year));
    }

    public void addDeposit(float deposit, String str, String month, int day, int year) {
        allTransactions.add(new Transaction(deposit, str, month, day, year));
    }

    public void setStartingBalance(float startingBalance) {
        this.startingBalance = startingBalance;
    }

    public float getStartingBalance() {
        return startingBalance;
    }

    public Transaction getLastAndRemove() {
        if(allTransactions.size() > 0) {
            Transaction lastTransaction = allTransactions.get(allTransactions.size() - 1);
            allTransactions.remove(allTransactions.size() - 1);
            return lastTransaction;
        }
        return new Transaction();
    }

    public String toString() {
        StringBuilder str = new StringBuilder("Starting balance: " + currencyFormat(startingBalance) +
                "\nCalculated Ending Balance: " + currencyFormat(getBalance()) + "\n\nTransactions:\n");
        if(allTransactions.size() > 0) {
            for(int i = 0; i < allTransactions.size(); i++) {
                str.append(i + 1).append(") ").append(allTransactions.get(i).getMonthIndex()).append("/").append(allTransactions.get(i).getDay())
                        .append("/").append(allTransactions.get(i).getYear()).append(" | ")
                        .append(currencyFormat(allTransactions.get(i).getAmount()));
                if(!allTransactions.get(i).getDescription().isEmpty()) {
                    str.append(" | ").append(allTransactions.get(i).getDescription());
                }
                str.append("\n");
            }
        } else {
            str.append("There are no transactions\n");
        }
        return str.toString();
    }

    public static String currencyFormat(float n) {
        if(n < 0.01 && n > -0.01) {
            return "$0.00";
        }
        return NumberFormat.getCurrencyInstance().format(n);
    }

    private float sumOfArrayList(ArrayList<Transaction> arr) {
        float sum = 0;
        for(Transaction t : arr) {
            sum += t.getAmount();
        }
        return sum;
    }
}