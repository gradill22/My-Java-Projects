package PitchCharting;

import java.util.ArrayList;

public class AtBat {
    private final ArrayList<Pitch> pitches;
    private String result;

    public AtBat() {
        pitches = new ArrayList<>();
        result = "No result";
    }

    public ArrayList<Pitch> getPitches() {
        return pitches;
    }

    public void addPitch(Pitch pitch) {
        pitches.add(pitch);
    }

    public String getCount() {
        int strikes = 0;
        int balls = 0;
        for(Pitch p : pitches) {
            if(p.getBallOrStrike() == PitchResult.BALL) {
                balls++;
            } else if(strikes < 2){
                strikes++;
            }
        }
        return balls + "-" + strikes;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}