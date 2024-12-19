package zvds.aoc;

import zvds.calculator.distance.DistanceCalculator;
import zvds.calculator.distance.SimpleDistanceCalculator;

import zvds.reader.TxtReader;
import zvds.sorter.ArraySorter;
import zvds.utils.ArrayUtils;

import java.io.File;

public class PartOne implements PartInterface{
    @Override
    public void run(File input) {
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

}
