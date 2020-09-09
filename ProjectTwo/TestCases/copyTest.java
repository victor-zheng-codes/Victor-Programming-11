import org.junit.Before;
import org.junit.Test;
import sample.CopyFile;
import java.io.*;
import static org.junit.Assert.*;

public class copyTest {

    @Before
    public void setup(){
        //clear the file that we are reading by deleting it
        File myFile = new File("src\\sample\\OtherFiles\\copyFile.txt");
        // Delete this file by running an if statement. myFile.delete() returns a Boolean
        if (myFile.delete()) {
            System.out.println("Deleted the file: " + myFile.getName());
        } else {
            //This means that the file was never there, so we can still create it
            System.out.println("Failed to delete the file.");
        }
        // By doing the above code, either way, we end up without a file called copyFile.
    }

    @Test
    public void copyTesting() throws IOException {
        //Since the file should have been either deleted, or was not there in the first place, we can create a new file
        File newFile = new File("src\\sample\\OtherFiles\\copyFile.txt");

        // copy a file that has 3 people's names to a file named copyFile
        CopyFile.copy("src\\sample\\OtherFiles\\copyFile.txt", "src\\sample\\OtherFiles\\testCopyFile.txt");

        // read a pre created file that contains people.
        FileReader fr = new FileReader("src\\sample\\OtherFiles\\testCopyFile.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        int counter = 0;

        //Read each line in the file and count the number of lines
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            counter++;
            System.out.println(counter);

            if (counter == 1) {
                // Assert true that the first line is "Barack Obama". Assume that the rest must be true too
                assertTrue(line.equals("Barack Obama"));
            }
        }
        // assert equals that there are 3 people in thet file
        assertEquals(3, counter);
    }

}
