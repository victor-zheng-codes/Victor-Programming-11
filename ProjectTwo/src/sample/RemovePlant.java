package sample;
import java.io.*;

public class RemovePlant {
    private static FileReader fr;
    private static BufferedReader br;

    //Requires: A String type of plant either "harvest" or "grow" and the String plantName
    //Modifies: File either growList or harvestList and File tempFile
    //Effects: removes a  plant from the growList, by writing to a tempFile and then copying the file back to the inputFile
    public static void removePlant(String inputFileName, String plantName) throws IOException {
        // Store the input file as the fileName
        File inputFile = new File (inputFileName);
        // Store the tempFile as "tempFile.txt"
        File tempFile = new File ("tempFile.txt");
        System.out.println("Removing this: " + plantName);

        //Write to a temporary file that we can copy back to later
        FileWriter fileWrite = new FileWriter(tempFile);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

        // read the each line from the input file, which is either growList.txt or harvestList.txt
        FileReader fr = new FileReader(inputFile);
        BufferedReader br = new BufferedReader(fr);
        String line;

        // Go through each line, looking for a line that equals the plant name
        while ((line = br.readLine()) != null) {
            System.out.println("line located: " + line + " located: " + plantName);
            //When we locate the line, we can delete this from the list
            if (line.equals(plantName)) {
                // if the line is found, we don't need to write it to file
                System.out.println("line located: " + line + " located: " + plantName);
                System.out.println("line not written to temp file is: " + line);
            } else {
                // Else, we write the file back into the list
                System.out.println("wrote: " + line);
                bufferWrite.write(line + "\r");
            }
        }
        br.close();
        bufferWrite.close();
        // Run the class CopyFile to copy back the inputFile from the tempFile
        CopyFile.copy(inputFileName, "tempFile.txt");
    }
}

