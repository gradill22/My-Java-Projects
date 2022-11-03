package Homework4;

public class Q2 {
    public static void main(String[] args) {
        Battery bat_larry = new Battery(3000);
        bat_larry.drain(1000);
        System.out.println(bat_larry.getRemainingCapacity());
        bat_larry.charge();
        System.out.println(bat_larry.getRemainingCapacity());
    }
}