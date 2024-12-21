package zvds.navigator;

import zvds.domain.keypad.NumericKeyPad;

import java.util.*;

public class NumericKeypadNavigator {
    private final NumericKeyPad numericKeyPad;
    private int currentRow;
    private int currentCol;

    public NumericKeypadNavigator(NumericKeyPad numericKeyPad) {
        this.numericKeyPad = numericKeyPad;
        String[][] layout = numericKeyPad.getLayout();
        for (int row = 0; row < layout.length; row++) {
            for (int col = 0; col < layout[row].length; col++) {
                if ("A".equals(layout[row][col])) {
                    this.currentRow = row;
                    this.currentCol = col;
                    break;
                }
            }
        }
    }
    public String calculateCommands(String input) {
        StringBuilder commands = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char target : chars) {
            String path = findShortestPath(target);
            commands.append(path);
        }
        return commands.toString();
    }

    private String findShortestPath(char target) {
        String[][] layout = numericKeyPad.getLayout();
        int[] targetPos = findPosition(target, layout);
        if (targetPos == null) {
            throw new IllegalArgumentException("Invalid target: " + target);
        }
        // BFS Setup
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new State(currentRow, currentCol, ""));
        visited.add(currentRow + "," + currentCol);
        // BFS
        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            int row = currentState.row;
            int col = currentState.col;
            String path = currentState.path;
            if (row == targetPos[0] && col == targetPos[1]) {
                currentRow = row;
                currentCol = col;
                return path + "A";
            }
            for (Move move : Move.values()) {
                int newRow = row + move.rowDelta;
                int newCol = col + move.colDelta;

                if (isValid(newRow, newCol, layout) && !visited.contains(newRow + "," + newCol)) {
                    queue.add(new State(newRow, newCol, path + move.command));
                    visited.add(newRow + "," + newCol);
                }
            }
        }
        throw new IllegalStateException("No path found to target: " + target);
    }
    private boolean isValid(int row, int col, String[][] layout) {
        return row >= 0 && row < layout.length &&
                col >= 0 && col < layout[row].length &&
                layout[row][col] != null;
    }
    private int[] findPosition(char key, String[][] layout) {
        for (int row = 0; row < layout.length; row++) {
            for (int col = 0; col < layout[row].length; col++) {
                if (String.valueOf(key).equals(layout[row][col])) {
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }
    private enum Move {
        UP(-1, 0, "^"),
        DOWN(1, 0, "v"),
        LEFT(0, -1, "<"),
        RIGHT(0, 1, ">");

        final int rowDelta;
        final int colDelta;
        final String command;

        Move(int rowDelta, int colDelta, String command) {
            this.rowDelta = rowDelta;
            this.colDelta = colDelta;
            this.command = command;
        }
    }

    private static class State {
        final int row;
        final int col;
        final String path;

        State(int row, int col, String path) {
            this.row = row;
            this.col = col;
            this.path = path;
        }
    }
}
