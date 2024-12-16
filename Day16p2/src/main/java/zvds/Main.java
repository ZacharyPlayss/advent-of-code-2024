package zvds;

import java.io.*;
import java.util.*;

public class Main {

    private static final String FILENAME = "E:\\Development\\Java\\adventofcode2024\\Day16p2\\src\\main\\resources\\input.txt";
    private static final char START = 'S';
    private static final char END = 'E';
    private static final char WALL = '#';
    private static final char OPEN = '.';
    private static final int DIRECTION_COST = 1000;

    private static final int[][] DIRECTIONS = {
            {0, 1},   // Right
            {-1, 0},  // Up
            {0, -1},  // Left
            {1, 0}    // Down
    };

    public static void main(String[] args) throws IOException {
        Map<Position, Character> maze = parseInput(FILENAME);
        Position start = findInMaze(maze, START);
        Position end = findInMaze(maze, END);

        maze.put(start, OPEN);
        maze.put(end, OPEN);

        Map<Position, Map<Direction, State>> seenStates = dijkstra(maze, start);
        int[] result = solve(seenStates, end);

        System.out.println("Minimum score: " + result[0]);
        System.out.println("Unique tiles visited: " + result[1]);
    }

    private static Map<Position, Character> parseInput(String filename) throws IOException {
        Map<Position, Character> maze = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    maze.put(new Position(row, col), line.charAt(col));
                }
                row++;
            }
        }
        return maze;
    }

    private static Position findInMaze(Map<Position, Character> maze, char target) {
        return maze.entrySet().stream()
                .filter(entry -> entry.getValue() == target)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Target not found: " + target));
    }

    private static Map<Position, Map<Direction, State>> dijkstra(Map<Position, Character> maze, Position start) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.score));
        Map<Position, Map<Direction, State>> seenStates = new HashMap<>();

        for (Position pos : maze.keySet()) {
            Map<Direction, State> directionStates = new HashMap<>();
            for (int[] dir : DIRECTIONS) {
                directionStates.put(new Direction(dir[0], dir[1]), new State(Integer.MAX_VALUE, new HashSet<>()));
            }
            seenStates.put(pos, directionStates);
        }

        queue.add(new Node(0, start, new Direction(0, 1), new HashSet<>()));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            Position position = current.position;
            Direction direction = current.direction;

            int[][] allowedDirections = Arrays.stream(DIRECTIONS)
                    .filter(dir -> !(dir[0] == -direction.dr && dir[1] == -direction.dc))
                    .toArray(int[][]::new);

            for (int[] dir : allowedDirections) {
                int newRow = position.row + dir[0];
                int newCol = position.col + dir[1];
                Position newPos = new Position(newRow, newCol);

                if (!maze.containsKey(newPos) || maze.get(newPos) == WALL) {
                    continue;
                }

                int newScore = current.score + 1 + (dir[0] != direction.dr || dir[1] != direction.dc ? DIRECTION_COST : 0);
                Direction newDirection = new Direction(dir[0], dir[1]);
                State state = seenStates.get(newPos).get(newDirection);

                if (newScore > state.score) {
                    continue;
                }

                Set<Position> newTiles = new HashSet<>(current.visited);
                newTiles.add(newPos);

                if (newScore < state.score) {
                    state.score = newScore;
                    state.visited = newTiles;
                } else if (newScore == state.score) {
                    state.visited.addAll(newTiles);
                }

                queue.add(new Node(newScore, newPos, newDirection, state.visited));
            }
        }

        return seenStates;
    }

    private static int[] solve(Map<Position, Map<Direction, State>> seenStates, Position end) {
        State bestState = seenStates.get(end).values().stream()
                .min(Comparator.comparingInt(s -> s.score))
                .orElseThrow(() -> new IllegalArgumentException("No solution found"));
        return new int[]{bestState.score, bestState.visited.size() + 1};
    }

    private static class Position {
        int row, col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row && col == position.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    private static class Direction {
        int dr, dc;

        Direction(int dr, int dc) {
            this.dr = dr;
            this.dc = dc;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Direction direction = (Direction) o;
            return dr == direction.dr && dc == direction.dc;
        }

        @Override
        public int hashCode() {
            return Objects.hash(dr, dc);
        }
    }

    private static class State {
        int score;
        Set<Position> visited;

        State(int score, Set<Position> visited) {
            this.score = score;
            this.visited = visited;
        }
    }

    private static class Node {
        int score;
        Position position;
        Direction direction;
        Set<Position> visited;

        Node(int score, Position position, Direction direction, Set<Position> visited) {
            this.score = score;
            this.position = position;
            this.direction = direction;
            this.visited = visited;
        }
    }
}
