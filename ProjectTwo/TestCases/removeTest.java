//Not in use

import org.junit.Before;
import org.junit.Test;
import sample.RemovePlant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class removeTest {

    @Before
    public void setup(){

    }

    @Test
    public void removePlantTest() throws IOException {
        //check the current size of the plantsList
        FileReader fr = new FileReader("src\\sample\\OtherFiles\\removalTest.txt");
        BufferedReader br = new BufferedReader(fr);
        int counter = 0;
        String line;
        String removalLine = "";
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            counter ++;

            if(counter == 1){
                // We will try to delete the first line
                removalLine = line;
            }
        }

        RemovePlant.removePlant("src\\sample\\OtherFiles\\removalTest.txt", removalLine);

        /* Rerun the FileReader to determine if the counter has decreased by 1
           This is to determine if one line has been removed and that the correct line has been removed
        */
        //check the current size of the plantsList
        FileReader newfr = new FileReader("src\\sample\\OtherFiles\\removalTest.txt");
        BufferedReader newbr = new BufferedReader(newfr);
        int newCounter = 0;
        String newLine;
        String compareLine = "";

        while ((newLine = newbr.readLine()) != null) {
            System.out.println("newLine is: " + newLine);
            newCounter ++;

            if(newCounter == 1){
                compareLine = newLine;
                // Check that the new line is different than the removed line
                assertFalse(compareLine.equals(removalLine));
            }
        }
        // Check that the new counter is one line less than the old counter
        assertEquals(counter, (newCounter + 1));
    }
}
