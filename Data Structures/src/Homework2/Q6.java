package Homework2;

public class Q6 {
    public static void main(String[] args) {
        String dayName = "What day?";
        int day = (int) (Math.random() * 7 + 1);
        switch(day) {
            case 1: dayName = "Sunday"; break;
            case 2: dayName = "Monday"; break;
            case 3: dayName = "Tuesday"; break;
            case 4: dayName = "Wednesday"; break;
            case 5: dayName = "Thursday"; break;
            case 6: dayName = "Friday"; break;
            case 7: dayName = "Saturday"; break;
        }
        System.out.printf("%d\n%s", day, dayName);
    }
}