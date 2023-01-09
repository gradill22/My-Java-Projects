package Gradebook;

import java.util.ArrayList;

public class Class {
    private final ArrayList<Grade> grades = new ArrayList<>();
    private final ArrayList<GradeType> gradeTypes = new ArrayList<>();
    private String name;
    private int credits;

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

    public float numberGrade() {
        float sumGrades = 0;
        for(Grade g : grades) {
            float grade = g.getGrade();
            float weight = g.getWeight();
            int count = gradeTypesCount(g.getGradeType());
            sumGrades += grade * weight / count;
        }
        return sumGrades;
    }

    public String letterGrade() {
        if(grades.size() == 0) {
            return "N/A";
        }
        float grade = numberGrade();
        if(grade >= 93) {
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
        } else if(grade > 0) {
            return "F";
        }
        return "N/A";
    }

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

    public void setGrades(Grade[] grades) {
        for(Grade g : grades) {
            if(g != null) {
                this.grades.add(g);
            }
        }
    }

    public void setGradeTypes(GradeType[] gradeTypes) {
        for(GradeType gt : gradeTypes) {
            if(gt != null) {
                this.gradeTypes.add(gt);
            }
        }
    }

    public void addGrade(Grade newGrade) {
        grades.add(newGrade);
    }

    public void addGradeType(GradeType newGradeType) {
        gradeTypes.add(newGradeType);
    }

    public void removeGradeType(String gradeType) {
        gradeTypes.remove(getGradeTypeIndex(gradeType));
    }

    public Grade getGrade(int index) {
        return grades.get(index);
    }

    public ArrayList<Grade> getGrades() {
        return this.grades;
    }

    public GradeType getGradeType(int index) {
        return gradeTypes.get(index);
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

    public String formattedGrade() {
        return "%s (%.2f)".formatted(letterGrade(), numberGrade());
    }

    public int getGradeTypeIndex(String gradeType) {
        for(int i = 0; i < gradeTypes.size(); i++) {
            if(gradeTypes.get(i).getGradeType().equals(gradeType)) {
                return i;
            }
        }
        return -1;
    }

    private int gradeTypesCount(String gradeType) {
        int sum = 0;
        for(Grade g : grades) {
            sum += g.getGradeType().equals(gradeType) ? 1 : 0;
        }
        return sum;
    }

    public String[] getGradeTypes() {
        String[] names = new String[gradeTypes.size()];
        for(int i = 0; i < names.length; i++) {
            names[i] = gradeTypes.get(i).getGradeType();
        }
        return names;
    }

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