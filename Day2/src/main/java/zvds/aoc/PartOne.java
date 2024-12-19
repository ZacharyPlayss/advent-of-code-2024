package zvds.aoc;

import zvds.reader.TxtReader;
import zvds.validator.ArrayValidator;
import zvds.validator.Validator;
import zvds.validator.predicates.AdjacentLevelsDifferBy1To3Predicate;
import zvds.validator.predicates.DecreasingPredicate;
import zvds.validator.predicates.IncreasingPredicate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PartOne implements PartInterface{
    final int maxDifference = 3;
    @Override
    public void run(File input) {
        int[][] actualInput = TxtReader.readFileTo2DArray(input);

        IncreasingPredicate isIncreasing = new IncreasingPredicate();
        DecreasingPredicate isDecreasing = new DecreasingPredicate();
        AdjacentLevelsDifferBy1To3Predicate adjacentLevelsDifferBy1To3 = new AdjacentLevelsDifferBy1To3Predicate();

        ArrayValidator validator = new ArrayValidator(isIncreasing, isDecreasing, adjacentLevelsDifferBy1To3);

        List<int[]> safeArrays = validateAllArrays(actualInput, validator);
        System.out.println(safeArrays.size() + " arrays are safe!");
    }
    private List<int[]> validateAllArrays(int[][] input, Validator validator){
        List<int[]> safeArrays = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            if (validator.isValid(input[i])) {
                safeArrays.add(input[i]);
            }
        }
        return safeArrays;
    }

}
