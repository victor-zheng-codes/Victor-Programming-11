import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sample.InitiatePlant;
import sample.RemovePlant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class RemovalTests {

    @Before
    public void setup() throws IOException{
        System.out.println("Running Removal Tests");

        // Initialize a new plant so that we can delete them in the tests.
        InitiatePlant newPlant = new InitiatePlant("TestPlant", "02-29-2021", "09-21-2020", "Needs watering");
        newPlant.writeToFile("TestPlant.txt");
        newPlant.writeToGrowList();
        newPlant.writeToHarvestList();
    }

    @Test
    // Test removePlant method, along with the removePermanentlyCheck method by deleting from the growList
    public void removePlantFromGrowTest() throws IOException {
        // Since the file should be in the growList, we can test that this file is there by testing the Boolean method removePermanentlyCheck.
        assertFalse(RemovePlant.removePermanentlyCheck("TestPlant"));

        // Begin a new file reader for the growList
        FileReader fr = new FileReader("growList.txt");
        BufferedReader br = new BufferedReader(fr);
        int counter = 0;
        String line;
        // Go through each line, determining the amount of lines in the file
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            // Add one to the counter each time. Determine how many lines in the file
            counter ++;
            }

        // Remove the TestPlant from the growList
        String removalLine = "TestPlant";
        RemovePlant.removePlant("growList.txt", removalLine);

        // Rerun the FileReader to determine if the counter has decreased by 1
        // Check the number of lines in growList again
        FileReader newfr = new FileReader("growList.txt");
        BufferedReader newbr = new BufferedReader(newfr);
        int newCounter = 0;
        String newLine;

        while ((newLine = newbr.readLine()) != null) {
            System.out.println("newLine is: " + newLine);
            // Add one each iteration to determine the number of lines in the file.
            newCounter ++;
        }

        // Check that the old counter is equal to the newCounter + 1
        assertEquals(counter, newCounter + 1);
    }

    @Test
    // Test removePlant method, along with the removePermanentlyCheck method by deleting from the harvestList
    public void removePlantFromHarvestTest() throws IOException {
        // Since the file should be in the harvestList, we can test that this file is there by testing the Boolean method removePermanentlyCheck.
        assertFalse(RemovePlant.removePermanentlyCheck("TestPlant"));

        // Begin a new file reader for the harvestList
        FileReader fr = new FileReader("harvestList.txt");
        BufferedReader br = new BufferedReader(fr);
        int counter = 0;
        String line;
        // Go through each line, determining the amount of lines in the file
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            // Add one to the counter each time. Determine how many lines in the file
            counter ++;
        }

        String removalLine = "TestPlant";
        RemovePlant.removePlant("harvestList.txt", removalLine);

        // Rerun the FileReader to determine if the counter has decreased by 1
        // Check the number of lines in the growList again
        FileReader newFr = new FileReader("harvestList.txt");
        BufferedReader newBr = new BufferedReader(newFr);
        int newCounter = 0;
        String newLine;

        while ((newLine = newBr.readLine()) != null) {
            System.out.println("newLine is: " + newLine);
            // Add one each iteration to determine the number of lines in the file.
            newCounter ++;
        }
        // Check that the old counter is equal to the newCounter + 1
        assertEquals(counter, newCounter + 1);
    }

    @After
    // Ensures that if we only run one test, we will delete the created files. Methods are tested above.
    public void removeAllAgain() throws IOException {
        // Remove file from growList
        RemovePlant.removePlant("growList.txt", "TestPlant");
        // Remove file from harvestList
        RemovePlant.removePlant("harvestList.txt", "TestPlant");
        // Remove file from file. This should work as long as the plants are removed from the grow and harvestLists
        RemovePlant.removePermanentlyCheck("TestPlant");
    }
}
