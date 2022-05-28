import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Day7 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String inputLine = input.nextLine();
        ArrayList<Integer> crabArr = new ArrayList<>();
        int currNum = 0;
        int maxNum = -1;
        for (char c: inputLine.toCharArray()){
            if (c == ','){
                //currNum = 0;
                crabArr.add(currNum);
                if (currNum > maxNum){
                    maxNum = currNum;
                }
                currNum = 0;
            }
            else{
                currNum *= 10;
                currNum += (c-48);
            }
        }
        crabArr.add(currNum);
        if (currNum > maxNum){
            maxNum = currNum;
        }
        //crabArr.add(currNum);
        //simply try every position?
        int lowestFuelCost = 0;
        int lowestIndex = 0;
        for (int i = 0; i < maxNum; i++){
            int fuelCost = 0;
            for (int j = 0; j < crabArr.size(); j++){
                int distance = Math.abs(i-crabArr.get(j));
                fuelCost += distance*(distance+1)/2;
            }
            if (i == 0){
                lowestFuelCost = fuelCost;
            }
            else{
                if (lowestFuelCost > fuelCost){
                    lowestFuelCost = fuelCost;
                    lowestIndex = i;
                }
            }
        }
        System.out.println(Arrays.toString(crabArr.toArray()));
        System.out.println(lowestIndex);
        System.out.println(lowestFuelCost);
    }
}
