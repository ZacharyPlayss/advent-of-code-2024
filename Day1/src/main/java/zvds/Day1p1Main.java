package zvds;


import zvds.aoc.PartOne;
import zvds.aoc.PartTwo;

import java.io.File;

public class Day1p1Main {

    public static void main(String[] args) {
        PartOne partOne = new PartOne();
        PartTwo partTwo = new PartTwo();
        File input = fetchInputFile("input.txt");
        partOne.run(input);
        partTwo.run(input);
    }

    private static File fetchInputFile(String fileName){
        ClassLoader classLoader = Day1p1Main.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}