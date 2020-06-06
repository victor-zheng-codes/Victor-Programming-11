//Main class to run code
public class Main {
    public static void main(String[] args) {

        //Construct a new school called Van Tech.
        School vanTech = new School("Vancouver Technical Secondary School", "2600 East Broadway", "Mr. Moro");
        //Print out the school
        vanTech.toString("School");

        //Add 10 students to student Array list for students
        vanTech.addStudent("Thomas", "Dam", 10);
        vanTech.addStudent("John", "Tam", 11);
        vanTech.addStudent("Jessie", "Stuart", 12);
        vanTech.addStudent("Chris", "Zheng", 9);
        vanTech.addStudent("Tommy", "Luu", 8);
        vanTech.addStudent("Hewwit", "Sampy", 10);
        vanTech.addStudent("Amy", "Lou", 9);
        vanTech.addStudent("Louis", "Charles", 10);
        vanTech.addStudent("Samantha", "Friendly", 11);
        vanTech.addStudent("Abraham", "Lincoln", 11);

        //add 10 teachers to Array List for teachers
        vanTech.addTeacher("Jimmy","Ford","Social Studies");
        vanTech.addTeacher("Addison","Smith","Mathematics");
        vanTech.addTeacher("Isabella","Macdonald","Chemistry");

        //print out the students array list
        vanTech.toString("Students");
        System.out.println(" ");

        //print out the teachers array list
        vanTech.toString("Teachers");
        System.out.println(" ");

        //delete teacher from arrayList location
        vanTech.deleteTeacher(0);
        vanTech.deleteTeacher(0);
        System.out.println(" ");


        //delete student from arraylist location
        vanTech.deleteStudent(0);
        System.out.println(" ");

        //print out the students array list
        vanTech.toString("Students");
        System.out.println(" ");

        //print out the teachers array list
        vanTech.toString("Teachers");
        System.out.println(" ");


    }


}
