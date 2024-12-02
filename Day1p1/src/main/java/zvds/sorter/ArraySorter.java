package zvds.sorter;

import java.util.Arrays;

public class ArraySorter {
    public static int[] sortArray(int[] array) {
        return Arrays.stream(array).sorted().toArray();
    }
}
