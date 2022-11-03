package Homework4;

/*
Implement the Fan class. Write a test program that creates two Fan objects. Assign maximum speed, radius 10, color
yellow, and turn it on to the first object. Assign medium speed, radius 5, color blue, and turn it off to the second
object. Display the objects by invoking their toString() method.
*/

public class Q3 {
    public static void main(String[] args) {
        Fan f1 = new Fan(Fan.FAST, 10, "yellow", true);
        System.out.println(f1);
        Fan f2 = new Fan(Fan.MEDIUM, 5, "blue", false);
        System.out.println(f2);
    }
}