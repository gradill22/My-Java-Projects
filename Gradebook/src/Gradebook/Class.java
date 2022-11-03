package Gradebook;

import java.util.ArrayList;
import java.util.Arrays;

public class Class {
    private final ArrayList<Grade> grades = new ArrayList<>();
    private final ArrayList<String> gradeTypes = new ArrayList<>();
    private String name;
    private int credits;

    public Class(Grade[] grades, String[] gradeTypes, String name, int credits) {
        this.grades.addAll(Arrays.asList(grades));
        this.gradeTypes.addAll(Arrays.asList(gradeTypes));
        this.name = name;
        this.credits = credits;
    }

    public Class(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    public Class(String readClass) {
        String[] info = readClass.split("\\|");
        this.name = info[0];
        this.credits = Integer.parseInt(info[1]);
        for(int i = 2; i < info.length; i++) {
            String[] gradeInfo = info[i].split(":");
            System.out.println(Arrays.toString(gradeInfo));
            this.gradeTypes.add(gradeInfo[0]);
            for(int j = 1; j < gradeInfo.length; j++) {
                this.grades.add(new Grade(gradeInfo[j]));
            }
        }
        for(Grade g: grades) {
            System.out.println(g);
        }
    }

    public Class() {
        this.name = "New Class";
        this.credits = 3;
    }

    public String letterGrade() {
        float grade = numberGrade();
        if (grade >= 90) {
            return "A";
        } else if (grade >= 80) {
            return "B";
        } else if (grade >= 70) {
            return "C";
        } else if (grade >= 60) {
            return "D";
        } else if (grade < 60) {
            return "F";
        }
        return "N/A";
    }

    public float numberGrade() {
        float grade = 0;
        for(Grade g: grades) {
            float sum = 0;
            for(float f: g.getGrades()) {
                sum += f;
            }
            grade += sum * g.getWeight();
        }
        return grade * 100;
    }

    public void addToGrade(int index, float points, float maxPoints) {
        this.grades.get(index).addGrade(points, maxPoints);
    }

    public void addNewGrade(String newGradeType, float newGradeWeight) {
        this.gradeTypes.add(newGradeType);
        this.grades.add(new Grade("New assignment", newGradeWeight));
    }

    public Grade getGrade(int index) {
        return this.grades.get(index);
    }

    public ArrayList<Grade> getGrades() {
        return this.grades;
    }

    public Grade getGrade(String gradeName) {
        return grades.get(gradeTypes.indexOf(gradeName));
    }

    public String getGradeName(int index) {
        return this.gradeTypes.get(index);
    }

    public String getGradeName(Grade g) {
        return this.gradeTypes.get(this.grades.indexOf(g));
    }

    public void setGradeName(int index, String newName) {
        this.gradeTypes.set(index, newName);
    }

    public String getClassName() {
        return this.name;
    }

    public void setClassName(String newClassName) {
        this.name = newClassName;
    }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int newCredits) {
        this.credits = newCredits;
    }

    public String[] gradeNames() {
        String[] strings = new String[this.gradeTypes.size()];
        for(int i = 0; i < this.gradeTypes.size(); i++) {
            strings[i] = this.gradeTypes.get(i);
        }
        return strings;
    }

    public String toString() {
        StringBuilder str = new StringBuilder(this.name + "|" + this.credits);
        for(int i = 0; i < this.gradeTypes.size(); i++) {
            str.append("|").append(this.gradeTypes.get(i)).append(this.grades.get(i).toString());
        }
        str.append("\n");
        return str.toString();
    }
}