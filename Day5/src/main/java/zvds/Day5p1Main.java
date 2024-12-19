package zvds;

import zvds.aoc.PartOne;
import zvds.aoc.PartTwo;
import zvds.domain.Input;
import zvds.domain.Rule;
import zvds.parser.InputParser;
import zvds.reader.TxtReader;
import zvds.validator.RuleValidator;

import java.io.File;
import java.util.*;

public class Day5p1Main {

    public static void main(String[] args) {
        File file = fetchInputFile("input.txt");
        PartOne partone = new PartOne();
        PartTwo partTwo = new PartTwo();
        partone.run(file);
        partTwo.run(file);
    }
    private static File fetchInputFile(String fileName){
        ClassLoader classLoader = Day5p1Main.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
    public static int middlePage(List<Integer> pages) {
        if (pages.isEmpty()) throw new IllegalArgumentException("Empty print run");
        return pages.get(pages.size() / 2);
    }

}

