package zvds.validator;

import zvds.utils.ArrayUtils;
import zvds.validator.predicates.AdjacentLevelsDifferBy1To3Predicate;
import zvds.validator.predicates.DecreasingPredicate;
import zvds.validator.predicates.IncreasingPredicate;

import java.util.function.Predicate;

public class ArrayValidator implements Validator {

    private Predicate<int[]> isIncreasing;
    private Predicate<int[]> isDecreasing;
    private Predicate<int[]> adjacentLevelsDifferBy1To3;

    public ArrayValidator(Predicate<int[]> isIncreasing, Predicate<int[]> isDecreasing, Predicate<int[]> adjacentLevelsDifferBy1To3) {
        this.isIncreasing = isIncreasing;
        this.isDecreasing = isDecreasing;
        this.adjacentLevelsDifferBy1To3 = adjacentLevelsDifferBy1To3;
    }


    @Override
    public <T> boolean isValid(T input) {
        if(input.getClass().isArray()) {
            return (isIncreasing.or(isDecreasing)).and(adjacentLevelsDifferBy1To3).test((int[]) input);
        }else{
            throw new IllegalArgumentException("Arrayvalidator expects the input to be an array");
        }
    }

    public boolean isSafe(int[] array) {
        return (isIncreasing.or(isDecreasing)).and(adjacentLevelsDifferBy1To3).test(array);
    }

    public boolean isSafeWithDampener(int[] array){
        ArrayValidator validator = new ArrayValidator(
                new IncreasingPredicate(),
                new DecreasingPredicate(),
                new AdjacentLevelsDifferBy1To3Predicate()
        );

        if (validator.isSafe(array)) {
            return true;
        }

        for (int i = 0; i < array.length; i++) {
            int[] dampenedArray = ArrayUtils.removeElement(array, i);
            if (validator.isSafe(dampenedArray)) {
                return true;
            }
        }

        return false;
    }

}