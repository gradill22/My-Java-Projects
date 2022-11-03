package FreePractice;

public class Q9 {
    public static void main(String[] args) {
        double n2 = 3;
        double sum = 0;
        for(double n1 = 1; n1 <= 97; n1 += 2) {
            sum += n1 / n2;
            n2 += 2;
        }
        System.out.println(sum);
    }
}