package zvds.validator.predicates;

import java.util.function.Predicate;

public class AdjacentLevelsDifferBy1To3Predicate implements Predicate<int[]> {

    @Override
    public boolean test(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int diff = Math.abs(array[i] - array[i + 1]);
            if (diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }
}