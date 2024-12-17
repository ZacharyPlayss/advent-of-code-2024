package zvds.validator;

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
}