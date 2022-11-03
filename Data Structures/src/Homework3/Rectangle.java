package Homework3;

public class Rectangle {
    double width;
    double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle() {
        this.width = 1;
        this.height = 1;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double newWidth) {
        this.width = newWidth;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double newHeight) {
        this.height = newHeight;
    }

    public double getArea() {
        return this.width * this.height;
    }

    public double getPerimeter() {
        return this.width * 2 + this.height * 2;
    }
}