import java.util.ArrayList;
import java.util.Scanner;

public class Day22 {
    public static void main(String[] args) {
        ArrayList<Command> commands = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()){
            String currLine = input.nextLine();
            if (currLine.equals("d")){
                break;
            }
            commands.add(new Command(currLine));
        }
        Reactor reactor = new Reactor();
        for(Command command : commands){
            reactor.executeCommand(command);
        }
        System.out.println(reactor.getOnCuboids());
    }
    Command parseLine(){
        return null;
    }
}
class Command{
    boolean on;
    boolean valid = true;
    int yStart;
    int yEnd;
    int xStart;
    int xEnd;
    int zStart;
    int zEnd;
    Command(String inputLine){
        int spaceDelin = inputLine.indexOf(' ');
        String onStr = inputLine.substring(0, spaceDelin);
        on = onStr.equals("on");
        String cubeValues = inputLine.substring(spaceDelin+1);
        String[] individualCubes = cubeValues.split(",");
        for (String individualCube : individualCubes){
            parseIndividualCubes(individualCube);
        }

        System.out.println(xStart);
        System.out.println(xEnd);
        System.out.println(yStart);
        System.out.println(yEnd);
        System.out.println(zStart);
        System.out.println(zEnd);
        System.out.println(on);
        //System.out.println
    }
    void parseIndividualCubes(String individualCubes){
        char identifier = individualCubes.charAt(0);
        int delin = individualCubes.indexOf('.');
        int start = Integer.parseInt(individualCubes.substring(2, delin)) + Reactor.center;
        int end = Integer.parseInt(individualCubes.substring(delin+2)) + Reactor.center;

        if (start < 0 && end >= 101){
            start = 0;
            end = 100;
        }
        else if (end < 0 || start > 101){
            valid = false;
        }
        //else if (start)

        if (identifier == 'x'){
            xStart = start;
            xEnd = end;
        }
        else if (identifier == 'y'){
            yStart = start;
            yEnd = end;
        }
        else if (identifier == 'z'){
            zStart = start;
            zEnd = end;
        }
    }
    /*int[] getIterator(int dimension){
        //0 = x, 1 = y, 2 = z
        int start;
        int end;
        boolean increasing;
        if(dimension == 0){
            start = xStart;
            end = xEnd;

        }
        else if (dimension == 1){

        }
        else{

        }
    }*/
}
class Reactor{
    boolean[][][] cuboids = new boolean[101][101][101];
    static int center = 50;
    void executeCommand(Command command){

        if (command.valid){
            for (int x = command.xStart; x <= command.xEnd; x++) {
                for (int y = command.yStart; y <= command.yEnd; y++) {
                    for (int z = command.zStart; z <= command.zEnd; z++) {
                        cuboids[x][y][z] = command.on;
                    }
                }
            }
        }
    }
    int getOnCuboids(){
        int onNum = 0;
        for (int x = 0; x < 100; x++){
            for (int y = 0; y < 100; y++){
                for (int z = 0; z < 100; z++){
                    if (cuboids[x][y][z]){
                        onNum++;
                    }
                }
            }
        }
        return onNum;
    }
}