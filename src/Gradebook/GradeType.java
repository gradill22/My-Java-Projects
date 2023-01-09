package Gradebook;

public class GradeType {
    private String gradeType;
    private float weight;

    public GradeType(String name, float weight) {
        setGradeType(name);
        setWeight(weight);
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
        weight = weight < 0 ? 0 : weight;
        this.weight = weight >= 0 && weight < 1 ? weight * 100 : weight;
    }

    public String toString() {
        return String.format("%s/%.2f", getGradeType(), getWeight());
    }
}