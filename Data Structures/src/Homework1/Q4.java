package Homework1;

public class Q4 {
    public static void main(String[] args) {
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String randRank = ranks[(int) (Math.random() * ranks.length)];
        String randSuit = suits[(int) (Math.random() * suits.length)];
        System.out.printf("The card you picked is %s of %s", randRank, randSuit);
    }
}