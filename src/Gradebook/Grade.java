package Gradebook;

/*
Every Grade, or assignment, has a name, some number of earned points, and some number of the maximum amount of points
possible. This class stores all of that information for every grade. This Grade class extends GradeType to help store
GradeType information.
*/

public class Grade extends GradeType {
    private String name;
    private float points, maxPoints;

    public Grade(String gradeType, float gradeWeight, String parseGrade) {
        super(gradeType, gradeWeight);  // GradeType call
        String[] gradeInfo = parseGrade.split("/");
        setName(gradeInfo[1]);
        String[] grade = gradeInfo[2].split("-");
        setPoints(Float.parseFloat(grade[0]));
        setMaxPoints(Float.parseFloat(grade[1]));
    }

    public Grade(String name, String gradeType, float weight) {
        super(gradeType, weight);  // GradeType call
        setName(name);
        setPoints(0);
        setMaxPoints(0);
    }

    // default constructor
    public Grade() {
        super();  // default GradeType call
        setName("");
        setPoints(0);
        setMaxPoints(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public float getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(float maxPoints) {
        this.maxPoints = maxPoints;
    }

    // This method returns the decimal (float) representation of the grade
    public float getGrade() {
        return maxPoints > 0 ? points * 100 / maxPoints : 0;  // return 0 if maxPoints is 0 to avoid ArithmeticError
    }

    // This method returns the formatted version of the grade for data storage purposes
    public String toString() {
        return String.format("%s/%s/%.3f-%.3f", getGradeType(), getName(), getPoints(), getMaxPoints());
    }
}