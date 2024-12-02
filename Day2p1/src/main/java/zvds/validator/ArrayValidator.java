package zvds.validator.predicates;

import java.util.function.Predicate;

public class ArrayValidator {

    private Predicate<int[]> isIncreasing;
    private Predicate<int[]> isDecreasing;
    private Predicate<int[]> adjacentLevelsDifferBy1To3;

    public ArrayValidator(Predicate<int[]> isIncreasing, Predicate<int[]> isDecreasing, Predicate<int[]> adjacentLevelsDifferBy1To3) {
        this.isIncreasing = isIncreasing;
        this.isDecreasing = isDecreasing;
        this.adjacentLevelsDifferBy1To3 = adjacentLevelsDifferBy1To3;
    }

    public boolean isSafe(int[] array) {
        return (isIncreasing.or(isDecreasing)).and(adjacentLevelsDifferBy1To3).test(array);
    }
}