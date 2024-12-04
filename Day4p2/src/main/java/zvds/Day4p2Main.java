package zvds;

import zvds.counter.FormationCounter;
import zvds.manager.WordManager;
import zvds.reader.TxtReader;

import java.util.List;

public class Day4p2Main {
    public static void main(String[] args) {
        char[][] input = TxtReader.readFileToChar2DArray("E:/Development/Java/adventofcode2024/Day4p2/src/main/resources/input.txt");
        //char[][] input = TxtReader.readFileToChar2DArray("E:/Development/Java/adventofcode2024/Day4p1/src/main/resources/test-input.txt");
        int[][] directions = {
                {-1, -1}, // Up-Left
                {-1, 1},  // Up-Right
                {1, -1},  // Down-Left
                {1, 1}    // Down-Right
        };
        WordManager wordManager = new WordManager(directions);
        FormationCounter formationCounter = new FormationCounter();

        String word = "MAS";
        List<List<int[]>> results = wordManager.searchWord(input, word);
        System.out.println("Occurrences of the word \"" + word + "\":");
        for (List<int[]> path : results) {
            System.out.println("Found word at the following positions:");
            for (int[] position : path) {
                System.out.println("Row: " + position[0] + ", Col: " + position[1]);
            }
            System.out.println("----");
        }
        int mountOfXFormations = formationCounter.countXFormations(results);
        System.out.println("The word \"" + word + "\" appears a total of " + results.size() + " times in the grid.");
        System.out.println("The word \"" + word + "\" appears a total of " + mountOfXFormations + " times in the grid.");
    }

}
