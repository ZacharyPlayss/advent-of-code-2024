package zvds;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day6p2Main {

    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("E:\\Development\\Java\\adventofcode2024\\Day6p2\\src\\main\\resources\\input.txt"));
        char[][] grid = parseInput(input);
        grid = padGrid(grid);

        int[] start = findStart(grid, '^');

        // Part 1
        char[][] part1Grid = deepCopy(grid);
        int direction = 0;
        int[] pos = Arrays.copyOf(start, 2);

        while (part1Grid[pos[0] + DIRECTIONS[direction][0]][pos[1] + DIRECTIONS[direction][1]] != '0') {
            if (part1Grid[pos[0] + DIRECTIONS[direction][0]][pos[1] + DIRECTIONS[direction][1]] == '#') {
                direction = (direction + 1) % 4;
            } else {
                pos[0] += DIRECTIONS[direction][0];
                pos[1] += DIRECTIONS[direction][1];
                part1Grid[pos[0]][pos[1]] = 'X';
            }
        }

        int xCount = countOccurrences(part1Grid, 'X');
        System.out.println(xCount);

        // Part 2
        int count = 0;

        for (int i = 0; i < part1Grid.length; i++) {
            for (int j = 0; j < part1Grid[0].length; j++) {
                if (part1Grid[i][j] == 'X') {
                    char[][] part2Grid = deepCopy(grid);
                    part2Grid[i][j] = '#';
                    int[][] turns = new int[grid.length][grid[0].length];
                    direction = 0;
                    pos = Arrays.copyOf(start, 2);

                    while (part2Grid[pos[0] + DIRECTIONS[direction][0]][pos[1] + DIRECTIONS[direction][1]] != '0') {
                        if (part2Grid[pos[0] + DIRECTIONS[direction][0]][pos[1] + DIRECTIONS[direction][1]] == '#') {
                            if ((turns[pos[0]][pos[1]] & (1 << direction)) != 0) {
                                count++;
                                break;
                            }
                            turns[pos[0]][pos[1]] |= (1 << direction);
                            direction = (direction + 1) % 4;
                        } else {
                            pos[0] += DIRECTIONS[direction][0];
                            pos[1] += DIRECTIONS[direction][1];
                            part2Grid[pos[0]][pos[1]] = 'X';
                        }
                    }
                }
            }
        }

        System.out.println(count);
    }

    private static char[][] parseInput(String input) {
        String[] lines = input.split("\\n");
        char[][] grid = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            grid[i] = lines[i].toCharArray();
        }
        return grid;
    }

    private static char[][] padGrid(char[][] grid) {
        // Find the maximum width of rows to handle non-uniform lengths
        int maxCols = 0;
        for (char[] row : grid) {
            maxCols = Math.max(maxCols, row.length);
        }

        // Define padded grid dimensions
        int rows = grid.length + 2;
        int cols = maxCols + 2;
        char[][] paddedGrid = new char[rows][cols];

        // Fill the padded grid with default '0' characters
        for (char[] row : paddedGrid) {
            Arrays.fill(row, '0');
        }

        // Copy the original grid into the padded grid
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, paddedGrid[i + 1], 1, grid[i].length);
        }

        return paddedGrid;
    }

    private static int[] findStart(char[][] grid, char startChar) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == startChar) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("Start character not found");
    }

    private static char[][] deepCopy(char[][] grid) {
        char[][] copy = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, grid[0].length);
        }
        return copy;
    }

    private static int countOccurrences(char[][] grid, char target) {
        int count = 0;
        for (char[] row : grid) {
            for (char c : row) {
                if (c == target) {
                    count++;
                }
            }
        }
        return count;
    }
}
