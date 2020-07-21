package sample;

public class Friend {
    private String name;
    private String skills;
    private String traits;
    private int age;
    private int grade;

    Friend(String name, int age, int grade, String skills, String traits){
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.skills = skills;
        this.traits = traits;
    }


    //Getter and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getTraits() {
        return traits;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


    //To string method
    public String toString(){
        return name;
    }

}

