package MovingGUI;

import java.util.ArrayList;

public class Datapoint {
    int x;
    int y;
    ArrayList<Datapoint> points;

    public Datapoint() {
        points = new ArrayList<>();
    }

    private Datapoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(int x, int y) {
        points.add(new Datapoint(x, y));
    }

    public int getX(int i) {
        return points.get(i).getX();
    }

    private int getX() {
        return x;
    }

    public int getY(int i) {
        return points.get(i).getY();
    }

    private int getY() {
        return y;
    }

    public String toString(int i) {
        return "(" + getX(i) + ", " + getY(i) + ")";
    }
}