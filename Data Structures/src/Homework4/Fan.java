package Homework4;

/*
Design a class named Fan to represent a fan. The class contains:
- Three constants named SLOW, MEDIUM, and FAST with values 1, 2, and 3 to denote a fan speed
- A private int data field named "speed" that specifies the speed of the fan (default SLOW)
- A private boolean data field named "on" that specifies whether the fan is on (default false)
- A private double data field named "radius" that specifies the radius of the fan (default 5)
- A String data field named "color" that specifies the color of the fan (default "blue")
- The accessor and mutator methods for all four data fields
- A no-arg constructor that creates a default fan
- A method named toString() that returns a String description for the fan. If the fan is on, the method returns the fan
speed, color, and radius in one combined String. If the fan is not on, the method returns fan color and radius along
with the String "fan is off" in one combined String.
*/

public class Fan {
    public static final int SLOW = 1;
    public static final int MEDIUM = 2;
    public static final int FAST = 3;
    private int speed;
    private boolean on;
    private double radius;
    private String color;

    public Fan(int speed, double radius, String color, boolean on) {
        setSpeed(speed);
        setOn(on);
        setRadius(radius);
        setColor(color);
    }

    public Fan() {
        setSpeed(SLOW);
        setOn(false);
        setRadius(5);
        setColor("blue");
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        switch(speed) {
            case 2: this.speed = MEDIUM; break;
            case 3: this.speed = FAST; break;
            default: this.speed = SLOW;
        }
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        if(isOn()) {
            return String.format("Speed: %d\nColor: %s\nRadius: %.1f\n", getSpeed(), getColor(), getRadius());
        }
        return String.format("Speed: %d\nColor: %s\nRadius: %.1f\nFan is off\n", getSpeed(), getColor(), getRadius());
    }
}