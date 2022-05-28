import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class Day4 {
    public static void main(String[] args){
        ArrayList<BingoBoard> bingoBoards = new ArrayList<BingoBoard>();
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> valuesChosen = new ArrayList<Integer>();
        String currLine;
        int lineNum = 0;
        boolean firstLine = true;
        int[][] bingoValues = new int[5][5];
        while (input.hasNextLine()){
            currLine = input.nextLine();
            //System.out.print(currLine);
            if (currLine.equals("d")){
                System.out.println("WTFFF");
                break;
            }
            if (currLine.equals("")){
                System.out.println("NOT FIRST LINE");
                lineNum = 0;
                if (!firstLine){
                    //print bingo values
                    bingoBoards.add(new BingoBoard(bingoValues));
                    System.out.println("BINGO VALUES");
                    for (int i = 0; i < 5; i++){
                        for (int j = 0; j < 5; j++){
                            System.out.print(bingoValues[i][j]);
                            System.out.print(" ");
                        }
                        System.out.print("\n");
                    }
                }
                bingoValues = new int[5][5];
                firstLine = false;
            }
            else if (firstLine){
                char[] currNum = new char[10];
                int currNumIndex = 0;
                for (char c : currLine.toCharArray()){
                    if (c == ','){
                        //Integer currNumValue = Integer.parseInt(currNum.toString());
                        //System.out.println(Integer.parseInt("7"));
                        //System.out.println(new String(currNum));
                        int num = 0;
                        //String test = new String(currNum);
                        for (char currChar : currNum){
                            if (currChar == '\0'){
                                break;
                            }
                            else if (currChar >= 48 && currChar <= 57){
                                num *= 10;
                                num += currChar - 48;
                            }
                        }
                        //System.out.println(new String(currNum));
                        //System.out.println(num);
                        valuesChosen.add(num);
                        currNum = new char[10];
                        currNumIndex = 0;
                    }
                    else{
                        currNum[currNumIndex] = c;
                        currNumIndex++;
                    }
                }
                int num = 0;
                //String test = new String(currNum);
                for (char currChar : currNum){
                    if (currChar == '\0'){
                        break;
                    }
                    else if (currChar >= 48 && currChar <= 57){
                        num *= 10;
                        num += currChar - 48;
                    }
                }
                //System.out.println(num);
                valuesChosen.add(num);
            }
            else{
                int[] currRow = new int[5];
                char[] currNum = new char[10];
                int currNumIndex = 0;
                int currRowIndex = 0;
                for (char c : currLine.toCharArray()){
                    if (c == ' '){
                        if (currNum[0] == '\0'){
                            //System.out.println("GOTTEM");
                            continue;
                        }
                        /*else{
                            System.out.println("Passed");
                            System.out.println(Arrays.toString(currNum));
                        }*/
                        int num = 0;
                        //String test = new String(currNum);
                        for (char currChar : currNum){
                            if (currChar == '\0'){
                                break;
                            }
                            else if (currChar >= 48 && currChar <= 57){
                                num *= 10;
                                num += currChar - 48;
                            }
                        }
                        currRow[currRowIndex] = num;
                        currNum = new char[10];
                        currNumIndex = 0;
                        currRowIndex++;
                    }
                    else if (c == '\n'){
                        break;
                    }
                    else{
                        currNum[currNumIndex] = c;
                        currNumIndex++;
                    }
                }
                int num = 0;
                //String test = new String(currNum);
                for (char currChar : currNum){
                    if (currChar == '\0'){
                        break;
                    }
                    else if (currChar >= 48 && currChar <= 57){
                        num *= 10;
                        num += currChar - 48;
                    }
                }
                currRow[currRowIndex] = num;
                bingoValues[lineNum] = currRow;
                //System.out.println("AAA");
                //System.out.println(Arrays.toString(currRow));
                System.out.println(Arrays.toString(bingoValues[lineNum]));
                lineNum++;
            }
        }
        boolean bingoBoardFound = false;
        BingoBoard justWon = null;
        int justDrawn = 0;
        //looooop
        ArrayList<Boolean> wonBoards = new ArrayList<Boolean>();
        for (int i = 0; i < valuesChosen.size(); i++){
            Integer currNum = valuesChosen.get(i);
            for (int j = 0; j < bingoBoards.size(); j++){
                BingoBoard bingoBoard = bingoBoards.get(j);
                bingoBoard.checkValue(currNum);
                if (!bingoBoard.won){
                    bingoBoard.won = bingoBoard.checkWin();
                    bingoBoardFound = true;
                    for (int k = 0; k < bingoBoards.size(); k++){
                        BingoBoard checkBoard = bingoBoards.get(k);
                        if (!checkBoard.won){
                            bingoBoardFound = false;
                            break;
                        }
                    }
                    if (bingoBoardFound){
                        justDrawn = currNum;
                        justWon = bingoBoard;
                        break;
                    }
                }

                /*if (bingoBoard.checkWin()){
                    bingoBoardFound = true;
                    justDrawn = currNum;
                    justWon = bingoBoard;
                    break;
                }*/
            }
            if (bingoBoardFound){
                break;
            }
        }
        if (justWon != null) {
            for (int i = 0; i < 5; i++){
                System.out.println(Arrays.toString(justWon.bingoValues[i]));
            }
            for (int i = 0; i < 5; i++){
                System.out.println(Arrays.toString(justWon.markedValues[i]));
            }
            System.out.print("justDrawn: ");
            System.out.println(justDrawn);
            //get value
            //add all unmarked values
            int sum = 0;
            for (int i = 0; i < justWon.markedValues.length; i++) {
                for (int j = 0; j < justWon.markedValues[0].length; j++) {
                    if (!justWon.markedValues[i][j]){
                        sum += justWon.bingoValues[i][j];
                    }
                }
            }
            System.out.print("FINAL VALUE:");
            System.out.println(sum*justDrawn);
        }
    }
}
class BingoBoard{
    int[][] bingoValues = new int[5][5];
    boolean[][] markedValues = new boolean[5][5];
    boolean won = false;
    public BingoBoard(int[][] values){
        //create deep copy
        for (int i = 0; i < 5; i++){
            System.arraycopy(values[i], 0, bingoValues[i], 0, 5);
        }
        //this.bingoValues = values;
    }
    public void checkValue(int value){
        boolean found = false;
        for (int i = 0; i < bingoValues.length; i++){
            for (int j = 0; j < bingoValues[0].length; j++){
                if (bingoValues[i][j] == value){
                    markedValues[i][j] = true;
                    //found = true;
                    break;
                }
            }
            if (found){
                break;
            }
        }
    }
    public boolean checkWin(){
        //check row
        boolean rowFound = true;
        for (int i = 0; i < bingoValues.length; i++){
            rowFound = true;
            for (int j = 0; j < bingoValues[0].length; j++){
                if (!markedValues[i][j]){
                    rowFound = false;
                    break;
                }
            }
            if (rowFound){
                return true;
            }
        }
        //check column
        boolean columnFound = true;
        for (int i = 0; i < bingoValues[0].length; i++){
            columnFound = true;
            for (int j = 0; j < bingoValues.length; j++){
                if (!markedValues[j][i]) {
                    columnFound = false;
                    break;
                }
            }
            if (columnFound){
                return true;
            }
        }
        return false;
    }

}