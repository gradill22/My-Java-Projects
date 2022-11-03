package Animals;

public class Elephant {
    private String name; // name of elephant
    private int age; // age of elephant
    private boolean sick; // health of elephant
    private double weight; // weight of elephant

    public Elephant() { // default elephant constructor
        name = "Elephant"; // default name
        age = 0; // default age
        sick = false; // default health
        weight = 1000.0; // default weight
    }

    public Elephant(String name, int age, boolean sick, double weight) { // custom elephant constructor
        this.name = name; // sets name
        this.age = age; // sets age
        this.sick = sick; // sets health
        this.weight = weight; // sets weight
    }

    public String getName() { // gets name of elephant
        return name;
    }

    public int getAge() { // gets age of elephant
        return age;
    }

    public boolean getSick() { // returns health of elephant
        return sick;
    }

    public double getWeight() { // returns weight of elephant
        return weight;
    }

    public void setName(String name) { // sets new name for elephant
        this.name = name;
    }

    public void setAge(int age) { // sets new age for elephant
        this.age = age;
    }

    public void setSick(boolean sick) { // sets new health of elephant
        this.sick = sick;
    }

    public void setWeight(double weight) { // sets new weight of elephant
        this.weight = weight;
    }

    public String toString() { // returns elephant object in string form
        return "name = " + name + "; age = " + age + "; sick = " + sick + "; weight = " + weight;
    }
}