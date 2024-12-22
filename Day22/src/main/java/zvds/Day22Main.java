package zvds;

import zvds.aoc.PartOne;
import zvds.aoc.PartTwo;

import java.io.File;

public class Day22Main {
    public static void main(String[] args) {
        File input = fetchInputFile("input.txt");
        PartOne partOne = new PartOne();
        PartTwo partTwo = new PartTwo();
        partOne.run(input);
        partTwo.run(input);
    }
    private static File fetchInputFile(String fileName){
        ClassLoader classLoader = Day22Main.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}