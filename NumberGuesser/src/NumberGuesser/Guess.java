package NumberGuesser;

public class Guess {
    private final int attempts;
    private final boolean cpu;

    public Guess(int attempts, boolean cpu) {
        this.attempts = attempts;
        this.cpu = cpu;
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean cpu() {
        return cpu;
    }

    public static int playerWins() {
        int score = 0;
        for(int i = 0; i < Main.attempts.size(); i++) {
            if(Main.attempts.get(i).cpu() && Main.attempts.get(i).getAttempts() < CPU.attempts.get(i)) {
                score++;
            }
        }
        return score;
    }

    public static int cpuWins() {
        int score = 0;
        for(int i = 0; i < Main.attempts.size(); i++) {
            if(CPU.attempts.get(i) < Main.attempts.get(i).getAttempts()) {
                score++;
            }
        }
        return score;
    }

    public static int ties() {
        int ties = 0;
        for(int i = 0; i < Main.attempts.size(); i++) {
            if(CPU.attempts.get(i).equals(Main.attempts.get(i).getAttempts())) {
                ties++;
            }
        }
        return ties;
    }

    public static float playerTriesAvg() {
        if(Main.attempts.size() > 0) {
            float num = 0;
            for(Guess i : Main.attempts) {
                num += i.getAttempts();
            }
            return num / (float) Main.attempts.size();
        }
        return 0;
    }

    public static float cpuTriesAvg() {
        if(Main.attempts.size() > 0) {
            float num = 0;
            for(int i : CPU.attempts) {
                num += i;
            }
            return num / (float) CPU.attempts.size();
        }
        return 0;
    }
}