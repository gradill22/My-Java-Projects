package Gradebook;

import java.util.ArrayList;

public class Grade {
    private float weight;
    private String name;
    private final ArrayList<Float> points = new ArrayList<>();
    private final ArrayList<Float> maxPoints = new ArrayList<>();

    public Grade(String name, float weight, float[] points, float[] maxPoints) {
        this.name = name;
        this.weight = weight;
        for(float n: points) {
            this.points.add(n);
        }
        for(float n: maxPoints) {
            this.maxPoints.add(n);
        }
    }

    public Grade(String readGrade) {
        String[] gradeInfo = readGrade.split("/");
        this.name = gradeInfo[0];
        this.weight = Float.parseFloat(gradeInfo[1]);
        for(int i = 2; i < gradeInfo.length; i++) {
            String[] grade = gradeInfo[i].split("-");
            points.add(Float.valueOf(grade[0]));
            maxPoints.add(Float.valueOf(grade[1]));
        }
    }

    public Grade(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public float getGrade(int index) {
        return this.points.get(index) / this.maxPoints.get(index);
    }

    public float getMaxPoints(int index) {
        return this.maxPoints.get(index);
    }

    public float[] getGrades() {
        float[] grades = new float[points.size()];
        for(int i = 0; i < this.points.size(); i++) {
            grades[i] = this.points.get(i) / this.maxPoints.get(i);
        }
        return grades;
    }

    public void setGrade(int index, float points, float maxPoints) {
        this.points.set(index, points);
        this.maxPoints.set(index, maxPoints);
    }

    public void addGrade(float points, float maxPoints) {
        this.points.add(points);
        this.maxPoints.add(maxPoints);
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float newWeight) {
        this.weight = newWeight;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        StringBuilder str = new StringBuilder(":" + this.name + "/" + this.weight);
        for(int i = 0; i < points.size(); i++) {
            str.append("/").append(points.get(i)).append("-").append(maxPoints.get(i));
        }
        return str.toString();
    }
}