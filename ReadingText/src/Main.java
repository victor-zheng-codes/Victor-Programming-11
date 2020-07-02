import java.io.*;
import java.util.ArrayList;

//This code reads an input txt document, rewrites it into a new file, and has a method that can search for text.
public class Main {

    //Requires: String
    //Modifies: this
    //Effects: Reads the input file and determines at what index each occurrence is at.
    public static int findText(String finding) throws IOException {
        System.out.println("");
        System.out.println("Searching for word: " + finding);
        int counter = 0;
        int lineCounter = 0;
        int indexBeginning =0;
        FileReader fReader = new FileReader("ProgrammingHistory.txt");
        BufferedReader bReader = new BufferedReader(fReader);
        String line;
        while ((line = bReader.readLine()) != null) {
            System.out.println("Just Read this line: " + line);
            lineCounter ++;

            indexBeginning = line.indexOf(finding);
            while(indexBeginning >= 0) {
                System.out.println("Found word: " + finding + ", at index position: " + indexBeginning + ", on ArrayList element: " + lineCounter);
                indexBeginning = line.indexOf(finding, indexBeginning + 1);
                counter ++;


                //System.out.println(line.substring(index,line.length()));
            }
        }
        System.out.println(counter + " occurrences of this word");

        return indexBeginning;
    }

    public static void main(String[] args) throws IOException {
        //copy each line into an arrayList
        ArrayList<String> lines = new ArrayList<>();
        FileReader fr = new FileReader("ProgrammingHistory.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            System.out.println("Just Read: " + line);
            lines.add(line);

        }
        br.close();
        for(String i:lines){
            System.out.println(i);
        }


        /*
        //Write out the arrayList into a new txt file. (not sure if this is required in the criteria)
        FileWriter fw = new FileWriter("out.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Writing to out file from ProgrammingHistory.txt file\r");
        for(String i : lines){
            bw.write(i + "\r");
        }
        bw.close();

         */


        //run the method that finds the text. Input is what we are looking for.
        findText("machine");


    }
}
