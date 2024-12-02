package zvds;

import zvds.reader.TxtReader;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        final int maxDifference = 3;

        int[][] input = {{7,6,4,2,1}, {1,2,7,8,9},{9,7,6,2,1}, {1,3,2,4,5},{8,6,4,4,1},{1,3,6,7,9}};
        int[][] actualInput = TxtReader.readFileTo2DArray("E:\\Development\\Java\\adventofcode2024\\Day2p1\\src\\main\\resources\\input.txt");
        List<int[]> safeArrays = new ArrayList<>();

        for(int i =0; i < actualInput.length; i++) {
            if(isSafe(actualInput[i], maxDifference)) {
                System.out.println("Arr 1 is Safe");
                safeArrays.add(actualInput[i]);
            }
            else {
                System.out.println("Arr 1 is Unsafe");
            }
        }

        System.out.println(safeArrays.stream().count() + " arrays are safe!");

    }

    public static void visualizeData(int[][] input){
        for(int i = 0; i< input.length; i++){
            for(int j = 0; j < input[i].length; j++){
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static boolean isSafe(int[] levels, int maxDifference){
        boolean isIncreasing = levels[1] > levels[0];
        for (int i = 1; i < levels.length; i++) {
            int difference = levels[i] - levels[i - 1];
            if (Math.abs(difference) < 1 || Math.abs(difference) > maxDifference) {
                return false;
            }
            if ((difference > 0) != isIncreasing) {
                return false;
            }
        }

        return true;
    }


}