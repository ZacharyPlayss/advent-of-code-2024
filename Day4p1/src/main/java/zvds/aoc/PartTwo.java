package zvds.aoc;
import zvds.reader.TxtReader;
import zvds.validator.ArrayValidator;
import zvds.validator.predicates.AdjacentLevelsDifferBy1To3Predicate;
import zvds.validator.predicates.DecreasingPredicate;
import zvds.validator.predicates.IncreasingPredicate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PartTwo implements PartInterface{
    @Override
    public void run(File input) {
        int[][] actualInput = TxtReader.readFileTo2DArray(input);
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
