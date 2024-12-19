package zvds;

import zvds.aoc.PartOne;
import zvds.aoc.PartTwo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Day7p1Main {
    public static void main(String[] args) throws IOException {
        File file = fetchInputFile("input.txt");

        PartOne partOne = new PartOne();
        PartTwo partTwo = new PartTwo();

        partOne.run(file);
        partTwo.run(file);

    }
    private static File fetchInputFile(String fileName){
        ClassLoader classLoader = Day7p1Main.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}