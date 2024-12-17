package zvds;


import zvds.calculator.DistanceCalculator;
import zvds.calculator.SimpleDistanceCalculator;
import zvds.reader.TxtReader;
import zvds.sorter.ArraySorter;
import zvds.utils.ArrayUtils;

import java.io.File;

public class Day1p1Main {

    public static void main(String[] args) {
        File input = fetchInputFile("input.txt");

        int[][] allArrays = TxtReader.readFileTo2DArrayAsColumns(input);
        int[] column1 = allArrays[0];
        int[] column2 = allArrays[1];

        column1 = ArraySorter.sortArray(column1);
        column2 = ArraySorter.sortArray(column2);

        ArrayUtils.printArray(column1);
        ArrayUtils.printArray(column2);

        DistanceCalculator distanceCalculator = new SimpleDistanceCalculator();
        int totalDistance = distanceCalculator.calculateListDifference(column1, column2);

        System.out.println("Total distance between both lists: " + totalDistance);
    }

    private static File fetchInputFile(String fileName){
        ClassLoader classLoader = Day1p1Main.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}