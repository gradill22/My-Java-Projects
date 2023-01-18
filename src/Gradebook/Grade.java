package Gradebook;

public class Grade extends GradeType {
    private String name;
    private float points, maxPoints;

    public Grade(String gradeType, float gradeWeight, String parseGrade) {
        super(gradeType, gradeWeight);
        String[] gradeInfo = parseGrade.split("/");
        setName(gradeInfo[1]);
        String[] grade = gradeInfo[2].split("-");
        setPoints(Float.parseFloat(grade[0]));
        setMaxPoints(Float.parseFloat(grade[1]));
    }

    public Grade(String name, String gradeType, float weight) {
        super(gradeType, weight);
        setName(name);
        setPoints(0);
        setMaxPoints(0);
    }

    public Grade() {
        super("", 100);
        setName("");
        setPoints(0);
        setMaxPoints(0);
    }

    public float getPoints() {
        return points;
    }

    public float getMaxPoints() {
        return maxPoints;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public void setMaxPoints(float maxPoints) {
        this.maxPoints = maxPoints;
    }

    public float getGrade() {
        return maxPoints > 0 ? points * 100 / maxPoints : 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("%s/%s/%.3f-%.3f", getGradeType(), getName(), getPoints(), getMaxPoints());
    }
}