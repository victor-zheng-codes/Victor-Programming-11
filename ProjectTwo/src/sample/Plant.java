package sample;

import java.io.*;
import java.nio.Buffer;
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
        System.out.println("writing: " + name + " to harvestList");
        FileReader fr = new FileReader("harvestList.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        int counter = 0;

        while((line = br.readLine()) != null) {
            System.out.println("Reading this line: " + line);
            FileReader newFr = new FileReader("C:\\Users\\zheng\\IdeaProjects\\ProjectTwo\\Plants\\" + line + ".txt");
            BufferedReader newBr = new BufferedReader(newFr);
            String newLine;

            while((newLine = newBr.readLine()) != null){
                counter ++;
                if(counter == 3){
                    System.out.println("compare date is: " + newLine);
                    if(compareDates(harvestDate, newLine)){
                        System.out.println("Compared True");
                    }
                    else{
                        //Write to harvestList
                        FileWriter fw = new FileWriter("harvestList.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        System.out.println("Compared False");
                        bw.write(name + "\r");
                        bw.close();
                    }
                }
                counter = 0;
            }
        }
    }

    //Requires: Nothing
    //Modifies: Nothing
    //Effects: Compares two dates, returns true if first date is greater than the second
    public static boolean compareDates(String firstDate, String comparedDate){
        //Prompt Question: Is the first date bigger than the second, eg 2020-08-22 > 2020-08-02. Therefore true is returned
        System.out.println("Comparing these dates: " + firstDate + " and " + comparedDate);
        //Has to be less the 10 which is the length of the date
        int yearFirst = Integer.parseInt(firstDate.substring(0,4));
        int monthFirst = Integer.parseInt(firstDate.substring(5,7));
        int dayFirst = Integer.parseInt(firstDate.substring(8,10));

        System.out.println("year is: " + yearFirst + " month is: "+ monthFirst + " day is: " + dayFirst);

        int yearCompared = Integer.parseInt(comparedDate.substring(0,4));
        int monthCompared = Integer.parseInt(comparedDate.substring(5,7));
        int dayCompared = Integer.parseInt(comparedDate.substring(8,10));

        System.out.println("year is: " + yearCompared + " month is: " + monthCompared + " day is: " + dayCompared);

        if(yearFirst > yearCompared){
            System.out.println("year first is greater than year compared");
            return true;
        }
        if(yearFirst < yearCompared){
            System.out.println("year first is less than year compared");
            return false;
        }

        else{
            System.out.println("equivalent years, checking month next");
            if(monthFirst > monthCompared){
                System.out.println("month first is greater than the month compared");
                return true;
            }
            if(monthFirst < monthCompared){
                System.out.println("month first is less than the month compared");
                return false;
            }
            else{
                System.out.println("equivalent months, checking day next");
                if(dayFirst > dayCompared){
                    System.out.println("dayFirst is greater than the compared day");
                    return true;
                }
                if(dayFirst< dayCompared){
                    System.out.println("dayFirst is less than the compared day");
                    return false;
                }
                if(dayFirst == dayCompared){
                    System.out.println("equivalent days, true");
                    return true;
                }
                else{
                    System.out.println("ERROR ERROR ERROR: did not find ");
                }
            }
        }


        return false;
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
