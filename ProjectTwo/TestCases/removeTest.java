//Not in use

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class removeTest {

    @Before
    public void setup(){

    }

    @Test
    public void removePlantTest() throws IOException {
        //check the current size of the plantsList
        FileReader fr = new FileReader("plantList");
        BufferedReader br = new BufferedReader(fr);
        String line;
        int counter = 0;
        //Read through each line, adding 1
        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            counter ++;
        }

        int newCounter = 0;

        while ((line = br.readLine()) != null) {
            System.out.println("line is: " + line);
            newCounter ++;
        }

        //Check that counter is one more than the removed counter
        assertEquals(newCounter, counter+1);
    }
}
