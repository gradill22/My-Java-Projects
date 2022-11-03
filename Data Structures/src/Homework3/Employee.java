package Homework3;

public class Employee {
    String name;
    double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double newSalary) {
        this.salary = newSalary;
    }

    public void raiseSalary(double raise) {
        this.salary += raise;
    }
}