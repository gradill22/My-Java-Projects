package Homework3;

public class Stock {
    String symbol, name;
    double previousClosingPrice, currentPrice;

    public Stock(String symbol, String name, double previousClosingPrice) {
        this.symbol = symbol;
        this.name = name;
        this.previousClosingPrice = previousClosingPrice;
        this.currentPrice = previousClosingPrice;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String newSymbol) {
        this.symbol = newSymbol;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public double getCurrentPrice() {
        return this.currentPrice;
    }

    public void setCurrentPrice(double newCurrentPrice) {
        this.previousClosingPrice = this.currentPrice;
        this.currentPrice = newCurrentPrice;
    }

    public double getPreviousClosingPrice() {
        return this.previousClosingPrice;
    }

    public void setPreviousClosingPrice(double newPreviousClosingPrice) {
        this.previousClosingPrice = newPreviousClosingPrice;
    }

    public double getPercentChange() {
        return 100 - (previousClosingPrice * 100 / currentPrice);
    }

    public String toString() {
        return "Name: " + this.name + "\nSymbol: " + this.symbol + "\nPrevious closing price: " +
                this.previousClosingPrice + "\nCurrent closing price: " + this.currentPrice +
                "\nPercentage change: " + String.format("%.2f", getPercentChange()) + "%";
    }
}