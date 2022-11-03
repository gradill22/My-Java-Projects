package FreePractice;

public class Q7 {
    public static void main(String[] args) {
        int count = 0;
        for(int i = 120; i <= 1000; i += 30) {
            System.out.print(i + " ");
            count += 1;
            if(count == 10) {
                System.out.println();
                count = 0;
            }
        }
    }
}