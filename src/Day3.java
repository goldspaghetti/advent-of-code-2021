import java.util.Scanner;
public class Day3 {
    static int inputNum = 0;
    static int[] bitFrequency = new int[12];
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String currInput;
        while (input.hasNextLine()){
            currInput = input.nextLine();
            if (currInput.equals("\n") || currInput.equals("")){
                break;
            }
            inputNum++;
            for (int i = 0; i < 12; i++){
                if (currInput.charAt(i) - 48 == 1){
                    bitFrequency[i]++;
                }
            }
        }
        System.out.println(inputNum);
        int gamma = 0;
        int epsilon = 0;
        for (int i = 0; i < 12; i++){
            if (bitFrequency[i] > Math.floor((float)inputNum/2)){
            //if (bitFrequency[i] > inputNum/2){
                gamma = gamma|1;
                gamma = gamma << 1;
                epsilon = epsilon << 1;
            }
            else{
                epsilon = epsilon|1;
                epsilon = epsilon<<1;
                gamma = gamma <<1;
            }
        }
        System.out.println((~gamma)*gamma);
        System.out.println(epsilon*gamma);
    }
}
