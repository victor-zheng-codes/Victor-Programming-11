package sample;

import java.io.*;

public class RemovePlant {
    private static FileReader fr;
    private static BufferedReader br;

    //Requires: A String type of plant either "harvest" or "grow" and the String plantName
    //Modifies: File either growList or harvestList and File tempFile
    //Effects: removes a  plant from the growList, by writing to a tempFile and then copying the file over to the growList
    public static void removePlant(String fileName, String plantName) throws IOException {
        File inputFile = new File (fileName);
        File tempFile = new File ("tempFile.txt");
        System.out.println("Removing this: " + plantName);

        FileReader fr = new FileReader(inputFile);
        BufferedReader br = new BufferedReader(fr);
        String line;

        //Write to a temporary file that we can copy back to later
        FileWriter fileWrite = new FileWriter(tempFile);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

        while ((line = br.readLine()) != null) {
            System.out.println("line located: " + line + " located: " + plantName);
            //When we locate the line, we can delete this from the list
            if (line.equals(plantName)) {
                System.out.println("line located: " + line + " located: " + plantName);
                System.out.println("line not written to temp file is: " + line);
            } else {
                //Else, we write the file back into the list
                System.out.println("wrote: " + line);
                bufferWrite.write(line + "\r");
            }
        }
        br.close();
        bufferWrite.close();
        CopyFile.copy(fileName, "tempFile.txt");
    }
}

