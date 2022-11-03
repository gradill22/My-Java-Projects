package Checkbook;

public class Transaction {
    private final float amount;
    private final String description;
    private final String month;
    private final int day;
    private final int year;

    public Transaction(float amount, String description, String month, int day, int year) {
        this.amount = amount;
        this.description = description;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Transaction() {
        amount = 0;
        description = "";
        month = "January";
        day = 1;
        year = 2021;
    }

    public float getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getMonth() {
        return month;
    }

    public int getMonthIndex() {
        switch(month) {
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
        return 0;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }
}