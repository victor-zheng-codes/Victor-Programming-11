import org.junit.Before;
import org.junit.Test;
import sample.LoadPlants;
import sample.Plant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class writePlantToFileTest {
    @Before
    public void setup(){

    }
    @Test

    public void checkNewPlant() throws IOException {
        //Initialize array list
        ArrayList<Plant> testing = new ArrayList<>();
        //check that plant list is empty
        assertEquals(0, testing.size());

        //create a new plant, append false to start from beginning
        Plant newPlantOne = new Plant("name", "07-29-2020", "09-21-2020", "Needs watering");
        newPlantOne.writeToFile("test.txt", false);

        //Check that array list size is increased file
        testing.add(newPlantOne);
        assertEquals(1, testing.size());

        //Check that plant is inserted into file
        FileReader fr = new FileReader("test.txt");
        BufferedReader br = new BufferedReader(fr);

        //Read each line, easiest way to test would be count the amount of lines
        int counter = 0;
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            counter ++;
        }
        assertEquals(5, counter);

    }

}
