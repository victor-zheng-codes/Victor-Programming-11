//This class creates a new student.
public class Student {
    private String firstName;
    private String lastName;
    private int grade;
    static int idNum;
    private int studentCount = 1000; //Start at 1000 to keep all student IDs to 4 digits

    //default constructor
    Student(){
        firstName = "";
        lastName = "";
        grade = 0;
        idNum = studentCount; //each student is given a new id number every time
        studentCount ++;
    }

    Student(String firstName, String lastName, int grade){
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.idNum = studentCount;
        studentCount ++;
    }


    //getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    //If someone wants to print out the student, then this will override the normal toString method
    public String toString(){
        return "Name: " + this.firstName + " " + this.lastName + "\t" + "Grade: " + this.grade;
    }

}
