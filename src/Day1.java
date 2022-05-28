import java.util.Arrays;
import java.util.Scanner;
public class Day1 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int inputVal;
        int increasing = 0;
        int pastVal = 0;
        int[] pastVals = new int[3];
        for (int i = 0; i < 3; i++){
            inputVal = input.nextInt();
            pastVal += inputVal;
            pastVals[i] = inputVal;
        }
        while (input.hasNextInt()){
            /*//String inputStr = input.nextLine();
            //System.out.println(inputStr);
            if (inputStr.equals("")){
                break;
            }
            inputVal = Integer.parseInt(input.nextLine());**/
            inputVal = input.nextInt();
            for (int i = 0; i < 2; i++){
                pastVals[i] = pastVals[i+1];
            }
            System.out.println(Arrays.toString(pastVals));
            pastVals[2] = inputVal;
            int currSum = 0;
            for (int i = 0; i < 3; i++){
                currSum += pastVals[i];
            }
            if(inputVal == 0){
                break;
            }
            System.out.println(inputVal);
            if (currSum > pastVal){
                increasing++;
            }
            pastVal = currSum;
        }
        System.out.println(increasing);
    }
}
