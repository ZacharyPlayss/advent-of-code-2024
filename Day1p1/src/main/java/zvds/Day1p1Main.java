package zvds;


import zvds.calculator.DistanceCalculator;
import zvds.calculator.SimpleDistanceCalculator;
import zvds.reader.CsvReader;
import zvds.sorter.ArraySorter;
import zvds.utils.ArrayUtils;

public class Day1p1Main {

    public static void main(String[] args) {
        int[][] allArrays = CsvReader.readArraysFromCSV("E:/Development/Java/adventofcode2024/Day1p1/src/main/resources/input-sheet.csv");
        int[] ia1 = allArrays[0];
        int[] ia2 = allArrays[1];

        ia1 = ArraySorter.sortArray(ia1);
        ia2 = ArraySorter.sortArray(ia2);

        ArrayUtils.printArray(ia1);
        ArrayUtils.printArray(ia2);

        DistanceCalculator distanceCalculator = new SimpleDistanceCalculator();
        int totalDistance = distanceCalculator.calculateListDifference(ia1, ia2);

        System.out.println("Total distance between both lists: " + totalDistance);
    }

}