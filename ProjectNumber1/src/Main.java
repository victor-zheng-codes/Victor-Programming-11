import java.util.Arrays;
import java.util.List;

public class Main {
    //Code your solution to problem number one here
    static int problemOne(String s){
        int answer = 0;
        //your code here
        //Determine what input string is
        System.out.println("input string is:" + s);
        //introduce variables
        String letter;

        //create for loop to iterate over every letter in input
        for(int i = 0; i < s.length(); i++){
            //find the location of the letter, starting at the first letter
            letter = s.substring(i, i+1);
            //determine if letter is an a, e, i, o, or u
            if(letter.equals("a")	|| letter.equals("e") || letter.equals("i") || letter.equals("o") || letter.equals("u")){
                //Add one to the answer, this is the total number of vowels
                answer += 1;
            }

        }
        //print out the answer
        System.out.println(answer);
        return answer;
    }
    //Code you problem number two here
    static int problemTwo(String s){
        int answer = 0;
        //your code here
        //print what the input is
        System.out.println("Input is: " + s );
        //introduce variables
        String findingBob;

        //create for loop to run through loop. Can put s.length() - 2 because bob is 3 characters
        for(int i = 0; i < (s.length() - 2) ; i++){
            //System.out.println("iteration: "+ i);
            //iterate over every 3 characters at once.
            findingBob = s.substring(i, i+3);
            //System.out.println("test: " + test);

            //determine if the varable findingBob is bob
            if(findingBob.equals("bob")){
                //add the total number to total
                answer += 1;
                //System.out.println("found bob");
            }
        }
        System.out.println("answer is: " + answer);
        return answer;
    }
    //Code your solution to problem number 3 here
    static String problemThree(String s){
        //your code here
        //introduce new variables
        //create a letter array with total length of s and add 1. will help later when determining longest string
        int[] letterArray = new int[s.length()+1];
        String[] finalArray = new String[s.length()];
        String currentString = "";
        //make a string for the alphabet. Add a space to help iterate over the last letter
        String alphabet = ("abcdefghijklmnopqrstuvwxyz ");
        String letterOne = "";
        String alphabetOne = "";
        String finalCheck = "";
        String previousTest = "";
        int count = 0;
        int arrayCount = 0;
        int biggestArray = 0;

        //for loop to iterate over the length of the input
        for(int i = 0; i < (s.length()); i ++) {
            //Determine if the number will go cause an error
            if (i == (s.length() - 1)) {
                System.out.println("Entered last letter");
                //create a new variable so that no problem occurs
                int p = i;
                letterOne = s.substring(p, p + 1);

            } else {
                //if there is no error, continue as usual
                letterOne = s.substring(i, i + 1);
            }

            //make another for loop to make each letter in the alphabet equal something.
            for (int j = 0; j < alphabet.length(); j++) {
                //iterate over alphabet
                alphabetOne = alphabet.substring(j, j + 1);
                //System.out.println("alphabetOne is: " + alphabetOne);

                //determine which letter matches with each letter
                if(alphabetOne.equals(letterOne)) {
                    //put the number of iterations it took to get to this number in an array that starts with 0
                    letterArray[i+1] = count;
                    System.out.println("Found letter: " + alphabetOne);
                    System.out.println("count is: " + count);
                    count = 0;
                    System.out.println("letterArray is: " + letterArray[i+1]);
                    //Print out the string to see, importing standard java utilities
                    System.out.println(Arrays.toString(letterArray));
                    //break out of loop after finding the correct letter
                    break;

                } else {
                    //If the letter is not found, then add to the counter
                    count += 1;

                }
            }
        }
        // create another for loop to store together alphabetical strings
        for(int k = 0; k < s.length(); k ++){

            System.out.println("k"+ k);
            // determine if the count from each letter is greater than or equal to the next letter
            //also helps with preventing out of bounds errors
            if(letterArray[k] <= letterArray[k+1]){
                //
                System.out.println("Array is: " + Arrays.toString(letterArray));
                System.out.println("LetterArray current to next: " + letterArray[k] +"\t" + letterArray[k+1]);
                //determine if the amount iterated over is greater than 1
                if(k>1){
                    //This solves issues with forgetting the previous letter.
                    if(letterArray[k-1] <= letterArray[k]){
                        System.out.println("previousTest condition met");
                        //add the previous array to the current array to create currentString
                        String previousArray = finalArray[k-1];
                        previousArray = previousTest;
                        //the current string is: the current iteration - the the counter of continuous letters, to the current letter
                        currentString = s.substring((k-arrayCount), k+1);
                        currentString = previousArray + currentString;
                        System.out.println("currentString" + currentString);

                    }
                    else if(letterArray[k-1] >= letterArray[k]){
                        //determine if the previous letter had a decrease beforehand, then also add previous to current
                        System.out.println("previous test condition met");
                        String previousArray = finalArray[k-1];
                        previousArray = previousTest;
                        //the current string is: the current iteration - the the counter of continuous letters, to the current letter
                        currentString = s.substring((k-arrayCount), k+1);
                        //add the current string to the previous string
                        currentString = previousArray + currentString;
                        System.out.println("currentString" + currentString);

                    }
                    else{
                        //Print out that previousTest has not been met
                        System.out.println("previousTest condition not met");
                        System.out.println("letterArray: " + letterArray[k-1] +"\t"+ letterArray[k]);
                        //the current string is: the current iteration - the the counter of continuous letters, to the current letter
                        currentString = s.substring(k-arrayCount,(k+1));
                    }

                }
                else{
                    //This is when k is equal to one or zero
                    System.out.println("k equals 0 or 1");
                    currentString= s.substring(k-arrayCount, (k+1));
                }
                //Will do this no matter what happnens
                System.out.println("k and arraycount: " + (k-arrayCount) + "\t" + (arrayCount+1));
                System.out.println("currentString: " + currentString);
                //add the current string to an array
                finalArray[k] = currentString;
                //print out current string usign java utilities
                System.out.println("final array" + Arrays.toString(finalArray));
                //since this is a true iteration, then continue this to next letter
                arrayCount += 1;
            }
            else{
                //previousTest to store previous number
                previousTest =  s.substring(k,k+1);
                System.out.println("Did not find new letter");
                //create an empty array space in this area
                finalArray[k] = "";
                System.out.println("final array" + Arrays.toString(finalArray));
                //Restart the array count at zero, the string will not continue
                arrayCount = 0;
            }
        }
        // for loop to determine largest array from finalArray
        for(int h = 0; h<finalArray.length; h++){
            System.out.println("ENTERED FINDING CORRECT ARRAY");

            //create a list from array for easier iteration
            List<String> listCheck = Arrays.asList(finalArray);
            //The final check goes through each set of letters from the list
            finalCheck = listCheck.get(h);

            System.out.println("listCheck is: " + listCheck);
            System.out.println("finalCheck: " + finalCheck);
            //determine if the length of the array is bigger than all others. Lengths that have already appeared, won't be iterated through
            if(finalCheck.length() > biggestArray){
                //set the biggestArray to the array with the biggest length. Ensures that the first one found will be kept.
                biggestArray = finalCheck.length();
                //set s to the final check that is largest.
                s = finalCheck;
                System.out.println("s is finally: " + s);
            }
        }

        System.out.println("s: " + s);
        return s;
    }
    public static void main(String[] args) {
        /*
        Set s to a string and run your method using s as the parameter
        Run your method in a println statement to determine what the output was
        Once you think you have it working try running the tests.
        The tests will put your method through several different Strings to test
        all possible cases.  If you have 100% success then there is no bugs in your methods.
         */
        String s;
        //problemThree("abcdefghijklmnopqrstuvwxyz");
        problemThree("fhsjdjdgdf");


    }
}
