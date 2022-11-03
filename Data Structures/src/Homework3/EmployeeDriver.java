package Homework3;

public class EmployeeDriver {
    public static void main(String[] args) {
        Employee emp = new Employee("Emily", 60000);
        System.out.println("Employee name: " + emp.getName());
        System.out.println("Employee salary: " + emp.getSalary());
        emp.setName("Bob");
        emp.setSalary(55000);
        System.out.println("Employee new name: " + emp.getName());
        System.out.println("Employee new salary: " + emp.getSalary());
        double raise = 5000;
        emp.raiseSalary(raise);
        System.out.println(emp.getName() + " got a raise of " + raise + ". Their new salary is " + emp.getSalary());
    }
}