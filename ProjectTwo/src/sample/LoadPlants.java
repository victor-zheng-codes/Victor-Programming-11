package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadPlants {

    private static FileReader fr;
    private static BufferedReader br;
    private static ArrayList<Plant> plants = new ArrayList<>();

    //Requires: String
    //Modifies: this, File fileName
    //Effects: Reads the elements in the file we are looking for, returns the ArrayList of each element place
    public static ArrayList viewPlants(String fileName) throws IOException{
        plants.clear();
        System.out.println("Creating a plant");
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        String line;
        String plantsString = "";

        while((line=br.readLine()) != null){
            if(!line.equals(";")){
                plantsString += line;
                System.out.println(line);
            }
            else{
                parsePlant(plantsString);
                plantsString = "";
                System.out.println("Special Line: "+line);
            }
        }
        br.close();
        return plants;
    }

    //Requires: String element
    //Modifies: this
    //Effects: separates the String name, String startDate, String harvestDate, String notes
    private static void parsePlant(String element){
        int startIndex = 0;
        int endIndex = 0;
        int locationCounter = 0;

        System.out.println("Element is: " + element);
        //introduce ArrayList to store location of comma
        ArrayList<Integer> location = new ArrayList<>();
        //introduce ArrayList to store the name, age, grade, skills and traits
        ArrayList<String> conversionList = new ArrayList<>();

        //adding each comma location into the list
        for(int i = 0; i < element.length(); i++){
            if(element.substring(i,i+1).equals(",")){
                System.out.println("Adding: " + i);
                location.add(i);
            }
        }
        for(int i = 0; i < element.length(); i++){
            if(i == location.get(0)){
                conversionList.add(element.substring(0, i));
            }
            if(locationCounter > 2) {
                System.out.println("Breaking from loop");
                break;
            }
            //System.out.println("i is: " + i);

            if (element.substring(i, i + 1).equals(",")) {
                System.out.println("locationCounter: " + locationCounter + " location getting: " + location.get(locationCounter));
                System.out.println("Found Comma");

                startIndex = location.get(locationCounter);
                endIndex = location.get(locationCounter + 1);

                conversionList.add(element.substring(startIndex + 1, endIndex));

                locationCounter++;
            }
        }
        for(String n: conversionList){
            System.out.println("Printing conversion list");
            System.out.println(n);
        }

        //String name, String startDate, String harvestDate, String notes
        plants.add(new Plant(conversionList.get(0),(conversionList.get(1)),(conversionList.get(2)),conversionList.get(3)));

    }
}