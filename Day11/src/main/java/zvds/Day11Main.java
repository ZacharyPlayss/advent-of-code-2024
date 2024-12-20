package zvds;

import zvds.aoc.PartOne;
import zvds.aoc.PartTwo;

import java.io.*;

public class Day11Main {

    public static void main(String[] args) {
        File file = fetchInputFile("input.txt");
        PartOne partOne = new PartOne();
        PartTwo partTwo = new PartTwo();
        partOne.run(file);
        partTwo.run(file);

    }
    private static File fetchInputFile(String fileName){
        ClassLoader classLoader = Day11Main.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}
