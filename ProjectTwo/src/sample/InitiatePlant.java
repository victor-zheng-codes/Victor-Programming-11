package sample;
import java.io.*;

public class InitiatePlant {
    // Create fields for a new plant
    private String name;
    private String startDate;
    private String harvestDate;
    private String notes;

    // Constructor for initializing plants
    public InitiatePlant(String name, String startDate, String harvestDate, String notes){
        this.name = name;
        this.startDate = startDate;
        this.harvestDate = harvestDate;
        this.notes = notes;
    }
    //Requires: String fileName
    //Modifies: nothing
    //Effects: Adds a new list name into the growList
    public void writeToGrowList() throws IOException{
        System.out.println("grow date length is: " + startDate.length());
        System.out.println("writing: " + name + " to growList");
        // Initialize a fileWriter to the growList.txt file. Make sure to append
        FileWriter fw = new FileWriter("growList.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        // Write the name of the new plant to the file
        bw.write(name + "\r");
        bw.close();
    }
    //Requires: String fileName
    //Modifies: nothing
    //Effects: Adds a new list name into the harvestList
    public void writeToHarvestList() throws IOException{
        System.out.println("grow date length is: " + startDate.length());
        System.out.println("writing: " + name + " to harvestList");
        // Initialize a fileWriter to the harvestList.txt file. Making sure to append
        FileWriter fw = new FileWriter("harvestList.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        // Write the name of the new plant to the file
        bw.write(name + "\r");
        bw.close();
    }
    //Requires: String fileName that we are writing to
    //Modifies: fileName, this
    //Effects: writes the name, age, grade, skills, and traits of this friend to the specified file
    public void writeToFile(String fileName) throws IOException {
        System.out.println("writing to this file: " + fileName);
        // Write to the fileName. Making sure to append to an empty file
        FileWriter fileWrite = new FileWriter("Plants\\" + fileName, false);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);
        // Write the name, start date, harvest date, and notes. Add a comma and create a new line after each line.
        bufferWrite.write(name + ",\r");
        bufferWrite.write(startDate + ",\r");
        bufferWrite.write(harvestDate + ",\r");
        bufferWrite.write(notes + ",\r");
        // Write an empty line to file, so that we don't miss a line when reading
        bufferWrite.write(";\r");
        bufferWrite.close();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(String harvestDate) {
        this.harvestDate = harvestDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String toString(){
        return name;
    }
}
