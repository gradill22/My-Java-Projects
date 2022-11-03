package Homework4;

/*
Implement a class Car with the following properties. A car has a certain fuel efficiency (measured in miles/gallon) and
a certain amount of fuel in the gas tank. The efficiency is specified in the constructor, and the initial fuel level is
0. Supply a method drive that simulates driving the car for a certain distance, reducing the fuel level in the gas tank,
and methods getGasLevel, to return the current fuel level, and addGas, to tank up.
 */

public class Q1 {
    public static void main(String[] args) {
        Car myHybrid = new Car(50);
        myHybrid.addGas(20);
        myHybrid.drive(100);
        System.out.println(myHybrid.getGasLevel());
    }
}