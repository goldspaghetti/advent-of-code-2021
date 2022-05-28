import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Day5 {
    public static void main(String[] args){
        //String inputLine;
        Scanner input = new Scanner(System.in);
        ArrayList<Vent> vents = new ArrayList<>();
        while (true){
            String inputLine = input.nextLine();
            if (!inputLine.equals("d")){
                vents.add(new Vent(inputLine));
            }
            else{
                break;
            }
        }
        //ArrayList<ArrayList<Integer>> floorVents = new ArrayList<>();
        int[][] floorVents = new int[1000][1000];
        for (int i = 0; i < vents.size(); i++){
            Vent vent = vents.get(i);
            int[][] coors = vent.getCoorArray();
            if (coors == null){
                System.out.println("---");
                System.out.println(i);
                System.out.println("null");
                continue;
            }
            System.out.println(coors.length);
            for (int[] currCoor : coors){
                //System.out.println("AAA?")
                System.out.println("-");
                System.out.println(currCoor[0]);
                System.out.println(currCoor[1]);
                floorVents[currCoor[0]][currCoor[1]]++;
            }
        }

        int overlaps = 0;
        for (int i = 0; i < floorVents.length; i++){
            //System.out.println(Arrays.toString(floorVents[i]));
            for (int j = 0; j < floorVents[i].length; j++){
                //System.out.println(j);
                //System.out.println(Arrays.toString(floorVents[i]));
                if (floorVents[i][j] >= 2){
                    overlaps++;
                }
            }
        }
        System.out.print("overlaps: ");
        System.out.println(overlaps);
    }
}
class Vent{
    int startX;
    int startY;
    int endX;
    int endY;

    public Vent(){

    }
    public Vent(String inputLine){
        String startStr = "";
        String endStr = "";
        for (int i = 0; i < inputLine.length(); i++){
            char c = inputLine.charAt(i);
            if (c == ' '){
                startStr = inputLine.substring(0, i);
                endStr = inputLine.substring(i+4);
                break;
            }
        }
        //System.out.println(startStr);
        //System.out.println(endStr);
        String startXStr = "";
        String startYStr = "";
        String endXStr = "";
        String endYStr = "";
        for (int i = 0; i < startStr.length(); i++){
            char c = startStr.charAt(i);
            if (c == ','){
                startXStr = startStr.substring(0, i);
                startYStr = startStr.substring(i+1);
            }
        }
        for (int i = 0; i < endStr.length(); i++){
            char c = endStr.charAt(i);
            if (c == ','){
                //System.out.println(i);
                //System.out.println(c);
                endXStr = endStr.substring(0, i);
                endYStr = endStr.substring(i+1);
            }
        }
        /*System.out.println(startXStr);
        System.out.println(startYStr);
        System.out.println(endXStr);
        System.out.println(endYStr);*/

        this.startX = Integer.parseInt(startXStr);
        this.startY = Integer.parseInt(startYStr);
        this.endX = Integer.parseInt(endXStr);
        this.endY = Integer.parseInt(endYStr);
        /*System.out.print("startX: ");
        System.out.println(startX);
        System.out.print("startY: ");
        System.out.println(startY);
        System.out.print("endX: ");
        System.out.println(endX);
        System.out.print("endY: ");
        System.out.println(endY);*/
    }
    public int[][] getCoorArray(){
        int[][] returnList;
        int xDiff = Math.abs(startX-endX);
        int yDiff = Math.abs(startY-endY);
        if (xDiff == yDiff){
            int length = endX - startX;
            boolean xReversed = false;
            if (length < 0){
                length *= -1;
                xReversed = true;
            }
            boolean yReversed = false;
            if (endY - startY < 0){
                yReversed = true;
            }
            length += 1;
            returnList = new int[length][2];
            for (int i = 0; i < length; i++){
                int[] currCoor = new int[2];
                if (yReversed){
                    //currCoor[0] = startX - i;
                    currCoor[1] = startY - i;
                }
                else{
                    currCoor[1] = startY + i;
                }
                if (xReversed){
                    currCoor[0] = startX - i;
                }
                else{
                    currCoor[0] = startX + i;
                }
                returnList[i] = currCoor;
            }
            return returnList;
        }
        if (startX == endX){
            int length = endY - startY;
            boolean reversed = false;
            if (length < 0){
                length *= -1;
                reversed = true;
            }
            length += 1;
            returnList = new int[length][2];
            for (int i = 0; i < length; i++){
                int[] currCoor = new int[2];
                if (reversed){
                    currCoor[0] = startX;
                    currCoor[1] = startY - i;
                }
                else{
                    currCoor[0] = startX;
                    currCoor[1] = startY + i;
                }
                returnList[i] = currCoor;
            }
            return returnList;
        }
        else if (startY == endY){
            System.out.println("horizontal");
            int length = endX - startX;
            boolean reversed = false;
            if (length < 0){
                length *= -1;
                reversed = true;
            }
            length += 1;
            returnList = new int[length][2];
            for (int i = 0; i < length; i++){
                int[] currCoor = new int[2];
                if (reversed){
                    currCoor[0] = startX - i;
                    currCoor[1] = startY;
                }
                else{
                    currCoor[0] = startX + i;
                    currCoor[1] = startY;
                }
                returnList[i] = currCoor;
            }
            return returnList;
        }
        return null;
    }
}
