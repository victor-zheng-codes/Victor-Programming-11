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

        // Read the file that needs to be compied from
        FileReader frTemp = new FileReader(copiedFrom);
        BufferedReader brTemp = new BufferedReader(frTemp);

        String line;

        while ((line = brTemp.readLine()) != null) {
            bufferWrite.write(line + "\r");
        }
        bufferWrite.close();
        brTemp.close();
    }
}
