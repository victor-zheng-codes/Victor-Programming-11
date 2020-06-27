package sample;

import java.io.*;
import java.util.ArrayList;

public class AvailableLists {

    private String name;

    private static ArrayList<String> lists = new ArrayList<>();

    AvailableLists(){
        this.name = name;
    }

    public static void addToList(String string){
        lists.add(string);
    }

    public static void removeFromList(String string){
        int counter = 0;

        for(String i: lists){
            counter ++;
            if(i.equals(string)){
                lists.remove(counter);
            }
        }
    }

    /*
    public static void copyList() throws IOException {
        FileWriter fileWrite = new FileWriter("friendLists.txt");
        BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

        FileReader frtemp = new FileReader("tempFile.txt");
        BufferedReader brtemp = new BufferedReader(frtemp);

        String line;

        while ((line = brtemp.readLine()) != null) {
            bufferWrite.write(line + "\r");
        }
        bufferWrite.close();
    }

     */

    public static ArrayList<String> getLists() {
        return lists;
    }

    public static void setLists(ArrayList<String> lists) {
        AvailableLists.lists = lists;
    }

    //To string method
    public String toString(){
        for(String i:lists){
            name = i;
        }
        return name;
    }
}
