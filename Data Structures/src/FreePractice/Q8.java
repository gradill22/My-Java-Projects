package FreePractice;

public class Q8 {
    public static void main(String[] args) {
        final int INITIAL_TUITION = 10000;
        final double INCREASE = 0.05;
        double tuition = INITIAL_TUITION;
        for(int i = 0; i < 10; i++) {
            tuition *= (1 + INCREASE);
        }
        System.out.println(tuition + "\n" + tuition * 4);
    }
}