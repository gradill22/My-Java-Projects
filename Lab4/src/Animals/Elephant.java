package Animals;

public class Elephant {
    private String name;
    private int age;
    private boolean sick;
    private double weight;

    public Elephant() {
        name = "Elephant";
        age = 20;
        sick = false;
        weight = 1000.0;
    }

    public Elephant(String name, int age, boolean sick, double weight) {
        this.name = name;
        this.age = age;
        this.sick = sick;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean getSick() {
        return sick;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSick(boolean sick) {
        this.sick = sick;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String toString() {
        return "name = " + name + "; age = " + age + "; sick = " + sick + "; weight = " + weight;
    }
}