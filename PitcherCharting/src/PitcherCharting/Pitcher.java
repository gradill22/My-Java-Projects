package PitcherCharting;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Pitcher {
    private String name;
    private int number;
    private int age;
    private String team;
    private final ArrayList<String> pitchTypes;
    private final ArrayList<Pitch> pitches;

    public Pitcher(String name, int number, int age, String team, ArrayList<String> pitch) {
        this.name = name;
        this.number = number;
        this.age = age;
        this.team = team;
        pitches = new ArrayList<>();
        pitchTypes = new ArrayList<>();
        pitchTypes.addAll(pitch);
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

    public String getTeam() {
        return team;
    }

    public String getPitchType(int i) {
        return pitchTypes.get(i);
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void addPitch(Pitch pitch) {
        pitches.add(pitch);
    }

    public int getPitchTypesCount() {
        return pitchTypes.size();
    }

    public String pitchTypesString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < pitchTypes.size() - 1; i++) {
            str.append(pitchTypes.get(i)).append(", ");
        }
        str.append(pitchTypes.get(pitchTypes.size() - 1));
        return str.toString();
    }

    public ArrayList<String> getPitchTypesList() {
        return pitchTypes;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("0.0");
        StringBuilder str = new StringBuilder();
        if(pitches.size() > 0) {
            str.append("For #").append(getNumber()).append(" ").append(getName()).append(" (").append(getAge()).append(", ").append(getTeam()).append("):");
            double strike = 0;
            for(int i = 0; i < pitchTypes.size(); i++) {
                double count = 0;
                double pitchStrike = 0;
                for(Pitch p : pitches) {
                    if(p.getPitchType() == i) {
                        count++;
                        if(p.getBallOrStrike().equals(PitchResult.Strike)) {
                            pitchStrike++;
                            strike++;
                        }
                    }
                }
                if(count > 0) {
                    str.append("\n").append(pitchTypes.get(i)).append("'s thrown: ").append((int) count).append(" (").append(df.format(pitchStrike * 100 / count)).append("% strikes)");
                }
            }
            str.append("\n\nStrike percentage: ").append(df.format(strike * 100 / pitches.size())).append("% (").append((int) strike).append("/").append(pitches.size()).append(")\n");
            str.append("\nPitch Log:");
            for(int i = 0; i < pitches.size(); i++) {
                str.append("\n").append(pitches.get(i).getHighOrLow().toString()).append("-").append(pitches.get(i)
                        .getInsideOrOutside()).append(" ").append(pitchTypes.get(pitches.get(i).getPitchType())).append(" for a ")
                        .append(pitches.get(i).getBallOrStrike()).append(basesString(i));
            }
        }
        return str.toString();
    }

    private String basesString(int i) {
        boolean first = pitches.get(i).getFirstBase();
        boolean second = pitches.get(i).getSecondBase();
        boolean third = pitches.get(i).getThirdBase();
        if(first && !second && !third) {
            return " with a runner on first";
        } else if(!first && second && !third) {
            return " with a runner on second";
        } else if(!first && !second && third) {
            return " with a runner on third";
        } else if(first && second && !third) {
            return " with runners on first and second";
        } else if(!first && second) {
            return " with runners on second and third";
        } else if(first && !second) {
            return " with runners on first and third";
        } else if(first) {
            return " with bases loaded";
        } else {
            return "";
        }
    }
}