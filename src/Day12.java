import java.util.ArrayList;
import java.util.Scanner;
/*
Pt1: see https://adventofcode.com/2021/day/12
Pt 2:
After reviewing the available paths, you realize you might have time to visit a single small cave twice.
Specifically, big caves can be visited any number of times, a single small cave can be visited at most
twice, and the remaining small caves can be visited at most once. However, the caves named start and end
can only be visited exactly once each: once you leave the start cave, you may not return to it, and once
you reach the end cave, the path must end immediately.
 */

public class Day12 {
    static ArrayList<Cave> caves = new ArrayList<>();
    static Cave startCave;
    static Cave endCave;
    public static void main(String[] args){
        //get inputs
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()){
            String currLine = input.nextLine();
            if (currLine.equals("d")){
                //idk, need more elegant way to terminate reading input. Maybe read from file and actually use EOF for has next line?
                break;
            }
            else{
                parseConnection(currLine);
            }
        }
        //printCaves();
        //System.out.println(startCave.getCavePaths());
        //System.out.println(startCave.getCavePaths2(false));
        //System.out.println(startCave.printCavePaths2(new ArrayList<Cave>()));
        System.out.println(startCave.printCavePaths3(new ArrayList<Cave>(), false));
    }
    public static void parseConnection(String inputLine){
        int delineatorIndex = 0;
        for (int i = 0; i < inputLine.length(); i++){
            char c = inputLine.charAt(i);
            if (c == '-'){
                delineatorIndex = i;
            }
        }
        String cave1Identifier = inputLine.substring(0, delineatorIndex);
        String cave2Identifier = inputLine.substring(delineatorIndex+1);
        Cave cave1 = findGivenCave(cave1Identifier);
        Cave cave2 = findGivenCave(cave2Identifier);
        cave1.addNewCave(cave2);
        cave2.addNewCave(cave1);
    }
    public static void printCaves(){
        for (Cave cave : caves){
            System.out.print(cave.identifier);
            System.out.print("-");
            System.out.print(cave.caveType);
            System.out.print("-");
            System.out.print(cave.largeCave);
            System.out.println("-");
        }
    }
    public static int findTotalPaths(){
        return startCave.getCavePaths();
    }
    public static Cave findGivenCave(String identifier){
        Cave currCave = null;
        boolean caveFound = false;
        for (Cave cave : caves){
            if (cave.identifier.equals(identifier)){
                currCave = cave;
                caveFound = true;
                break;
            }
        }
        if (!caveFound){
            currCave = new Cave(identifier);
            caves.add(currCave);
        }
        return currCave;
    }
}

class Cave{
    int caveType; //0 start, 1 end, 2 normal
    boolean largeCave = false;
    boolean movedThrough = false;
    int movedThroughNum = 0;
    boolean pathDouble = false;
    String identifier;
    ArrayList<Cave> connectedCaves = new ArrayList<>();
    Cave(String identifier){
        if (identifier.charAt(0) <= 90){
            largeCave = true;
        }
        this.identifier = identifier;
        if (identifier.equals("start")){
            caveType = 0;
            Day12.startCave = this;
        }
        else if (identifier.equals("end")){
            caveType = 1;
            Day12.endCave = this;
        }
        else{
            caveType = 2;
        }
    }
    void addNewCave(String identifier){
        for (Cave cave : Day12.caves){
            if (cave.identifier.equals(identifier)){
                connectedCaves.add(cave);
                break;
            }
        }
    }
    void addNewCave(Cave cave){
        this.connectedCaves.add(cave);
    }
    int getCavePaths(){ //FOR DAY 1
        System.out.println(identifier);
        //System.out.println((int))
        //System.out.println(movedThrough);
        //System.out.println(largeCave);
        if (this.caveType == 1){
            return 1;
        }
        else if (caveType == 0 && movedThrough){
            return 0;
        }
        else if (movedThrough && !largeCave){
            System.out.println("AAA");
            return 0;
        }
        int sum = 0;
        this.movedThrough = true;
        for (Cave cave : connectedCaves){
            sum += cave.getCavePaths();
        }
        this.movedThrough = false;
        return sum;
    }
    int getCavePaths2(boolean pathDoubled){ //FOR DAY 2
        System.out.print(identifier);
        if (this.caveType == 1){
            return 1;
        }
        else if (caveType == 0 && (movedThroughNum >= 1)){
            return 0;
        }
        if ((movedThroughNum >= 1) && !largeCave){
            if (pathDoubled){
                return 0;
            }
            else{
                pathDoubled = true;
                //this.pathDouble = true;
            }
        }
        int sum = 0;
        this.movedThroughNum += 1;
        for (Cave cave : connectedCaves){
            sum += cave.getCavePaths2(pathDoubled);
        }
        this.movedThroughNum -= 1;
        return sum;
    }
    int printCavePaths3(ArrayList<Cave> cavesVisited, boolean pathDoubled){ //depreciated, still uses old method of trying to solve pt2
        //cavesVisited.add(this);
        if (this.caveType == 1){
            System.out.println(pathDoubled);
            for (Cave cave :cavesVisited){
                System.out.print(cave.identifier);
                System.out.print(",");
            }
            System.out.println("end");
            //System.out.println("------")
            return 1;
        }
        else if (caveType == 0 && (this.movedThroughNum >= 1)){
            return 0;
        }
        if ((this.movedThroughNum >= 1) && !largeCave){
            if (pathDoubled){
                return 0;
            }
            else{
                pathDoubled = true;
            }
        }
        cavesVisited.add(this);
        int sum = 0;
        this.movedThroughNum += 1;
        for (Cave cave : connectedCaves){
            sum += cave.printCavePaths3((ArrayList<Cave>)cavesVisited.clone(), pathDoubled);
        }
        this.movedThroughNum -= 1;
        return sum;
    }
    int printCavePaths2(ArrayList<Cave> cavesVisited){
        //cavesVisited.add(this);
        if (this.caveType == 1){
            for (Cave cave :cavesVisited){
                System.out.print(cave.identifier);
                System.out.print(",");
            }
            System.out.println("end");
            //System.out.println("------")
            return 1;
        }
        else if (caveType == 0 && (movedThroughNum > 0)){
            return 0;
        }
        else if ((movedThroughNum >= 2) && !largeCave){
            return 0;
        }
        cavesVisited.add(this);
        int sum = 0;
        this.movedThroughNum += 1;
        for (Cave cave : connectedCaves){
            sum += cave.printCavePaths2((ArrayList<Cave>)cavesVisited.clone());
        }
        this.movedThroughNum -= 1;
        return sum;
    }
    void removeMovedThrough(){

    }
}
