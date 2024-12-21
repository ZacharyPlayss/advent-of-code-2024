package zvds.navigator;

import zvds.domain.keypad.KeyPadInterface;

import java.util.*;

public class KeypadNavigator {
    private final KeyPadInterface keyPad;
    private int currentRow;
    private int currentCol;
    private Map<String, String> pathCache;  // Cache for paths

    public KeypadNavigator(KeyPadInterface keyPad) {
        this.keyPad = keyPad;
        this.pathCache = new HashMap<>();
        String[][] layout = keyPad.getLayout();
        for (int row = 0; row < layout.length; row++) {
            for (int col = 0; col < layout[row].length; col++) {
                if ("A".equals(layout[row][col])) {
                    this.currentRow = row;
                    this.currentCol = col;
                    return;
                }
            }
        }
    }

    public String calculateCommands(String input) {
        StringBuilder commands = new StringBuilder();
        char[] chars = input.toCharArray();
        String prevChar = "A";

        for (char target : chars) {
            String path = getOptimizedPath(prevChar, String.valueOf(target));
            String optimizedPath = optimizePath(path);
            commands.append(optimizedPath);
            prevChar = String.valueOf(target);
        }

        return commands.toString();
    }

    private String getOptimizedPath(String start, String target) {
        String cacheKey = start + target;
        if (pathCache.containsKey(cacheKey)) {
            return pathCache.get(cacheKey);
        }

        String path = findAStarPath(start, target);
        pathCache.put(cacheKey, path);
        return path;
    }

    private String findAStarPath(String start, String target) {
        String[][] layout = keyPad.getLayout();
        int[] startPos = findPosition(start.charAt(0), layout);
        int[] targetPos = findPosition(target.charAt(0), layout);

        if (startPos == null || targetPos == null) {
            throw new IllegalArgumentException("Invalid start or target position");
        }

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost + heuristic(s.row, s.col, targetPos)));
        Set<String> visited = new HashSet<>();

        pq.add(new State(startPos[0], startPos[1], "", 0));

        while (!pq.isEmpty()) {
            State currentState = pq.poll();
            int row = currentState.row;
            int col = currentState.col;

            if (layout[row][col].equals(target)) {
                return currentState.path + "A";
            }

            for (Move move : Move.values()) {
                int newRow = row + move.rowDelta;
                int newCol = col + move.colDelta;

                if (isValid(newRow, newCol, layout) && !visited.contains(newRow + "," + newCol)) {
                    int newCost = currentState.cost + 1;
                    pq.add(new State(newRow, newCol, currentState.path + move.command, newCost));
                    visited.add(newRow + "," + newCol);
                }
            }
        }

        return "";
    }

    private int heuristic(int row, int col, int[] targetPos) {
        return Math.abs(row - targetPos[0]) + Math.abs(col - targetPos[1]);
    }

    private boolean isValid(int row, int col, String[][] layout) {
        return row >= 0 && row < layout.length && col >= 0 && col < layout[row].length && layout[row][col] != null;
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

    private String optimizePath(String path) {
        StringBuilder optimizedPath = new StringBuilder();
        Stack<Character> directionStack = new Stack<>();

        for (char currentCommand : path.toCharArray()) {
            if (!directionStack.isEmpty() && isOpposite(directionStack.peek(), currentCommand)) {
                directionStack.pop();
            } else {
                directionStack.push(currentCommand);
            }
        }

        while (!directionStack.isEmpty()) {
            optimizedPath.insert(0, directionStack.pop());
        }

        return optimizedPath.toString();
    }

    private boolean isOpposite(char prevCommand, char currentCommand) {
        return (prevCommand == '^' && currentCommand == 'v') ||
                (prevCommand == 'v' && currentCommand == '^') ||
                (prevCommand == '<' && currentCommand == '>') ||
                (prevCommand == '>' && currentCommand == '<');
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
        final int cost;

        State(int row, int col, String path, int cost) {
            this.row = row;
            this.col = col;
            this.path = path;
            this.cost = cost;
        }
    }
}
