package PitchCharting;

public class Pitch {
    private final boolean firstBase, secondBase, thirdBase, lefty;
    private final int location, pitchType;
    private final boolean lastPitch, foulBall;

    public Pitch(int location, boolean firstBase, boolean secondBase, boolean thirdBase, int pitchType, boolean lefty, boolean foulBall, boolean lastPitch) {
        this.location = location;
        this.firstBase = firstBase;
        this.secondBase = secondBase;
        this.thirdBase = thirdBase;
        this.pitchType = pitchType;
        this.lefty = lefty;
        this.foulBall = foulBall;
        this.lastPitch = lastPitch;
    }

    public PitchAltitude getHighOrLow() {
        if(location <= 9) {
            return PitchAltitude.HIGH;
        } else if(location >= 15) {
            return PitchAltitude.LOW;
        }
        return PitchAltitude.MIDDLE;
    }

    public PitchLongitude getInsideOrOutside() {
        if(location % 5 < 2) {
            if(lefty) {
                return PitchLongitude.OUTSIDE;
            }
            return PitchLongitude.INSIDE;
        } else if(location % 5 > 2) {
            if(lefty) {
                return PitchLongitude.INSIDE;
            }
            return PitchLongitude.OUTSIDE;
        }
        return PitchLongitude.MIDDLE;
    }

    public PitchResult getBallOrStrike() {
        int[] strikeLocations = {6, 11, 16};
        for(int num : strikeLocations) {
            if(location >= num && location <= (num + 2)) {
                return PitchResult.STRIKE;
            }
        }
        return PitchResult.BALL;
    }

    public boolean firstBase() {
        return firstBase;
    }

    public boolean secondBase() {
        return secondBase;
    }

    public boolean thirdBase() {
        return thirdBase;
    }

    public int getPitchType() {
        return pitchType;
    }

    public boolean isLefty() {
        return lefty;
    }

    public String toString(String pitch) {
        String str = getHighOrLow() + " " + getInsideOrOutside() + " " + pitch + " for a " + getBallOrStrike();
        if(foulBall) {
            str += " on a foul ball";
        }
        return str;
    }

    public boolean isLastPitch() {
        return lastPitch;
    }

    public boolean isFoulBall() {
        return foulBall;
    }

    public boolean basesEmpty() {
        return !firstBase && !secondBase && !thirdBase;
    }
}

enum PitchResult {
    BALL, STRIKE
}

enum PitchAltitude {
    LOW, MIDDLE, HIGH
}

enum PitchLongitude {
    INSIDE, MIDDLE, OUTSIDE
}