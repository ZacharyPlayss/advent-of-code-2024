package zvds;

import zvds.reader.TxtReader;
import zvds.validator.predicates.IncreasingPredicate;
import zvds.validator.predicates.AdjacentLevelsDifferBy1To3Predicate;
import zvds.validator.predicates.ArrayValidator;
import zvds.validator.predicates.DecreasingPredicate;


import java.util.ArrayList;
import java.util.List;

public class Day2p1Main {
    public static void main(String[] args) {
        final int maxDifference = 3;

        //int[][] actualInput = {{7, 6, 4, 2, 1}, {1, 2, 7, 8, 9}, {9, 7, 6, 2, 1}, {1, 3, 2, 4, 5}, {8, 6, 4, 4, 1}, {1, 3, 6, 7, 9}};
        int[][] actualInput = TxtReader.readFileTo2DArray("E:\\Development\\Java\\adventofcode2024\\Day2p1\\src\\main\\resources\\input.txt");


        IncreasingPredicate isIncreasing = new IncreasingPredicate();
        DecreasingPredicate isDecreasing = new DecreasingPredicate();
        AdjacentLevelsDifferBy1To3Predicate adjacentLevelsDifferBy1To3 = new AdjacentLevelsDifferBy1To3Predicate();

        ArrayValidator validator = new ArrayValidator(isIncreasing, isDecreasing, adjacentLevelsDifferBy1To3);

        List<int[]> safeArrays = new ArrayList<>();

        for (int i = 0; i < actualInput.length; i++) {
            if (validator.isSafe(actualInput[i])) {
                System.out.println("Array " + (i + 1) + " is Safe");
                safeArrays.add(actualInput[i]);
            } else {
                System.out.println("Array " + (i + 1) + " is Unsafe");
            }
        }

        System.out.println(safeArrays.size() + " arrays are safe!");
    }
}
