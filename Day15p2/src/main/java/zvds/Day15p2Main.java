package zvds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day15p2Main {
    private static final Map<Character, int[]> dirs = Map.of(
            'v', new int[]{1, 0},
            '^', new int[]{-1, 0},
            '>', new int[]{0, 1},
            '<', new int[]{0, -1}
    );

    private static List<String> input;
    private static int rows, cols;

    public static void main(String[] args) throws IOException {
        input = readInput("E:\\Development\\Java\\adventofcode2024\\Day15p2\\src\\main\\resources\\input.txt");
        solvePart1();
        solvePart2();
    }

    private static List<String> readInput(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static void visualiseGrid(Map<Point, Character> grid) {
        for (int r = 0; r < rows; r++) {
            StringBuilder row = new StringBuilder();
            for (int c = 0; c < cols; c++) {
                row.append(grid.getOrDefault(new Point(r, c), ' '));
            }
            System.out.println(row);
        }
        System.out.println();
    }

    private static int scoreGrid(Map<Point, Character> grid, char ch) {
        int score = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid.getOrDefault(new Point(r, c), ' ') == ch) {
                    score += 100 * r + c;
                }
            }
        }
        return score;
    }

    private static void solvePart1() {
        Map<Point, Character> grid = new HashMap<>();
        Point robot = null;
        int i = 0;
        cols = input.get(0).length();
        while (i < input.size() && !input.get(i).isEmpty()) {
            for (int j = 0; j < input.get(i).length(); j++) {
                char ch = input.get(i).charAt(j);
                if (ch == '@') {
                    robot = new Point(i, j);
                }
                grid.put(new Point(i, j), ch);
            }
            i++;
        }
        rows = i;
        String moves = String.join("", input.subList(i + 1, input.size()));

        for (char move : moves.toCharArray()) {
            int[] dir = dirs.get(move);
            Point[] result = tryMove(grid, robot, dir);
            grid = result[0].grid;
            robot = result[1];
        }

        System.out.println("Part 1: " + scoreGrid(grid, 'O'));
    }

    private static void solvePart2() {
        Map<Point, Character> grid = new HashMap<>();
        Point robot = null;
        int i = 0;
        cols = 2 * input.get(0).length();
        while (i < input.size() && !input.get(i).isEmpty()) {
            for (int j = 0; j < input.get(i).length(); j++) {
                char val = input.get(i).charAt(j);
                char l, r;
                if (val == '.') {
                    l = r = '.';
                } else if (val == 'O') {
                    l = '[';
                    r = ']';
                } else if (val == '#') {
                    l = r = '#';
                } else {
                    l = '@';
                    r = '.';
                    robot = new Point(i, j * 2);
                }
                grid.put(new Point(i, j * 2), l);
                grid.put(new Point(i, j * 2 + 1), r);
            }
            i++;
        }
        rows = i;
        String moves = String.join("", input.subList(i + 1, input.size()));

        for (char move : moves.toCharArray()) {
            int[] dir = dirs.get(move);
            Point[] result = tryMove2(grid, robot, dir);
            grid = result[0].grid;
            robot = result[1];
        }

        System.out.println("Part 2: " + scoreGrid(grid, '['));
    }

    private static Point[] tryMove(Map<Point, Character> grid, Point robot, int[] dir) {
        Point nextPos = new Point(robot.r + dir[0], robot.c + dir[1]);
        char nextType = grid.getOrDefault(nextPos, '#');
        if (nextType == '#') {
            return new Point[]{new Point(grid), robot};
        } else if (nextType == '.') {
            grid.put(robot, '.');
            grid.put(nextPos, '@');
            return new Point[]{new Point(grid), nextPos};
        } else {
            Point nextCheckPos = new Point(nextPos.r + dir[0], nextPos.c + dir[1]);
            boolean movable = false;
            while (true) {
                char nextCheckType = grid.getOrDefault(nextCheckPos, '#');
                if (nextCheckType == '#') {
                    break;
                } else if (nextCheckType == 'O') {
                    nextCheckPos = new Point(nextCheckPos.r + dir[0], nextCheckPos.c + dir[1]);
                } else {
                    grid.put(nextCheckPos, 'O');
                    movable = true;
                    break;
                }
            }
            if (movable) {
                grid.put(robot, '.');
                grid.put(nextPos, '@');
                return new Point[]{new Point(grid), nextPos};
            }
            return new Point[]{new Point(grid), robot};
        }
    }

    private static Point[] tryMove2(Map<Point, Character> grid, Point robot, int[] dir) {
        Point nextPos = new Point(robot.r + dir[0], robot.c + dir[1]);
        char nextType = grid.getOrDefault(nextPos, '#');
        if (nextType == '#') {
            return new Point[]{new Point(grid), robot};
        } else if (nextType == '.') {
            grid.put(robot, '.');
            grid.put(nextPos, '@');
            return new Point[]{new Point(grid), nextPos};
        } else {
            if (dir[1] != 0) {  // Horizontal movement
                Point nextCheckPos = new Point(nextPos.r + dir[0], nextPos.c + dir[1]);
                boolean movable = false;
                while (true) {
                    char nextCheckType = grid.getOrDefault(nextCheckPos, '#');
                    if (nextCheckType == '#') {
                        break;
                    } else if (nextCheckType == '[' || nextCheckType == ']') {
                        nextCheckPos = new Point(nextCheckPos.r + dir[0], nextCheckPos.c + dir[1]);
                    } else {
                        movable = true;
                        break;
                    }
                }
                if (movable) {
                    grid.put(robot, '.');
                    grid.put(nextPos, '@');
                    Point boxPos = new Point(nextPos.r + dir[0], nextPos.c + dir[1]);
                    while (true) {
                        if (grid.getOrDefault(boxPos, ' ') == '.') {
                            grid.put(boxPos, dir[1] == -1 ? '[' : ']');
                            break;
                        } else {
                            grid.put(boxPos, grid.get(boxPos) == ']' ? '[' : ']');
                            boxPos = new Point(boxPos.r + dir[0], boxPos.c + dir[1]);
                        }
                    }
                    return new Point[]{new Point(grid), nextPos};
                }
                return new Point[]{new Point(grid), robot};
            } else {  // Vertical movement
                Point left = nextType == '[' ? nextPos : new Point(nextPos.r, nextPos.c - 1);
                if (moveable(grid, dir, left)) {
                    grid = actuallyMove(grid, dir, left, new HashSet<>());
                    grid.put(nextPos, '@');
                    grid.put(robot, '.');
                    return new Point[]{new Point(grid), nextPos};
                }
                return new Point[]{new Point(grid), robot};
            }
        }
    }

    private static boolean moveable(Map<Point, Character> grid, int[] dir, Point box) {
        Point nextLeftPos = new Point(box.r + dir[0], box.c);
        Point nextRightPos = new Point(nextLeftPos.r, nextLeftPos.c + 1);
        if (grid.getOrDefault(nextLeftPos, ' ') == '.' && grid.getOrDefault(nextRightPos, ' ') == '.') {
            return true;
        }
        if (grid.getOrDefault(nextLeftPos, ' ') == '#' || grid.getOrDefault(nextRightPos, ' ') == '#') {
            return false;
        }
        if (grid.getOrDefault(nextLeftPos, ' ') == '[') {
            return moveable(grid, dir, nextLeftPos);
        }
        if (grid.getOrDefault(nextLeftPos, ' ') == '.' && grid.getOrDefault(nextRightPos, ' ') == '[') {
            return moveable(grid, dir, nextRightPos);
        }
        if (grid.getOrDefault(nextLeftPos, ' ') == ']' && grid.getOrDefault(nextRightPos, ' ') == '.') {
            return moveable(grid, dir, new Point(nextLeftPos.r, nextLeftPos.c - 1));
        }
        return moveable(grid, dir, new Point(nextLeftPos.r, nextLeftPos.c - 1)) && moveable(grid, dir, nextRightPos);
    }

    private static Map<Point, Character> actuallyMove(Map<Point, Character> grid, int[] dir, Point box, Set<Point> moved) {
        if (moved.contains(box)) {
            return grid;
        }
        Point nextLeftPos = new Point(box.r + dir[0], box.c);
        Point nextRightPos = new Point(nextLeftPos.r, nextLeftPos.c + 1);
        if (grid.getOrDefault(nextLeftPos, ' ') == '.' && grid.getOrDefault(nextRightPos, ' ') == '.') {
            grid.put(nextLeftPos, '[');
            grid.put(nextRightPos, ']');
            grid.put(box, '.');
            grid.put(new Point(box.r, box.c + 1), '.');
            moved.add(box);
            return grid;
        }
        if (grid.getOrDefault(nextLeftPos, ' ') == '[') {
            grid = actuallyMove(grid, dir, nextLeftPos, moved);
        }
        if (grid.getOrDefault(nextLeftPos, ' ') == ']') {
            grid = actuallyMove(grid, dir, new Point(nextLeftPos.r, nextLeftPos.c - 1), moved);
        }
        if (grid.getOrDefault(nextRightPos, ' ') == '[') {
            grid = actuallyMove(grid, dir, nextRightPos, moved);
        }
        grid.put(nextLeftPos, '[');
        grid.put(nextRightPos, ']');
        grid.put(box, '.');
        grid.put(new Point(box.r, box.c + 1), '.');
        moved.add(box);
        return grid;
    }

    private static class Point {
        int r, c;
        Map<Point, Character> grid;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Point(Map<Point, Character> grid) {
            this.grid = new HashMap<>(grid);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
