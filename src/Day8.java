import java.util.ArrayList;
import java.util.Scanner;
public class Day8 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        ArrayList<DataEntry> dataEntries = new ArrayList<>();
        while(input.hasNextLine()) {
            String inputLine = input.nextLine();
            if (inputLine.equals("d")) {
                break;
            }
            dataEntries.add(new DataEntry(inputLine));
        }

    }
}
class Number{
    int numberValue;
    char[] usedCharacters;
    boolean unique;
    Number(int numberValue, char[] usedCharacters, boolean unique){
        this.numberValue = numberValue;
        this.usedCharacters = usedCharacters;
        this.unique = unique;
    }
}
class DataEntry{
    static char[] zeroChars = {'a', 'b', 'c', 'e', 'f', 'g'};
    static Number zero = new Number(0, zeroChars, false);
    static char[] oneChars = {'c', 'f'};
    static Number one = new Number(1, oneChars, true);
    static char[] twoChars = {'a', 'c', 'd', 'e', 'g'};
    static Number two = new Number(2, twoChars, false);
    static char[] threeChars = {'a', 'c', 'd', 'f', 'g'};
    static Number three = new Number(3, threeChars, false);

    //char[][] inputSignals;
    //char[][] outputSignals;
    String[] inputSignals;
    String[] outputSignals;
    char[][] characterPairings = {{'a', 'a'},{'b', 'b'}};
    DataEntry(){

    }
    DataEntry(String inputLine){
        String[] inputSignals = new String[10];
        String[] outputSignals = new String[4];
        boolean afterDelimiter = false;
        int lastSpace = 0;
        int currIndex = 0;
        for (int i = 0; i < inputLine.length(); i++){
            char c = inputLine.charAt(i);
            if (c == '|'){
                currIndex = 0;
                afterDelimiter = true;
                lastSpace = i+1;
                i++;
                continue;
            }
            if (c == ' '){
                if (afterDelimiter){
                    outputSignals[currIndex] = inputLine.substring(lastSpace+1, i);
                }
                else{
                    inputSignals[currIndex] = inputLine.substring(lastSpace+1, i);
                }
                currIndex++;
                lastSpace = i;
            }
        }
        outputSignals[currIndex] = inputLine.substring(lastSpace+1);
        System.out.println(inputLine.substring(lastSpace));
        this.inputSignals = inputSignals;
        this.outputSignals = outputSignals;

        System.out.println("inputSignals:");
        for (int i = 0; i < 10; i++){
            System.out.print(inputSignals[i]);
            System.out.print(",");
        }
        System.out.println("");
        System.out.println("outputSignals: ");
        for (int i = 0; i < 4; i++){
            System.out.print(outputSignals[i]);
            System.out.print(",");
        }
        System.out.println("\n----");
    }
    public void decodeDataEntry(){
        //start with unique entries
        for (String input : inputSignals){
            if (input.length() == 2){

            }
        }
    }
}
