package BaseballSimulator;

public class Player {
    private final String name;
    private final double battingAverage;
    private final int position;

    public Player(String name, double battingAverage, int position) {
        this.name = name;
        this.battingAverage = battingAverage;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public double getBattingAverage() {
        return battingAverage;
    }
}