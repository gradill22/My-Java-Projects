package Gradebook;

// This class helps iterate through some kind of list by incrementing with every use

public class IndexStream {
    private int index;

    public IndexStream() {
        index = 0;
    }

    public int next() {
        return ++index;
    }

    public int currentIndex() {
        return index;
    }

    // Test toString() method
    public String toString() {
        return String.format("Index: %d", currentIndex());
    }
}