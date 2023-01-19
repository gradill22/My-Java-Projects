package Gradebook;

import java.util.ArrayList;

/*
Every class has a name, some number of credits, grades, and gradeTypes that have a certain name and weight. This keeps
track of all of those things and allows the program to get key information from this Class class.
*/

public class Class {
    private final ArrayList<Grade> grades = new ArrayList<>();  // ArrayList of class grades
    private final ArrayList<GradeType> gradeTypes = new ArrayList<>();  // ArrayList of class gradeTypes
    private String name;  // class name
    private int credits;  // number of class credits

    public Class(Grade[] grades, GradeType[] gradeTypes, String name, int credits) {
        setGradeTypes(gradeTypes);
        setGrades(grades);
        setClassName(name);
        setCredits(credits);
    }

    public Class(String name, int credits) {
        setClassName(name);
        setCredits(credits);
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

    public Grade getGrade(int index) {
        return grades.size() > 0 ? grades.get(index) : new Grade();
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public ArrayList<Grade> getGrades() {
        return this.grades;
    }

    public void setGrades(Grade[] grades) {
        while(this.grades.size() > 0) {  // remove all elements
            this.grades.remove(0);
        }
        for(Grade g : grades) {  // add all the elements from given parameter
            if(g != null) {
                this.grades.add(g);
            }
        }
    }

    public GradeType getGradeType(int index) {
        return gradeTypes.size() > 0 ? gradeTypes.get(index) : new GradeType();
    }

    public void removeGradeType(String gradeType) {
        if(getGradeTypeIndex(gradeType) == -1) {  // stop function if the gradeType isn't in the gradeTypes list
            return;
        }
        gradeTypes.remove(getGradeTypeIndex(gradeType));
    }

    public void addGradeType(GradeType newGradeType) {
        gradeTypes.add(newGradeType);
    }

    // This returns a String list of all the gradeTypes names
    public String[] getGradeTypes() {
        String[] names = new String[gradeTypes.size()];
        for(int i = 0; i < names.length; i++) {
            names[i] = gradeTypes.get(i).formattedGradeType();
        }
        return names;
    }

    public void setGradeTypes(GradeType[] gradeTypes) {
        while(this.gradeTypes.size() > 0) {  // remove all elements
            this.gradeTypes.remove(0);
        }
        for(GradeType gt : gradeTypes) {  // add all the elements from given parameter
            if(gt != null) {
                this.gradeTypes.add(gt);
            }
        }
    }

    // This returns the decimal (float) representation of the class' overall grade
    private float numberGrade() {
        if(grades.size() == 0) {  // if there are no grades, just return -1
            return -1;
        }
        float sumGrades = 0;
        float sumWeights = 0;
        float grade, weight, count;
        for(Grade g : grades) {
            grade = g.getGrade();
            weight = g.getWeight();
            count = gradeTypesCount(g.getGradeType());
            sumGrades += grade * weight / count;
            sumWeights += weight / count;
        }
        return sumWeights > 0 ? sumGrades / sumWeights : 0;  // prevents dividing by 0, throwing ArithmeticError
    }

    // This returns the letter (String) representation of a class' overall grade
    private String letterGrade() {
        float grade = numberGrade();
        if(grade > 100) {
            return "A+";
        } else if(grade >= 93) {
            return "A";
        } else if(grade >= 90) {
            return "A-";
        } else if(grade >= 87) {
            return "B+";
        } else if(grade >= 83) {
            return "B";
        } else if(grade >= 80) {
            return "B-";
        } else if(grade >= 77) {
            return "C+";
        } else if(grade >= 73) {
            return "C";
        } else if(grade >= 70) {
            return "C-";
        } else if(grade >= 67) {
            return "D+";
        } else if(grade >= 65) {
            return "D";
        } else if(grade >= 0) {
            return "F";
        }
        return "N/A";
    }

    // This returns the GPA score of the class
    public float GPAScore() {
        float grade = numberGrade();
        if(grade >= 93) {
            return 4.0f;
        } else if(grade >= 90) {
            return 3.7f;
        } else if(grade >= 87) {
            return 3.3f;
        } else if(grade >= 83) {
            return 3.0f;
        } else if(grade >= 80) {
            return 2.7f;
        } else if(grade >= 77) {
            return 2.3f;
        } else if(grade >= 73) {
            return 2.0f;
        } else if(grade >= 70) {
            return 1.7f;
        } else if(grade >= 67) {
            return 1.3f;
        } else if(grade >= 65) {
            return 1.0f;
        }
        return 0.0f;
    }

    // This returns the proper string representation of the class' overall grade
    public String formattedGrade() {
        return String.format("%s (%.2f)", letterGrade(), Math.max(numberGrade(), 0));
    }

    // This returns the index of input String gradeType in the gradeTypes ArrayList
    public int getGradeTypeIndex(String gradeType) {
        for(int i = 0; i < gradeTypes.size(); i++) {
            if(gradeTypes.get(i).getGradeType().equals(gradeType)) {
                return i;
            }
        }
        return -1;
    }

    // This returns the number of times the input String gradeType is found in the gradeTypes ArrayList
    private int gradeTypesCount(String gradeType) {
        int gradeTypeIndex = getGradeTypeIndex(gradeType);
        if(gradeTypeIndex == -1) {  // if this gradeType isn't in gradeTypes, stop and return 0
            return 0;
        }
        int count = 1;
        for(int i = gradeTypeIndex + 1; i < gradeTypes.size(); i++) {
            count += gradeTypes.get(i).getGradeType().equals(gradeType) ? 1 : 0;
        }
        return count;
    }

    // This returns the string representation of the class for data saving purposes
    public String toString() {
        StringBuilder str = new StringBuilder(getClassName() + "|" + getCredits() + "\n");
        str.append(gradeTypes.size()).append("\n");
        for(GradeType gradeType : gradeTypes) {
            str.append(gradeType.toString()).append("\n");
        }
        str.append(grades.size()).append("\n");
        for(Grade grade : grades) {
            str.append(grade.toString()).append("\n");
        }
        return str.toString();
    }
}