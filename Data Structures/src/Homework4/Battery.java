package Homework4;

/*
Write a class Battery that models a rechargeable battery. A battery has a constructor
public Battery(double capacity)
where capacity is a value measured in milliampere hours. A typical AA battery has a capacity of 2000 to 3000 mAh.
The method
public void drain(double amount)
drains the capacity of the battery by the given amount. The method
public void charge()
charges the battery to its original capacity. The method
public double getRemainingCapacity()
gets the remaining capacity of the battery.
*/

public class Battery {
    double charge;
    double capacity;

    public Battery(double capacity) {
        this.capacity = capacity;
        this.charge = capacity;
    }

    public void drain(double amount) {
        this.charge -= amount;
    }

    public void charge() {
        this.charge = this.capacity;
    }

    public double getRemainingCapacity() {
        return charge;
    }
}