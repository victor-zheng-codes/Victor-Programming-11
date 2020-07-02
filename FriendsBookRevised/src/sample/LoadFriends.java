package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadFriends {
    //no use for these because I am storing the variables in an arrayList
    /*
    private static String name;
    private static int age;
    private static int grade;
    private static String skills;
    private static String traits;
     */

    private static FileReader fr;
    private static BufferedReader br;
    private static ArrayList<Friend> friends = new ArrayList<>();

    //Requires: String
    //Modifies: this, File fileName
    //Effects: Reads the elements in the file we are looking for, returns the ArrayList of each element place
    public static ArrayList viewFriends(String fileName) throws IOException{
        friends.clear();
        System.out.println("Creating a friend");
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        String line;
        String friendsString = "";

        while((line=br.readLine()) != null){
            if(!line.equals(";")){
                friendsString += line;
                System.out.println(line);
            }
            else{
                parseFriend(friendsString);
                friendsString = "";
                System.out.println("Special Line: "+line);
            }
        }
        //br.close();
        return friends;
    }

    //Requires: String element
    //Modifies: this
    //Effects: separates the name, age, grade, skills, and traits
    private static void parseFriend(String element){
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
            if(locationCounter > 3) {
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

        //only do name and age for now
        friends.add(new Friend(conversionList.get(0),Integer.parseInt(conversionList.get(1)),Integer.parseInt(conversionList.get(2)),conversionList.get(3),conversionList.get(4)));
        //friends.add(new Friend(name,age,1,"",""));
    }
}
