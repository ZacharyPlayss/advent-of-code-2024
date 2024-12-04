package zvds.manager;

import java.util.ArrayList;
import java.util.List;

public class WordManager {

    private final int[][] DIRECTIONS;

    public WordManager(int[][] directions) {
        DIRECTIONS = directions;
    }

    public List<int[]> searchWord(char[][] grid, String word) {
        List<int[]> foundPositions = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;

        // Traverse each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Check if word can start here
                if (grid[r][c] == word.charAt(0)) {
                    // Search in all directions
                    for (int[] direction : DIRECTIONS) {
                        if (checkWord(grid, word, r, c, direction)) {
                            foundPositions.add(new int[]{r, c}); // Record start position
                        }
                    }
                }
            }
        }

        return foundPositions;
    }

    private boolean checkWord(char[][] grid, String word, int row, int col, int[] direction) {
        int rows = grid.length;
        int cols = grid[0].length;
        int wordLen = word.length();
        for (int i = 0; i < wordLen; i++) {
            int newRow = row + i * direction[0];
            int newCol = col + i * direction[1];
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || grid[newRow][newCol] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
