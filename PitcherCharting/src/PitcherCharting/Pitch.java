package PitcherCharting;

public class Pitch {
    private PitchResult result;
    private PitchAltitude altitude;
    private PitchLongitude longitude;
    private boolean firstBase;
    private boolean secondBase;
    private boolean thirdBase;
    private int pitchType;

    public Pitch() {
        result = PitchResult.Strike;
        altitude = PitchAltitude.Middle;
        longitude = PitchLongitude.Middle;
        firstBase = false;
        secondBase = false;
        thirdBase = false;
        pitchType = 0;
    }

    public PitchResult getBallOrStrike() {
        return result;
    }

    public PitchAltitude getHighOrLow() {
        return altitude;
    }

    public PitchLongitude getInsideOrOutside() {
        return longitude;
    }

    public boolean getFirstBase() {
        return firstBase;
    }

    public boolean getSecondBase() {
        return secondBase;
    }

    public boolean getThirdBase() {
        return thirdBase;
    }

    public int getPitchType() {
        return pitchType;
    }

    public void setBallOrStrike(PitchResult result) {
        this.result = result;
    }

    public void setHighOrLow(PitchAltitude altitude) {
        this.altitude = altitude;
    }

    public void setInsideOrOutside(PitchLongitude insideOrOutside) {
        longitude = insideOrOutside;
    }

    public void setFirstBase(boolean first) {
        firstBase = first;
    }

    public void setSecondBase(boolean second) {
        secondBase = second;
    }

    public void setThirdBase(boolean third) {
        thirdBase = third;
    }

    public void setPitchType(int pitchType) {
        this.pitchType = pitchType;
    }
}

enum PitchResult {
    Ball, Strike
}

enum PitchAltitude {
    Low, Middle, High
}

enum PitchLongitude {
    Inside, Middle, Outside
}