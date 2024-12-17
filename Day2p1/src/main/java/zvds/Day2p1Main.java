package zvds;

import zvds.reader.TxtReader;
import zvds.validator.Validator;
import zvds.validator.predicates.IncreasingPredicate;
import zvds.validator.predicates.AdjacentLevelsDifferBy1To3Predicate;
import zvds.validator.ArrayValidator;
import zvds.validator.predicates.DecreasingPredicate;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Day2p1Main {
    public static void main(String[] args) {
        final int maxDifference = 3;

        File file = fetchInputFile("input.txt");
        int[][] actualInput = TxtReader.readFileTo2DArray(file);

        IncreasingPredicate isIncreasing = new IncreasingPredicate();
        DecreasingPredicate isDecreasing = new DecreasingPredicate();
        AdjacentLevelsDifferBy1To3Predicate adjacentLevelsDifferBy1To3 = new AdjacentLevelsDifferBy1To3Predicate();

        ArrayValidator validator = new ArrayValidator(isIncreasing, isDecreasing, adjacentLevelsDifferBy1To3);

        List<int[]> safeArrays = validateAllArrays(actualInput, validator);
        System.out.println(safeArrays.size() + " arrays are safe!");
    }

    private static List<int[]> validateAllArrays(int[][] input, Validator validator){
        List<int[]> safeArrays = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            if (validator.isValid(input[i])) {
                safeArrays.add(input[i]);
            }
        }
        return safeArrays;
    }

    private static File fetchInputFile(String fileName){
        ClassLoader classLoader = Day2p1Main.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

}
