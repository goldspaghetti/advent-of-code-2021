import java.util.Scanner;
public class Day2 {
    static int horizontal = 0;
    static int depth = 0;
    static int aim = 0;
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()){
            if (parseCommand(input.nextLine()) == -1){
                break;
            }
        }
        System.out.println(horizontal);
        System.out.println(depth);
    }
    public static int parseCommand(String input){
        if (input.equals("\n") || input.equals("")){
            System.out.println("invalid input");
            return -1;
        }
        System.out.println(input);
        String command;
        int value;
        int breakPos = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ') {
                breakPos = i;
                break;
            }
        }
        if (breakPos == 0){
            System.out.println("oh no!");
        }
        command = input.substring(0, breakPos);
        value = Integer.parseInt(input.substring(breakPos+1));
        if (command.equals("forward")){
            depth += aim*value;
            horizontal += value;
        }
        else if (command.equals("down")){
            aim += value;
        }
        else if (command.equals("up")){
            aim -= value;
        }
        return 0;
    }
}
