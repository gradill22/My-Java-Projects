package CheckbookGUI;

import java.util.ArrayList; // imports array list to keep track of transactions
import java.text.NumberFormat; // imports number formatter to format float values to currency string

public class Checkbook {
    private final ArrayList<Float> allTransactions; // this arraylist keeps track of all transactions
    private float startingBalance; // this is the starting balance at the beginning of the bank statement

    public Checkbook(float startingBalance) {
        allTransactions = new ArrayList<>(); // creates new list to keep track of transactions in object
        this.startingBalance = startingBalance; // sets starting balance
    }

    public float getBalance() { // returns a value representing how close they are to their ending balance
        return startingBalance + sumOfArrayList(allTransactions); // returns the calculated over/under value
    }

    public int getTransactionCount() {
        return allTransactions.size(); // returns the amount of transactions added to the arraylist
    }

    public void addCredit(float credit) {
        allTransactions.add(-credit); // adds a negative value to the transaction arraylist
    }

    public void addDeposit(float deposit) {
        allTransactions.add(deposit); // adds a positive value to the transaction arraylist
    }

    public void setStartingBalance(float startingBalance) { // sets the starting value to a different value
        this.startingBalance = startingBalance;
    }

    public float getStartingBalance() { // returns the starting balance
        return startingBalance;
    }

    public float getLastAndRemove() {
        if(allTransactions.size() > 0) { // only executes if transactions are listed
            float lastTransaction = allTransactions.get(allTransactions.size() - 1); // new float value keeps the last transaction value
            allTransactions.remove(allTransactions.size() - 1); // removes the last added transaction from arraylist
            return Math.abs(lastTransaction); // returns the last transaction value to be reprinted
        }
        return 0; // returns 0 if the arraylist is empty
    }

    public String toString() { // this is what's printed out in the text file
        StringBuilder str = new StringBuilder("Starting balance: " + currencyFormat(startingBalance) + "\n\nTransactions:\n"); // creates string builder object to print out in end
        if(allTransactions.size() > 0) {
            for(int i = 0; i < allTransactions.size(); i++) { // prints out all transactions in order of previous inputs if transactions exist
                str.append(i + 1).append(") ").append(currencyFormat(allTransactions.get(i))).append("\n");
            }
        } else {
            str.append("There are no transactions\n"); // prints there are no transactions in arraylist
        }
        return str.append("\nCalculated Ending Balance: ").append(currencyFormat(getBalance())).toString(); // returns full string with ending balance to print in text file
    }

    public static String currencyFormat(float n) { // public and static to be used in both CheckbookGUI and Checkbook
        if(n < 0.01 && n > -0.01) {
            return "$0.00";
        }
        return NumberFormat.getCurrencyInstance().format(n); // returns formatted value to default US currency ($)
    }

    private float sumOfArrayList(ArrayList<Float> arr) {
        float sum = 0;
        for(float i : arr) {
            sum += i;
        }
        return sum;
    }
}