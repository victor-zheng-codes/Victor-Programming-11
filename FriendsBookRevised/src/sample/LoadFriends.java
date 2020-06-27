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
    private static void parseFriend(String string){
        int startIndex = 0;
        int endIndex = 0;
        int locationCounter = 0;

        System.out.println("String is: " + string);
        ArrayList<Integer> location = new ArrayList<>();
        ArrayList<String> conversionList = new ArrayList<>();

        //adding each comma into the list
        for(int i = 0; i < string.length(); i++){
            if(string.substring(i,i+1).equals(",")){
                System.out.println("Adding: " + i);
                location.add(i);
            }
        }
        for(int i = 0; i < string.length(); i++){
            if(i == location.get(0)){
                conversionList.add(string.substring(0, i));
            }
            if(locationCounter > 3) {
                System.out.println("Breaking from loop");
                break;
            }
            //System.out.println("i is: " + i);

            if (string.substring(i, i + 1).equals(",")) {
                System.out.println("locationCounter: " + locationCounter + " location getting: " + location.get(locationCounter));
                System.out.println("Found Comma");

                startIndex = location.get(locationCounter);
                endIndex = location.get(locationCounter + 1);
                //age = Integer.parseInt(string.substring(i + counter, pos + 3));
                //age = Integer.parseInt(string.substring(startIndex, endIndex));
                conversionList.add(string.substring(startIndex + 1, endIndex));

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
