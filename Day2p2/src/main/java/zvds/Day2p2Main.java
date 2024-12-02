package zvds;

import zvds.reader.TxtReader;
import zvds.validator.ArrayValidator;
import zvds.validator.predicates.AdjacentLevelsDifferBy1To3Predicate;
import zvds.validator.predicates.DecreasingPredicate;
import zvds.validator.predicates.IncreasingPredicate;

import java.util.ArrayList;
import java.util.List;

public class Day2p2Main {
    public static void main(String[] args) {
        int[][] actualInput = TxtReader.readFileTo2DArray("E:\\Development\\Java\\adventofcode2024\\Day2p1\\src\\main\\resources\\input.txt");
        //int[][] actualInput = {{7, 6, 4, 2, 1}, {1, 2, 7, 8, 9}, {9, 7, 6, 2, 1}, {1, 3, 2, 4, 5}, {8, 6, 4, 4, 1}, {1, 3, 6, 7, 9}};

        ArrayValidator validator = new ArrayValidator(
                new IncreasingPredicate(),
                new DecreasingPredicate(),
                new AdjacentLevelsDifferBy1To3Predicate()
        );

        List<int[]> safeArrays = new ArrayList<>();

        for (int i = 0; i < actualInput.length; i++) {
            boolean isSafe = validator.isSafe(actualInput[i]);
            boolean isSafeWithDampener = validator.isSafeWithDampener(actualInput[i]);
            if (isSafe || isSafeWithDampener) {
                System.out.println("Array " + (i + 1) + " is Safe");
                safeArrays.add(actualInput[i]);
            } else {
                System.out.println("Array " + (i + 1) + " is Unsafe");
            }
        }

        System.out.println(safeArrays.size() + " arrays are safe!");
    }

}
