package BulgarianSolitaire;

public class BulgarianSolitaire {
    private static final int NUM_CARDS = 45;
    private static int[] cardPiles = new int[4];  // can be any length

    public static void main(String[] args) {
        int[] goal = {9, 8, 7, 6, 5, 4, 3, 2, 1};  // what the sorted piles should look like at the end
        int turns = 0;
        assignPiles();
        while(!equals(sorted(cardPiles.clone()), goal)) {
            printArray(cardPiles);
            play();
            turns++;
        }
        printArray(cardPiles);
        System.out.printf("\nIt took %d iterations to reach the goal!", turns);
    }

    private static void assignPiles() {
        int numCardsCopy = NUM_CARDS;
        int index = 0;
        int cards;
        while(numCardsCopy > 0 && index < cardPiles.length - 1) {
            cards = (int) (Math.random() * (numCardsCopy / 2)) + 1;
            cardPiles[index] += cards;
            index++;
            numCardsCopy -= cards;
        }
        cardPiles[cardPiles.length - 1] += NUM_CARDS - sum(cardPiles);
    }

    private static void play() {
        int nextPile = 0;
        for(int i = 0; i < cardPiles.length; i++) {
            if(cardPiles[i] > 0) {
                cardPiles[i]--;
                nextPile++;
            }
        }
        cardPiles = stripZeros(cardPiles);
        int[] newPiles = new int[cardPiles.length + 1];
        for(int i = 0; i < cardPiles.length; i++) {
            newPiles[i] = cardPiles[i];
        }
        newPiles[newPiles.length - 1] = nextPile;
        cardPiles = newPiles;
    }

    private static int[] sorted(int[] arr) {
        int changes = 1;
        while(changes > 0) {
            changes = 0;
            for(int i = 0; i < arr.length - 1; i++) {
                if(arr[i] < arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    changes++;
                }
            }
        }
        return arr;
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for(int n: arr) {
            sum += n;
        }
        return sum;
    }

    private static int[] stripZeros(int[] arr) {
        int zerosFound = 0;  // tracks how many times 0s were removed to prevent indexing errors
        int[] newArr = new int[arr.length - countZero(arr)];
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > 0) {
                newArr[i - zerosFound] = arr[i];
            } else {
                zerosFound++;
            }
        }
        return newArr;
    }

    private static int countZero(int[] arr) {
        int zeros = 0;
        for(int n: arr) {
            if(n == 0) {
                zeros++;
            }
        }
        return zeros;
    }

    private static void printArray(int[] arr) {  // custom print method to have print layout at least somewhat readable
        for(int num : arr) {
            System.out.printf("%-2d| ", num);
        }
        System.out.println();
    }

    private static boolean equals(int[] arr1, int[] arr2) {
        if(arr1.length != arr2.length) {
            return false;
        }
        int index = 0;
        while(index < arr1.length && arr1[index] == arr2[index]) {
            index++;
        }
        return index == arr1.length;
    }
}