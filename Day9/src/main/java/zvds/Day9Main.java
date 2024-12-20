package zvds;

import zvds.aoc.PartOne;
import zvds.aoc.PartTwo;

import java.io.File;
import java.io.IOException;

public class Day9Main {
    public static void main(String[] args) throws IOException {
        File file = fetchInputFile("input.txt");
        PartOne partOne = new PartOne();
        PartTwo partTwo = new PartTwo();

        partOne.run(file);
        partTwo.run(file);

    }
    private static File fetchInputFile(String fileName){
        ClassLoader classLoader = Day9Main.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

}