import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sample.InitiatePlant;
import sample.RemovePlant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InitiatePlantTests {
    @Before
    public void setup(){
        System.out.println("Running Initiate Plant Tests");
    }
    @Test

    public void initializeNewPlantTest() throws IOException {
        //Initialize array list
        ArrayList<InitiatePlant> testing = new ArrayList<>();
        //check that plant list is empty
        assertEquals(0, testing.size());

        //create a new plant, append false to start from beginning
        InitiatePlant newPlantOne = new InitiatePlant("TestPlant", "07-29-2020", "09-21-2020", "Needs watering");
        newPlantOne.writeToFile("TestPlant.txt");

        //Check that array list size has increased
        testing.add(newPlantOne);
        assertEquals(1, testing.size());

        //Check that plant is inserted into file
        FileReader fr = new FileReader("Plants\\TestPlant.txt");
        BufferedReader br = new BufferedReader(fr);

        //Read each line, easiest way to test would be count the amount of lines
        int counter = 0;
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            counter ++;
        }
        // Expect 5 lines (plant name, grow date, harvest date, notes, and empty line). Assert equals that counter == 5
        assertEquals(5, counter);
    }

    @Test
    public void writeToGrowListTest()throws IOException{
        // Initialize new fileReader for growList. Determine how many lines are in the file
        FileReader fr = new FileReader("growList.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        int counter = 0;
        // record the number of lines in the grow list
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            counter ++;
        }

        // Make a new plant to add to the grow list
        InitiatePlant newPlantOne = new InitiatePlant("TestPlant", "07-29-2020", "09-21-2020", "Needs watering");
        newPlantOne.writeToGrowList();

        // Initialize a new fileReader for growList. Determine how many lines are in the file again.
        FileReader newFr = new FileReader("growList.txt");
        BufferedReader newBr = new BufferedReader(newFr);
        String newLine = "";
        int newCounter = 0;
        // Record the number of lines in the grow list again
        while ((newLine = newBr.readLine()) != null) {
            System.out.println("new line is: " + newLine);
            newCounter ++;
        }

        // Determine if the size of the new counter is equal to the size of the older counter + 1 new plant.
        assertEquals(newCounter, counter + 1);
    }

    @Test
        public void writeToHarvestListTest() throws IOException{
        FileReader fr = new FileReader("harvestList.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        int counter = 0;
        // Record the amount of lines in the harvest list
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            counter ++;
        }

        // Make a new plant to add to the harvestList list
        InitiatePlant newPlant = new InitiatePlant("TestPlant", "07-29-2020", "09-21-2020", "Needs watering");
        newPlant.writeToHarvestList();

        // Initialize a new fileReader for harvestList. Determine how many lines are in the file again.
        FileReader newFr = new FileReader("harvestList.txt");
        BufferedReader newBr = new BufferedReader(newFr);
        String newLine = "";
        int newCounter = 0;

        // record the amount of lines in the harvest list again
        while ((newLine = newBr.readLine()) != null) {
            System.out.println("new line is: " + newLine);
            newCounter ++;
        }

        // Determine if the size of the new counter is equal to the size of the older counter + 1 new plant.
        assertEquals(newCounter, counter + 1);
    }

    @After
    // Run the @After to ensure that the created files get deleted.
    public void remove() throws IOException{
        // Remove file from growList
        RemovePlant.removePlant("growList.txt", "TestPlant");
        // Remove file from harvestList
        RemovePlant.removePlant("harvestList.txt", "TestPlant");
        // Remove file from file. This should work as long as the plants are removed from the grow and harvestLists
        if(RemovePlant.removePermanentlyCheck("TestPlant")){ // This for some reason doesn't work. Doesn't affect program though.
            System.out.println("Removed Plant after running initiate plant tests");
        }
    }
}
