package PitchCharting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pitcher {
    private String name;
    private int number;
    private int age;
    private ArrayList<String> arsenal;
    private final ArrayList<AtBat> atBats;
    private AtBat currentAB;
    private float inningsPitched;

    public Pitcher(String name, int number, int age, ArrayList<String> pitch) {
        this.name = name;
        this.number = number;
        this.age = age;
        arsenal = pitch;
        atBats = new ArrayList<>();
        currentAB = new AtBat();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setArsenal(ArrayList<String> pitches) {
        arsenal = pitches;
    }

    public void setInningsPitched(float ip) {
        inningsPitched = ip;
    }

    public String getPitchType(int i) {
        return arsenal.get(i);
    }

    public void addPitch(Pitch pitch) {
        currentAB.addPitch(pitch);
        if(pitch.isLastPitch()) {
            atBats.add(currentAB);
            currentAB = new AtBat();
        }
    }

    public ArrayList<AtBat> getAtBats() {
        return atBats;
    }

    public AtBat getCurrentAB() {
        return currentAB;
    }

    public AtBat getLastAtBat() {
        if(atBats.size() == 0) {
            return currentAB;
        }
        return atBats.get(atBats.size() - 1);
    }

    public Pitch getLastPitch() {
        if(currentAB.getPitches().size() == 0 && atBats.size() > 0) {
            return atBats.get(atBats.size() - 1).getPitches().get(atBats.get(atBats.size() - 1).getPitches().size() - 1);
        } else if(currentAB.getPitches().size() == 0 && atBats.size() == 0) {
            return null;
        }
        return currentAB.getPitches().get(currentAB.getPitches().size() - 1);
    }

    public void removeLastPitch() {
        if(currentAB.getPitches().size() == 0 && atBats.size() > 0) {
            currentAB = atBats.get(atBats.size() - 1);
            atBats.remove(atBats.get(atBats.size() - 1));
            String result = currentAB.getResult();
            if(!(result.contains("HBP") || result.contains("Walk") || result.contains("Error"))) {
                Main.outs--;
                if(result.contains("Double Play")) {
                    Main.outs--;
                } else if(result.contains("Triple Play")) {
                    Main.outs -= 2;
                }
                if(Main.outs < 0) {
                    if(Main.inning > 1) {
                        Main.inning--;
                        Main.outs += 3;
                    } else {
                        Main.outs = 0;
                    }
                }
            }
            currentAB.setResult("No result");

        }
        if(currentAB.getPitches().size() > 0) {
            currentAB.getPitches().remove(currentAB.getPitches().size() - 1);
        }
    }

    public int getPitchArsenalCount() {
        return arsenal.size();
    }

    public String pitchArsenalString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < arsenal.size() - 1; i++) {
            str.append(arsenal.get(i)).append(", ");
        }
        str.append(arsenal.get(arsenal.size() - 1));
        return str.toString();
    }

    public ArrayList<String> getPitchArsenal() {
        return arsenal;
    }

    private float getInningsPitched() {
        return inningsPitched;
    }

    private int getSingles() {
        int singles = 0;
        for(AtBat ab : atBats) {
            if(!ab.getResult().isBlank() && ab.getResult().contains("Single")) {
                singles++;
            }
        }
        return singles;
    }

    private int getDoubles() {
        int doubles = 0;
        for(AtBat ab : atBats) {
            if(!ab.getResult().isBlank() && ab.getResult().contains("Double")) {
                doubles++;
            }
        }
        return doubles;
    }

    private int getTriples() {
        int triples = 0;
        for(AtBat ab : atBats) {
            if(!ab.getResult().isBlank() && ab.getResult().contains("Triple")) {
                triples++;
            }
        }
        return triples;
    }

    private int getHomeruns() {
        int homeruns = 0;
        for(AtBat ab : atBats) {
            if(!ab.getResult().isBlank() && ab.getResult().contains("Homerun")) {
                homeruns++;
            }
        }
        return homeruns;
    }

    private int getTotalPitches(boolean fromWindup) {
        int totalPitches = 0;
        for(AtBat ab : atBats) {
            for(Pitch p : ab.getPitches()) {
                if(fromWindup) {
                    if(p.basesEmpty()) {
                        totalPitches++;
                    }
                } else {
                    if(!p.basesEmpty()) {
                        totalPitches++;
                    }
                }
            }
        }
        return totalPitches;
    }

    private int getStrikes(boolean fromWindup) {
        int strikes = 0;
        for(AtBat ab : atBats) {
            for(Pitch pitch : ab.getPitches()) {
                if(pitch.getBallOrStrike() == PitchResult.STRIKE) {
                    boolean emptyBases = pitch.basesEmpty();
                    if(fromWindup && emptyBases) {
                        strikes++;
                    } else if(!fromWindup && !emptyBases) {
                        strikes++;
                    }
                }
            }
        }
        return strikes;
    }

    private int getBalls(boolean fromWindup) {
        int balls = 0;
        for(AtBat ab : atBats) {
            for(Pitch pitch : ab.getPitches()) {
                if(pitch.getBallOrStrike() == PitchResult.BALL) {
                    boolean emptyBases = pitch.basesEmpty();
                    if(fromWindup && emptyBases) {
                        balls++;
                    } else if(!fromWindup && !emptyBases) {
                        balls++;
                    }
                }
            }
        }
        return balls;
    }

    private float getStrikeRatio(boolean fromWindup) {
        int dom = getTotalPitches(fromWindup);
        if(dom == 0) {
            return 0;
        }
        return ((float) getStrikes(fromWindup) * 100 / getTotalPitches(fromWindup));
    }

    private float getOverallStrikeRatio() {
        int dom = getTotalPitches(true) + getTotalPitches(false);
        if(dom == 0) {
            return 0;
        }
        return (((float) (getStrikes(true) + getStrikes(false)) * 100) / dom);
    }

    private int getFPS(boolean fromWindup) {
        int fps = 0;
        for(AtBat ab : atBats) {
            if(ab.getPitches().size() > 0) {
                Pitch firstPitch = ab.getPitches().get(0);
                if(firstPitch.getBallOrStrike() == PitchResult.STRIKE) {
                    boolean emptyBases = ab.getPitches().get(0).basesEmpty();
                    if(fromWindup && emptyBases) {
                        fps++;
                    } else if(!fromWindup && !emptyBases) {
                        fps++;
                    }
                }
            }
        }
        return fps;
    }

    private int getFPB(boolean fromWindup) {
        int fpb = 0;
        for(AtBat ab : atBats) {
            if(ab.getPitches().size() > 0) {
                Pitch firstPitch = ab.getPitches().get(0);
                if(firstPitch.getBallOrStrike() == PitchResult.BALL) {
                    boolean emptyBases = firstPitch.basesEmpty();
                    if(fromWindup && emptyBases) {
                        fpb++;
                    } else if(!fromWindup && !emptyBases) {
                        fpb++;
                    }
                }
            }
        }
        return fpb;
    }

    private float getFPSRatio(boolean fromWindup) {
        int totalFirstPitches = getFPS(fromWindup) + getFPB(fromWindup);
        if(totalFirstPitches == 0) {
            return 0;
        }
        return ((float) getFPS(fromWindup) * 100 / totalFirstPitches);
    }

    private float getOverallFPSRatio() {
        int actualABCount = 0;
        for(AtBat ab : atBats) {
            if(ab.getPitches().size() > 0) {
                actualABCount++;
            }
        }
        if(actualABCount == 0) {
            return 0;
        }
        return (((float) (getFPS(true) + getFPS(false)) * 100) / actualABCount);
    }

    private int getPitchStrikes(boolean fromWindup, int pitch) {
        int strikes = 0;
        for(AtBat ab : atBats) {
            for(Pitch p : ab.getPitches()) {
                if(p.getPitchType() == pitch && p.getBallOrStrike() == PitchResult.STRIKE) {
                    boolean emptyBases = p.basesEmpty();
                    if(fromWindup && emptyBases) {
                        strikes++;
                    } else if(!fromWindup && !emptyBases) {
                        strikes++;
                    }
                }
            }
        }
        return strikes;
    }

    private int getPitchBalls(boolean fromWindup, int pitch) {
        int balls = 0;
        for(AtBat ab : atBats) {
            for(Pitch p : ab.getPitches()) {
                if(p.getPitchType() == pitch && p.getBallOrStrike() == PitchResult.BALL) {
                    boolean emptyBases = p.basesEmpty();
                    if(fromWindup && emptyBases) {
                        balls++;
                    } else if(!fromWindup && !emptyBases) {
                        balls++;
                    }
                }
            }
        }
        return balls;
    }

    private float getPitchStrikeRatio(boolean fromWindup, int pitch) {
        int strikes = getPitchStrikes(fromWindup, pitch);
        int dom = strikes + getPitchBalls(fromWindup, pitch);
        if(dom == 0) {
            return 0;
        }
        return ((float) strikes * 100 / dom);
    }

    private float getOverallPitchStrikeRatio(int pitch) {
        int strikes = getPitchStrikes(true, pitch) + getPitchStrikes(false, pitch);
        int dom = strikes + getPitchBalls(true, pitch) + getPitchBalls(false, pitch);
        if(dom == 0) {
            return 0;
        }
        return ((float) strikes * 100 / dom);
    }

    private int getPitchHits(boolean fromWindup, int pitch) {
        int hits = 0;
        for(AtBat ab : atBats) {
            if(!ab.getResult().isBlank()) {
                Pitch lastPitch = ab.getPitches().get(ab.getPitches().size() - 1);
                if(lastPitch.getPitchType() == pitch) {
                    String result = ab.getResult().split(", ")[0];
                    String[] hit = {"Single", "Double", "Triple", "Homerun"};
                    for(String h : hit) {
                        if(result.equalsIgnoreCase(h)) {
                            boolean emptyBases = lastPitch.basesEmpty();
                            if(fromWindup && emptyBases) {
                                hits++;
                            } else if (!fromWindup && !emptyBases){
                                hits++;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return hits;
    }

    private int getPitchStrikeouts(boolean fromWindup, int pitch) {
        int k = 0;
        for(AtBat ab : atBats) {
            if(!ab.getResult().isBlank()) {
                Pitch lastPitch = ab.getPitches().get(ab.getPitches().size() - 1);
                if(lastPitch.getPitchType() == pitch) {
                    String result = ab.getResult().split(", ")[0];
                    if(result.equalsIgnoreCase("Strikeout")) {
                        boolean emptyBases = lastPitch.basesEmpty();
                        if(fromWindup && emptyBases) {
                            k++;
                        } else if (!fromWindup && !emptyBases){
                            k++;
                        }
                    }
                }
            }
        }
        return k;
    }

    private int getWalks(boolean fromWindup) {
        int walks = 0;
        for(AtBat ab : atBats) {
            if(!ab.getResult().isBlank()) {
                String result = ab.getResult().split(", ")[0];
                if(result.equalsIgnoreCase("Walk")) {
                    boolean emptyBases = ab.getPitches().get(ab.getPitches().size() - 1).basesEmpty();
                    if(fromWindup && emptyBases) {
                        walks++;
                    } else if(!fromWindup && !emptyBases) {
                        walks++;
                    }
                }
            }
        }
        return walks;
    }

    private int getHBPs(boolean fromWindup) {
        int hbp = 0;
        for(AtBat ab : atBats) {
            if(!ab.getResult().isBlank()) {
                String result = ab.getResult().split(", ")[0];
                if(result.equalsIgnoreCase("HBP")) {
                    boolean emptyBases = ab.getPitches().get(ab.getPitches().size() - 1).basesEmpty();
                    if(fromWindup && emptyBases) {
                        hbp++;
                    } else if(!fromWindup && !emptyBases) {
                        hbp++;
                    }
                }
            }
        }
        return hbp;
    }

    private int getGroundBalls(boolean fromWindup) {
        int groundBalls = 0;
        for(AtBat ab : atBats) {
            if(!ab.getResult().isBlank()) {
                String result = ab.getResult().split(", ")[1].toLowerCase();
                if(result.contains("ground")) {
                    boolean emptyBases = ab.getPitches().get(ab.getPitches().size() - 1).basesEmpty();
                    if(fromWindup && emptyBases) {
                        groundBalls++;
                    } else if(!fromWindup && !emptyBases) {
                        groundBalls++;
                    }
                }
            }
        }
        return groundBalls;
    }

    private int getLineDrives(boolean fromWindup) {
        int lineDrives = 0;
        for(AtBat ab : atBats) {
            if(!ab.getResult().isBlank()) {
                String result = ab.getResult().split(", ")[1].toLowerCase();
                if(result.contains("line")) {
                    boolean emptyBases = ab.getPitches().get(ab.getPitches().size() - 1).basesEmpty();
                    if(fromWindup && emptyBases) {
                        lineDrives++;
                    } else if(!fromWindup && !emptyBases) {
                        lineDrives++;
                    }
                }
            }
        }
        return lineDrives;
    }

    private int getFlyBalls(boolean fromWindup) {
        int flyBalls = 0;
        for(AtBat ab : atBats) {
            if(!ab.getResult().isBlank()) {
                String result = ab.getResult().split(", ")[1].toLowerCase();
                if(result.contains("fly")) {
                    boolean emptyBases = ab.getPitches().get(ab.getPitches().size() - 1).basesEmpty();
                    if(fromWindup && emptyBases) {
                        flyBalls++;
                    } else if(!fromWindup && !emptyBases) {
                        flyBalls++;
                    }
                }
            }
        }
        return flyBalls;
    }

    public String export() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, LLL d, yyyy");
        StringBuilder str = new StringBuilder(LocalDate.now().format(dtf) + "\n");
        str.append(name).append(", #").append(number).append(", age ").append(age).append(", who throws ").append(pitchArsenalString()).append(":\n\n");
        str.append("Innings Pitched: ").append(String.valueOf(getInningsPitched()), 0, 3).append("\n");
        str.append("Singles: ").append(getSingles()).append("\n");
        str.append("Doubles: ").append(getDoubles()).append("\n");
        str.append("Triples: ").append(getTriples()).append("\n");
        str.append("Homeruns: ").append(getHomeruns()).append("\n\n");
        str.append(String.format("%-20s|%-10s|%-10s|%-10s", "Statistic", "Overall", "Stretch", "Windup")).append("\n");
        str.append(String.format("%-20s|%-10d|%-10d|%-10d", "Total pitches", getTotalPitches(true) + getTotalPitches(false), getTotalPitches(false), getTotalPitches(true))).append("\n");
        str.append(String.format("%-20s|%-10d|%-10d|%-10d", "Strikes", getStrikes(true) + getStrikes(false), getStrikes(false), getStrikes(true))).append("\n");
        str.append(String.format("%-20s|%-10d|%-10d|%-10d", "Balls", getBalls(true) + getBalls(false), getBalls(false), getBalls(true))).append("\n");
        str.append(String.format("%-20s|%-10.1f|%-10.1f|%-10.1f", "Strike%", getOverallStrikeRatio(), getStrikeRatio(false), getStrikeRatio(true))).append("\n");
        str.append(String.format("%-20s|%-10d|%-10d|%-10d", "FPS", getFPS(true) + getFPS(false), getFPS(false), getFPS(true))).append("\n");
        str.append(String.format("%-20s|%-10d|%-10d|%-10d", "FPB", getFPB(true) + getFPB(false), getFPB(false), getFPB(true))).append("\n");
        str.append(String.format("%-20s|%-10.1f|%-10.1f|%-10.1f", "FPS%", getOverallFPSRatio(), getFPSRatio(false), getFPSRatio(true))).append("\n");
        for(int i = 0; i < arsenal.size(); i++) {
            str.append(String.format("%-20s|%-10d|%-10d|%-10d", arsenal.get(i) + " Strikes", getPitchStrikes(true, i) + getPitchStrikes(false, i), getPitchStrikes(false, i), getPitchStrikes(true, i))).append("\n");
            str.append(String.format("%-20s|%-10d|%-10d|%-10d", arsenal.get(i) + " Balls", getPitchBalls(true, i) + getPitchBalls(false, i), getPitchBalls(false, i), getPitchBalls(true, i))).append("\n");
            str.append(String.format("%-20s|%-10.1f|%-10.1f|%-10.1f", arsenal.get(i) + " Strike%", getOverallPitchStrikeRatio(i), getPitchStrikeRatio(false, i), getPitchStrikeRatio(true, i))).append("\n");
        }
        for(int i = 0; i < arsenal.size(); i++) {
            str.append(String.format("%-20s|%-10d|%-10d|%-10d", arsenal.get(i) + " Hits", getPitchHits(true, i) + getPitchHits(false, i), getPitchHits(false, i), getPitchHits(true, i))).append("\n");
        }
        for(int i = 0; i < arsenal.size(); i++) {
            str.append(String.format("%-20s|%-10d|%-10d|%-10d", arsenal.get(i) + " Ks", getPitchStrikeouts(true, i) + getPitchStrikeouts(false, i), getPitchStrikeouts(false, i), getPitchStrikeouts(true, i))).append("\n");
        }
        str.append(String.format("%-20s|%-10d|%-10d|%-10d", "Walks", getWalks(true) + getWalks(false), getWalks(false), getWalks(true))).append("\n");
        str.append(String.format("%-20s|%-10d|%-10d|%-10d", "HBP", getHBPs(true) + getHBPs(false), getHBPs(false), getHBPs(true))).append("\n");
        str.append(String.format("%-20s|%-10d|%-10d|%-10d", "Ground Balls", getGroundBalls(true) + getGroundBalls(false), getGroundBalls(false), getGroundBalls(true))).append("\n");
        str.append(String.format("%-20s|%-10d|%-10d|%-10d", "Line Drives", getLineDrives(true) + getLineDrives(false), getLineDrives(false), getLineDrives(true))).append("\n");
        str.append(String.format("%-20s|%-10d|%-10d|%-10d", "Fly Balls", getFlyBalls(true) + getFlyBalls(false), getFlyBalls(false), getFlyBalls(true))).append("\n\n");
        str.append("Pitch Log:\n\n");
        if(atBats.size() > 0) {
            for(AtBat ab : atBats) {
                if(ab.getPitches().size() > 0) {
                    str.append("At-bat ").append(atBats.indexOf(ab) + 1).append(":\n");
                    if(ab.getPitches().get(0).isLefty()) {
                        str.append("Batter: Lefty\n");
                    } else {
                        str.append("Batter: Righty\n");
                    }
                    for(Pitch p : ab.getPitches()) {
                        str.append(p.toString(arsenal.get(p.getPitchType()))).append("\n");
                    }
                    String[] result = ab.getResult().split(", ");
                    str.append("Result: ").append(result[0]);
                    if(!result[1].equals("None")) {
                        str.append(", ").append(result[1]);
                    }
                    str.append("\n\n");
                }
            }
        } else {
            str.append("No pitches thrown");
        }
        return str.toString();
    }

    public static Pitcher buildPitcher(String line) {
        String[] info = line.split("\\|");
        String name = info[0];
        int number = Integer.parseInt(info[1]);
        int age = Integer.parseInt(info[2]);
        String[] pitchesArr = info[3].split(", ");
        ArrayList<String> pitches = new ArrayList<>(List.of(pitchesArr));
        return new Pitcher(name, number, age, pitches);
    }

    public String toString() {
        return name + "|" + number + "|" + age + "|" + pitchArsenalString();
    }
}