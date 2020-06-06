//This class creates a new teacher. One needs the teacher's firstName, lastName, and what subject they teach
public class Teacher {
    private String firstName;
    private String lastName;
    private String subject;

    //default constructor
    Teacher(){
        firstName = "";
        lastName = "";
        subject = "";

    }
    //constructor for the teacher
    Teacher(String firstName, String lastName, String subject){
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    //If someone wants to print out the student, then this will override the normal toString method
    public String toString(){
        return "Name: " + this.firstName + " " + this.lastName + "\t" + "Subject: " + this.subject;
    }

}
