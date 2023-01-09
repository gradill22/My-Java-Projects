package Gradebook;

public class IndexStream {
    private int index;

    public IndexStream() {
        index = 0;
    }

    public int next() {
        index += 1;
        return index;
    }

    public int currentIndex() {
        return index;
    }

    public String toString() {
        return String.format("Index: %d", currentIndex());
    }
}