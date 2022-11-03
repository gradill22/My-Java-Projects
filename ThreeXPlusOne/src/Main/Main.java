package Main;

public class Main {
    public static void main(String[] args) {
        int highestPow = 0, highestIndex = 0, largestNum = 0,
                largestIndex = 0, longestIteration = 0, longestIndex = 0;
        for(int i = 3; i <= 100; i++) {
            int n = i;
            int count = 0;
            if(!isLog2(n)) {
                System.out.println("n = " + n);
                while (n > 1) {
                    System.out.print(n);
                    if (n % 2 == 0) {
                        if (isLog2(n) && n > highestPow) {
                            highestPow = n;
                            highestIndex = i;
                        }
                        n /= 2;
                        System.out.println(" / 2 = " + n);
                    } else {
                        n *= 3;
                        n += 1;
                        System.out.println(" * 3 + 1 = " + n);
                    }
                    count++;
                    if (n > largestNum) {
                        largestNum = n;
                        largestIndex = i;
                    }
                }
                System.out.println("Total iterations: " + count + "\n");
                if (count > longestIteration) {
                    longestIteration = count;
                    longestIndex = i;
                }
            }
        }
        System.out.println("Highest log2 number: " + highestPow + " (Iteration " + highestIndex + ")");
        System.out.println("Largest number: " + largestNum + " (Iteration " + largestIndex + ")");
        System.out.println("Longest Iteration: " + longestIteration + " (Iteration " + longestIndex + ")");
    }

    private static boolean isLog2(int n) {
        while(n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }
}