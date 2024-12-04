package zvds;

import zvds.manager.WordManager;
import zvds.reader.TxtReader;

import java.util.List;

public class Day4p1Main {
    public static void main(String[] args) {
        char[][] input = TxtReader.readFileToChar2DArray("E:/Development/Java/adventofcode2024/Day4p1/src/main/resources/input.txt");
        //char[][] input = TxtReader.readFileToChar2DArray("E:/Development/Java/adventofcode2024/Day4p1/src/main/resources/test-input.txt");

        String word = "XMAS";
        int[][] directions = {
                {-1, 0}, // Up
                {1, 0},  // Down
                {0, -1}, // Left
                {0, 1},  // Right
                {-1, -1}, // Up-Left
                {-1, 1},  // Up-Right
                {1, -1},  // Down-Left
                {1, 1}    // Down-Right
        };

        WordManager wordManager = new WordManager(directions);
        List<int[]> results = wordManager.searchWord(input, word);

        for (int[] position : results) {
            System.out.println("Found at row: " + position[0] + ", col: " + position[1]);
        }
        System.out.println("The input list takes a total of " + results.size() + " occurences.");
    }


}
