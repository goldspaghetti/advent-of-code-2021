import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Day6 {
    static int days = 256;
    public static void main(String[] args){
        long[] fishArr = new long[9];
        Scanner input = new Scanner(System.in);
        String inputLine = input.nextLine();
        int currNum = 0;
        for (char c: inputLine.toCharArray()){
            if (c == ','){
                //currNum = 0;
                fishArr[currNum]++;
                currNum = 0;
            }
            else{
                currNum += (c-48);
            }
        }
        fishArr[currNum]++;
        for (int i = 0; i < 9; i++){
            System.out.print(fishArr[i]);
            System.out.print(',');
        }
        System.out.println("--");
        FishList fishList = new FishList(fishArr);
        for (int i = 0; i < days; i++){
            fishList.incrementDay();
        }
        for (int i = 0; i < 9; i++){
            System.out.print(fishList.fishNums[i]);
            System.out.print(',');
        }
        System.out.println("--");
        //for (int i = 0; )
        System.out.println(fishList.getFishNum());
    }
    static void day1(){
        ArrayList<Fish> fishArray = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String inputLine = input.nextLine();
        //ArrayList<Integer> fishNums = new ArrayList<>();
        int currNum = 0;
        for (char c: inputLine.toCharArray()){
            if (c == ','){
                //currNum = 0;
                fishArray.add(new Fish(currNum));
                currNum = 0;
            }
            else{
                currNum += (c-48);
            }
        }
        fishArray.add(new Fish(currNum));
        for (int i = 0; i < days; i++){
            int fishArraySize = fishArray.size();
            for (int j = 0; j < fishArraySize; j++){
                Fish fish = fishArray.get(j);
                fish.incrementDay(fishArray);
            }
        }
        /*for (int i = 0; i < fishArray.size(); i++){
            System.out.print(fishArray.get(i).dayDelay);
            System.out.print(",");
        }
        System.out.println("--");*/
        System.out.println(fishArray.size());
    }
}
class FishList{
    long[] fishNums;
    FishList(long[] startingFish){
        this.fishNums = startingFish;
    }
    public void incrementDay(){
        long newFish = fishNums[0];
        for (int i = 1; i < 9; i++){
            fishNums[i-1] = fishNums[i];
        }
        fishNums[6] += newFish;
        fishNums[8] = newFish;
    }
    public long getFishNum(){
        long fishNum = 0;
        for (int i = 0; i < 9; i++){
            fishNum += fishNums[i];
            System.out.println(fishNums[i]);
        }
        return fishNum;
    }
}
class Fish{
    int dayDelay;
    Fish(int delay){
        this.dayDelay = delay;
    }
    public void incrementDay(ArrayList<Fish> fishArray){
        if (this.dayDelay == 0){
            //System.out.println()
            dayDelay = 6;
            fishArray.add(new Fish(8));
        }
        else{
            dayDelay--;
        }
    }
}