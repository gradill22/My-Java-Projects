package Homework4;

/*
Implement a class Car with the following properties. A car has a certain fuel efficiency (measured in miles/gallon) and
a certain amount of fuel in the gas tank. The efficiency is specified in the constructor, and the initial fuel level is
0. Supply a method drive that simulates driving the car for a certain distance, reducing the fuel level in the gas tank,
and methods getGasLevel, to return the current fuel level, and addGas, to tank up.
 */

public class Car {
    double fuel;
    double efficiency;

    public Car(double efficiency) {
        this.efficiency = efficiency;
        this.fuel = 0;
    }

    public void drive(double miles) {
        fuel -= miles / efficiency;
    }

    public double getGasLevel() {
        return fuel;
    }

    public void addGas(double fuel) {
        this.fuel += fuel;
    }
}