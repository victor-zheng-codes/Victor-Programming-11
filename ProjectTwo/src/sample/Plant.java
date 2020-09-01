package sample;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Date;

public class Plant {
    private String name;
    private String startDate;
    private String harvestDate;
    private String notes;

    public Plant(String name, String startDate, String harvestDate, String notes){
        this.name = name;
        this.startDate = startDate;
        this.harvestDate = harvestDate;
        this.notes = notes;
    }

    //Requires: String fileName
    //Modifies: nothing
    //Effects: Adds a new list name into the plantsList
    public void writeToGrowList() throws IOException{

        System.out.println("grow date length is: " + startDate.length());
        System.out.println("writing: " + name + " to growList");
        FileWriter fw = new FileWriter("growList.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(name + "\r");
        bw.close();

    }

    public void writeToHarvestList() throws IOException{

        System.out.println("grow date length is: " + startDate.length());
        System.out.println("writing: " + name + " to harvestList");
        FileWriter fw = new FileWriter("harvestList.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(name + "\r");
        bw.close();

    }
    //Requires: String fileName that we are writing to, boolean append as true or false
    //Modifies: fileName, this
    //Effects: writes the name, age, grade, skills, and traits of this friend to the specified file
    public void writeToFile(String fileName, Boolean append) throws IOException {
        System.out.println("writing to this file: " + fileName);
        FileWriter fileWrite = new FileWriter(fileName, append);
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);
        bufferWrite.write(name + ",\r");
        bufferWrite.write(startDate + ",\r");
        bufferWrite.write(harvestDate + ",\r");
        bufferWrite.write(notes + ",\r");
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
