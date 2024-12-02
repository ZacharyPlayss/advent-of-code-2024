package zvds;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1p1Main {
    public static void main(String[] args) {
        int[][] allArrays= readArraysFromCSV("E:/Development/Java/adventofcode2024/Day1p1/src/main/resources/input-sheet.csv");
        int[] ia1 = allArrays[0];
        int[] ia2 = allArrays[1];

        ia1 = Arrays.stream(ia1).sorted().toArray();
        ia2 = Arrays.stream(ia2).sorted().toArray();


        for (int i = 0 ; i < ia1.length; i++){
            System.out.print(ia1[i] + " ");
        }
        System.out.println();
        for (int i = 0 ; i < ia2.length; i++){
            System.out.print(ia2[i] + " ");
        }
        System.out.println();
        int totalDistance = calculateListDifference(ia1, ia2);
        System.out.println("TOTALDISTANCE " + totalDistance);
    }

    private static int calculateListDifference(int[] inp1, int[] inp2){
        int totalDistance = 0;
        for(int i =0; i< inp1.length; i++){
            totalDistance += calculateNumberDifference(inp1[i] ,inp2[i]);
        }
        return totalDistance;
    }

    private static int calculateNumberDifference(int num1, int num2){
        return Math.abs(num1 - num2);
    }
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