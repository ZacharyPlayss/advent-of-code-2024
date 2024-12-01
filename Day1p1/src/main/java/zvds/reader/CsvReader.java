package zvds.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
    private static int[][] readArraysFromCSV(String filePath)  {
        List<Integer> column1 = new ArrayList<>();
        List<Integer> column2 = new ArrayList<>();

        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                if (values.length >= 2) {
                    column1.add(Integer.parseInt(values[0].trim()));
                    column2.add(Integer.parseInt(values[1].trim()));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int[] array1 = column1.stream().mapToInt(Integer::intValue).toArray();
        int[] array2 = column2.stream().mapToInt(Integer::intValue).toArray();

        return new int[][]{array1, array2};
    }
}
