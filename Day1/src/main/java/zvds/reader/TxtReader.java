package zvds.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtReader {

    public static int[][] readFileTo2DArrayAsColumns(File file) {
        List<Integer> column1 = new ArrayList<>();
        List<Integer> column2 = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 2) {
                    column1.add(Integer.parseInt(parts[0]));
                    column2.add(Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        int[] array1 = column1.stream().mapToInt(Integer::intValue).toArray();
        int[] array2 = column2.stream().mapToInt(Integer::intValue).toArray();

        return new int[][]{array1, array2};
    }
}
