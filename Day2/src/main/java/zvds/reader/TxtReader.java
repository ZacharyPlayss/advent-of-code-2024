package zvds.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtReader {
    public static int[][] readFileTo2DArray(File fileName) {
        List<int[]> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int[] row = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
                list.add(row);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return list.toArray(new int[0][]);
    }
}
