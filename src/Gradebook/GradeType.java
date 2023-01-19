package Gradebook;

/*
Every class has a set grading scale with grade types and their weights. This class is used to assist in storing that
information and allowing valid GradeTypes.
*/

public class GradeType {
    private String gradeType;
    private float weight;

    public GradeType(String name, float weight) {
        setGradeType(name);
        setWeight(weight);
    }

    public GradeType() {
        setGradeType("");
        setWeight(0);
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        weight = Math.max(weight, 0);  // to prevent negative weights
        this.weight = weight >= 0 && weight < 1 ? weight * 100 : weight;  // allows percentage or decimal version
    }

    // This method returns the formatted grade type to show both the name and the weight of the GradeType
    public String formattedGradeType() {
        char percent = '%';
        return String.format("%s (%.1f%c)", gradeType, weight, percent);
    }

    // This method returns the String representation of the GradeType for data storage purposes
    public String toString() {
        return String.format("%s/%.2f", getGradeType(), getWeight());
    }
}