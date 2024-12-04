package zvds.manager;

import java.util.ArrayList;
import java.util.List;

public class WordManager {
    private final int[][] DIRECTIONS;

    public WordManager(int[][] directions) {
        DIRECTIONS = directions;
    }

    public List<List<int[]>> searchWord(char[][] grid, String word) {
        List<List<int[]>> foundPositions = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Check if word can start here
                if (grid[r][c] == word.charAt(0)) {
                    // Search in all directions
                    for (int[] direction : DIRECTIONS) {
                        List<int[]> path = checkWordAndRecordPath(grid, word, r, c, direction);
                        if (path != null) {
                            foundPositions.add(path);
                        }
                    }
                }
            }
        }
        return foundPositions;
    }
    private List<int[]> checkWordAndRecordPath(char[][] grid, String word, int row, int col, int[] direction) {
        int rows = grid.length;
        int cols = grid[0].length;
        int wordLen = word.length();
        List<int[]> path = new ArrayList<>();

        for (int i = 0; i < wordLen; i++) {
            int newRow = row + i * direction[0];
            int newCol = col + i * direction[1];

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || grid[newRow][newCol] != word.charAt(i)) {
                return null;
            }
            path.add(new int[]{newRow, newCol});
        }

        return path;
    }
}
