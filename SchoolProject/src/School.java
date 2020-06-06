import java.util.ArrayList;

//This class if for the school itself. It holds the methods for adding, deleting, and showing students and teachers.

public class School {
    //These are the two array list
    ArrayList<Teacher> teachers = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    //Not sure what to do with this, as we never use it
    ArrayList<String> courses = new ArrayList<>();


    //Setting private fields for the school class. Can set what the school name is, the school location, and the school principal
    private String schoolName;
    private String schoolLocation;
    private String schoolPrincipal;

    // default constructor
    School(){
        schoolName = "";
        schoolLocation = "";
        schoolPrincipal = "";
    }

    //school constructor. Enables user to create multiple schools if they wish
    School(String schoolName, String schoolLocation, String schoolPrincipal){
        this.schoolName = schoolName;
        this.schoolLocation = schoolLocation;
        this.schoolPrincipal = schoolPrincipal;
    }

    //getters and setters for examples

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolLocation() {
        return schoolLocation;
    }

    public void setSchoolLocation(String schoolLocation) {
        this.schoolLocation = schoolLocation;
    }

    public String getSchoolPrincipal() {
        return schoolPrincipal;
    }

    public void setSchoolPrincipal(String schoolPrincipal) {
        this.schoolPrincipal = schoolPrincipal;
    }


    //methods for:

    //Adding new teacher by creating a new teacher from teacher class
    public void addTeacher(String firstName, String lastName, String subject){
        Teacher newTeacher = (new Teacher(firstName, lastName, subject));
        teachers.add(newTeacher);
    }

    //Adding student by creating a new student from student class
    public void addStudent(String firstName, String lastName, int grade){
        Student newStudent = new Student(firstName, lastName, grade);
        students.add(newStudent);

    }

    //Deleting teacher from an integer location. Integer needs to be within the size of the arrayList.
    public void deleteTeacher(int location) {
        if(location > teachers.size()){
            System.out.println("Location too large for ArrayList");
        }
        else{
            System.out.println("Removing this teacher: " + teachers.get(location));
            teachers.remove(location);

        }
    }

    //This is for deleting student from an integer location.  Integer needs to be within the size of the arrayList.
    public void deleteStudent(int location){
        if(location > students.size()){
            System.out.println("Location too large for ArrayList");
        }
        else{
            System.out.println("Removing this student: " + students.get(location));
            students.remove(location);

        }
    }
    //For showing students, teachers, courses (if applicable), and school. User needs to input correct string
    public void toString(String name){
        //Print out Students arrayList
        if(name.equals("Students")) {
            for (int i = 0; i < students.size(); i++) {
                System.out.println(students.get(i));

            }
        }
        //Print out Teachers arrayList
        else if(name.equals("Teachers")){
            for (int i = 0; i < teachers.size(); i++) {
                System.out.println(teachers.get(i));
            }
        }
        //Don't really need this because we don't ever use it
        else if(name.equals("Courses")){
            for (int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i));
            }
        }
        //Print out the school
        else if(name.equals("School")){
            System.out.println(this.schoolName + "\t" + this.schoolLocation + "\t" + this.schoolPrincipal);
        }
        //If user does not input correct string, then will print error
        else{
            System.out.println("Incorrect input");
        }

    }

}
