package Sorting;

public class Main {
    public static void main(String[] args) {
        int[] intArr1 = new int[10];
        int[] intArr2 = new int[10];
        for(int i = 0; i < intArr1.length; i++) {
            intArr1[i] = (int) ((Math.random() * 99) + 1); // generate random numbers for first int array
            intArr2[i] = (int) ((Math.random() * 99) + 1); // generate random numbers for second int array
        }
        // both provided String arrays to test String sorting methods
        String[] array1 = {"mango", "apple", "Nectarine", "banana", "pear", "cherries", "Peach", "Grapes", "orange", "strawberries"};
        String[] array2 = {"Lettuce", "mushrooms", "avocado", "zucchini", "tomatoes", "broccoli", "Squash", "kale", "radish", "Cabbage"};

        // lots of proper, awesome, neat-looking printing of sorted and unsorted arrays
        System.out.println("Unsorted array: \n" + toString(intArr1));
        System.out.println("Sort Array using Selection Sort: \n" + toString(selectionSortInt(intArr1)));
        System.out.println("\nUnsorted Array: \n" + toString(intArr2));
        System.out.println("Sorted Array using Bubble Sort: \n" + toString(bubbleSortInt(intArr2)));
        System.out.println("\nUnsorted Array: \n" + toString(array1));
        System.out.println("Sorted Array using Selection Sort: \n" + toString(selectionSortString(array1)));
        System.out.println("\nUnsorted Array: \n" + toString(array2));
        System.out.println("Sorted Array using Bubble Sort: \n" + toString(bubbleSortString(array2)));
    }

    public static int[] selectionSortInt(int[] intArr) {
        int[] arr = new int[10];
        for(int i = 0; i < intArr.length; i++) {
            int minIndex = i;
            for(int j = i; j < intArr.length; j++) {
                if(intArr[j] < intArr[minIndex]) {
                    minIndex = j; // finds the index of the smallest number after looping i times
                }
            }
            // swap numbers and add to second array
            int temp = intArr[i];
            arr[i] = intArr[minIndex];
            intArr[i] = intArr[minIndex];
            intArr[minIndex] = temp;
        }
        return arr;
    }

    public static String[] selectionSortString(String[] intArr) {
        String[] arr = new String[10];
        for(int i = 0; i < intArr.length; i++) {
            int minIndex = i;
            for(int j = i; j < intArr.length; j++) {
                if(intArr[j].compareToIgnoreCase(intArr[minIndex]) < 0) {
                    minIndex = j; // finds the index of the least sorted String after looping i times
                }
            }
            // swap Strings and add to second array
            String temp = intArr[i];
            arr[i] = intArr[minIndex];
            intArr[i] = intArr[minIndex];
            intArr[minIndex] = temp;
        }
        return arr;
    }

    public static int[] bubbleSortInt(int[] arr) {
        int swaps = 0;
        while(swaps > -1) {
            for(int i = 1; i < arr.length; i++) {
                if(arr[i - 1] > arr[i]) {
                    // swaps numbers if number at index i - 1 is greater than number at index i
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    swaps++; // counts the amount of swaps
                }
            }
            // ends or continues looping depending on if swaps were made or not
            if(swaps == 0) {
                swaps = -1;
            } else {
                swaps = 0;
            }
        }
        return arr;
    }

    public static String[] bubbleSortString(String[] arr) {
        int swaps = 0;
        while(swaps > -1) {
            for(int i = 0; i < arr.length - 1; i++) {
                if(arr[i].compareToIgnoreCase(arr[i + 1]) > 0) {
                    // swap Strings if String at index i - 1 is greater than String at index i
                    String temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swaps++; // counts the amount of snaps
                }
            }
            // ends or continues looping depending on if swaps were made or not
            if(swaps == 0) {
                swaps = -1;
            } else {
                swaps = 0;
            }
        }
        return arr;
    }

    // prints out int arrays neatly
    private static String toString(int[] arr) {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(int i = 0; i < arr.length - 1; i++) {
            str.append(arr[i]).append(", ");
        }
        str.append(arr[arr.length - 1]).append("]");
        return str.toString();
    }

    // prints out String arrays neatly
    private static String toString(String[] arr) {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(int i = 0; i < arr.length - 1; i++) {
            str.append(arr[i]).append(", ");
        }
        str.append(arr[arr.length - 1]).append("]");
        return str.toString();
    }
}