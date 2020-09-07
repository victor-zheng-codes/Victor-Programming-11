package sample;
import java.io.*;

public class CopyFile {

    //Requires: nothing
    //Modifies: File input file and File tempFile
    //Effects: reads tempFile.txt and copies it to the inputFile.txt.
    public static void copy(String copiedTo, String copiedFrom) throws IOException {
        // This is the file that needs to be written/copied to.
        FileWriter fileWrite = new FileWriter(copiedTo);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

        // Read the file that needs to be copied from
        FileReader frTemp = new FileReader(copiedFrom);
        BufferedReader brTemp = new BufferedReader(frTemp);

        String line;
        // Read each line in the original file
        while ((line = brTemp.readLine()) != null) {
            // Write each line into the file that needs to be copied to
            bufferWrite.write(line + "\r");
        }
        bufferWrite.close();
        brTemp.close();
    }
}
