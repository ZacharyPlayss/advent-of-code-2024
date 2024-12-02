package zvds.validator.predicates;
import java.util.function.Predicate;

public class DecreasingPredicate implements Predicate<int[]> {

    @Override
    public boolean test(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] <= array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}