package sample;
import java.io.*;

public class RemovePlant {
    //Requires: A String type of plant either "harvest" or "grow" and the String plantName
    //Modifies: File either growList or harvestList and File tempFile
    //Effects: removes a  plant from the growList, by writing to a tempFile and then copying the file back to the inputFile
    public static void removePlant(String inputFileName, String plantName) throws IOException {
        // Store the input file as the fileName
        File inputFile = new File (inputFileName);
        // Store the tempFile as "tempFile.txt"
        File tempFile = new File ("OtherFiles\\tempFile.txt");
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
        CopyFile.copy(inputFileName, "OtherFiles\\tempFile.txt");
    }
    //Requires: nothing
    //Modifies: File of the plant, if it is found to be true
    //Effects: removes a  plant from file if it has been removed from both the harvest and grow lists
    public static boolean removePermanentlyCheck(String plantName) throws IOException{
        boolean removedFromGrow = true;
        boolean removedFromHarvest = true;

        // Read each line of the growList, looking for the plantName
        FileReader frGrow = new FileReader("growList.txt");
        BufferedReader brGrow = new BufferedReader(frGrow);
        String growLine;
        // Go through each line, looking for a line that equals the plant name
        while ((growLine = brGrow.readLine()) != null) {
            if(growLine.equals(plantName)){
                // Set removed from grow to false. Indicates that this file is still in the grow list
                removedFromGrow = false;
            }
        }

        // Read each line of the harvestList, looking for the plantName
        FileReader frHarvest = new FileReader("harvestList.txt");
        BufferedReader brHarvest = new BufferedReader(frHarvest);
        String harvestLine;
        // Go through each line, looking for a line that equals the plant name
        while ((harvestLine = brHarvest.readLine()) != null) {
            if(harvestLine.equals(plantName)){
                // Set removed from harvest to false. Indicates that this file is still in the harvest list
                removedFromHarvest = false;
            }
        }
        // Determine if the plant has been removed from both lists.
        if(removedFromGrow && removedFromHarvest){
            System.out.println("Removed from both harvest and grow, now deleting");
            File removalFile = new File("Plants\\" + plantName + ".txt");
            System.out.println(removalFile);
            if(removalFile.delete()){
                System.out.println("Successful deletion");
                return true;
            }
            else{
                System.out.println(removalFile.getAbsolutePath());
                System.out.println("Unsuccessful deletion");
                return false;
            }
        }
        else{
            System.out.println("Did not remove file, still in a list");
            return false;
        }
    }
}

