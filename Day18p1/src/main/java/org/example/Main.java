package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {

    private List<Maze.Point> bytePositions;

    public void readInput(Input input) {
        bytePositions = input.asLines().stream()
                .map(line -> {
                    String[] parts = line.split(",");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    return new Maze.Point(x, y);
                })
                .collect(Collectors.toList());
    }

    public void part1() {
        Set<Maze.Point> map = new HashSet<>();
        for (int i = 0; i < 1024; i++) {
            map.add(bytePositions.get(i));
        }

        Maze maze = new Maze(p -> {
            if (p.x > 70 || p.x < 0 || p.y > 70 || p.y < 0) {
                return false;
            }
            return !map.contains(p);
        });

        List<Maze.Point> route = maze.findShortestRoute(new Maze.Point(0, 0), new Maze.Point(70, 70));
        System.out.println(route.size() - 1);
    }

    public void part2() {
        Set<Maze.Point> map = new HashSet<>();
        for (int i = 0; i < 1024; i++) {
            map.add(bytePositions.get(i));
        }

        Maze maze = new Maze(p -> {
            if (p.x > 70 || p.x < 0 || p.y > 70 || p.y < 0) {
                return false;
            }
            return !map.contains(p);
        });

        int byteIndex = 1024;
        while (true) {
            Maze.Point pos = bytePositions.get(byteIndex);
            map.add(pos);

            List<Maze.Point> route = maze.findShortestRoute(new Maze.Point(0, 0), new Maze.Point(70, 70));
            if (route.isEmpty()) {
                System.out.printf("%d,%d%n", pos.x, pos.y);
                break;
            }

            byteIndex++;
        }
    }

    public static class Input {
        private final List<String> lines;

        public Input(String filePath) throws IOException {
            lines = Files.readAllLines(Paths.get(filePath));
        }

        public List<String> asLines() {
            return lines;
        }
    }

    public static class Maze {
        private final PositionValidator positionValidator;

        public Maze(PositionValidator positionValidator) {
            this.positionValidator = positionValidator;
        }

        public List<Point> findShortestRoute(Point start, Point end) {
            Set<Point> visited = new HashSet<>();
            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(start, new ArrayList<>(List.of(start))));

            while (!queue.isEmpty()) {
                Node current = queue.poll();

                if (visited.contains(current.point)) {
                    continue;
                }
                if (current.point.equals(end)) {
                    return current.path;
                }

                visited.add(current.point);

                for (int[] direction : new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
                    Point neighbor = new Point(current.point.x + direction[0], current.point.y + direction[1]);
                    if (positionValidator.isValid(neighbor) && !visited.contains(neighbor)) {
                        List<Point> newPath = new ArrayList<>(current.path);
                        newPath.add(neighbor);
                        queue.add(new Node(neighbor, newPath));
                    }
                }
            }

            return Collections.emptyList();
        }

        public interface PositionValidator {
            boolean isValid(Point p);
        }

        public static class Point {
            final int x, y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Point point = (Point) obj;
                return x == point.x && y == point.y;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y);
            }

            @Override
            public String toString() {
                return String.format("(%d, %d)", x, y);
            }
        }

        private static class Node {
            final Point point;
            final List<Point> path;

            public Node(Point point, List<Point> path) {
                this.point = point;
                this.path = path;
            }
        }
    }

    public static void main(String[] args) {
        try {
            Input input = new Input("C:\\Users\\zachs\\Desktop\\PRIVATE\\DEVELOPMENT\\java\\advent-of-code-2024\\Day18p1\\src\\main\\resources\\input.txt");
            Main day18 = new Main();
            day18.readInput(input);

            System.out.println("Part 1:");
            day18.part1();

            System.out.println("Part 2:");
            day18.part2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}