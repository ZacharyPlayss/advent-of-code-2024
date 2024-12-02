package zvds.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtReader {
    public static int[][] readFileTo2DArray(String filePath) {
        List<int[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" ");
                int[] array = Arrays.stream(values)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                list.add(array);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.toArray(new int[0][]);
    }
}