package zvds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final int[][] DIRECTIONS = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    private static final Map<Integer, Integer> PERIMETER_ADJACENT_MAP = Map.of(
            0, 4,
            1, 3,
            2, 2,
            3, 1,
            4, 0
    );

    private static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public static List<String> readInputFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.strip());
            }
        }
        return lines;
    }

    public static int getPerimeterCount(int adjacentCount) {
        return PERIMETER_ADJACENT_MAP.get(adjacentCount);
    }

    public static void solution(List<String> lines) {
        boolean[][] visited = new boolean[lines.size()][lines.get(0).length()];
        int totalPrice = 0;

        for (int r = 0; r < lines.size(); r++) {
            for (int c = 0; c < lines.get(r).length(); c++) {
                if (visited[r][c]) continue;

                char plant = lines.get(r).charAt(c);
                Queue<Point> queue = new ArrayDeque<>();
                queue.add(new Point(r, c));

                Set<Point> region = new HashSet<>();
                while (!queue.isEmpty()) {
                    Point current = queue.poll();

                    if (visited[current.r][current.c]) continue;
                    visited[current.r][current.c] = true;
                    region.add(current);

                    for (int[] direction : DIRECTIONS) {
                        int rN = current.r + direction[0];
                        int cN = current.c + direction[1];

                        if (rN >= 0 && rN < lines.size() && cN >= 0 && cN < lines.get(r).length()
                                && lines.get(rN).charAt(cN) == plant && !visited[rN][cN]) {
                            queue.add(new Point(rN, cN));
                        }
                    }
                }

                // Calculate Boundary
                int area = region.size();
                int perimeter = 0;

                for (Point point : region) {
                    int adjCount = 0;
                    for (int[] direction : DIRECTIONS) {
                        int rN = point.r + direction[0];
                        int cN = point.c + direction[1];

                        if (rN >= 0 && rN < lines.size() && cN >= 0 && cN < lines.get(0).length()
                                && region.contains(new Point(rN, cN))) {
                            adjCount++;
                        }
                    }
                    perimeter += getPerimeterCount(adjCount);
                }

                totalPrice += (area * perimeter);
            }
        }

        System.out.println(totalPrice);
    }

    public static void main(String[] args) {
        try {
            List<String> lines = readInputFile("E:\\Development\\Java\\adventofcode2024\\Day12p1\\src\\main\\resources\\input.txt");
            solution(lines);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
