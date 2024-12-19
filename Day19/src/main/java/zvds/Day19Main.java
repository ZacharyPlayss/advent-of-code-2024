package zvds;

import zvds.aoc.PartOne;
import zvds.aoc.PartTwo;

import java.io.File;

public class Day19Main {
    public static void main(String[] args) {
        File input = fetchInputFile("input.txt");
        PartOne partOne = new PartOne();
        partOne.run(input);
        PartTwo partTwo = new PartTwo();
        partTwo.run(input);
    }

    private static File fetchInputFile(String fileName){
        ClassLoader classLoader = Day19Main.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}