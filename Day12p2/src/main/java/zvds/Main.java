package zvds;

import java.io.*;
import java.util.*;

public class Main {

    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static List<String> readInputFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.strip());
            }
        }
        return lines;
    }

    public static int getTurnContributionPairs(int[] point, int[][] adjacentNodes, Set<List<Integer>> nodes) {
        int r = point[0], c = point[1];
        int rA = adjacentNodes[0][0], cA = adjacentNodes[0][1];
        int rB = adjacentNodes[1][0], cB = adjacentNodes[1][1];

        if (rA == rB || cA == cB) return 0;

        if (rA == r && nodes.contains(Arrays.asList(rB, cA))) return 1;
        if (rB == r && nodes.contains(Arrays.asList(rA, cB))) return 1;
        if (rA == r && !nodes.contains(Arrays.asList(rB, cA))) return 2;
        if (rB == r && !nodes.contains(Arrays.asList(rA, cB))) return 2;

        return 0;
    }

    public static int getTurnContributionMultiplePairs(int[] point, int[][] adjacentNodes, Set<List<Integer>> nodes) {
        int r = point[0], c = point[1];
        int rA = adjacentNodes[0][0], cA = adjacentNodes[0][1];
        int rB = adjacentNodes[1][0], cB = adjacentNodes[1][1];

        if (rA == rB || cA == cB) return 0;

        if (rA == r && nodes.contains(Arrays.asList(rB, cA))) return 0;
        if (rB == r && nodes.contains(Arrays.asList(rA, cB))) return 0;
        if (rA == r && !nodes.contains(Arrays.asList(rB, cA))) return 1;
        if (rB == r && !nodes.contains(Arrays.asList(rA, cB))) return 1;

        return 0;
    }

    public static int getPerimeter(Set<List<Integer>> nodes) {
        int perimeter = 0;

        for (List<Integer> node : nodes) {
            int r = node.get(0), c = node.get(1);
            List<int[]> adjNodes = new ArrayList<>();

            for (int[] direction : DIRECTIONS) {
                int rN = r + direction[0];
                int cN = c + direction[1];

                if (nodes.contains(Arrays.asList(rN, cN))) {
                    adjNodes.add(new int[]{rN, cN});
                }
            }

            if (adjNodes.isEmpty()) {
                perimeter += 4;
            } else if (adjNodes.size() == 1) {
                perimeter += 2;
            } else if (adjNodes.size() == 2) {
                perimeter += getTurnContributionPairs(new int[]{r, c}, adjNodes.toArray(new int[0][0]), nodes);
            } else {
                for (int i = 0; i < adjNodes.size() - 1; i++) {
                    for (int j = i + 1; j < adjNodes.size(); j++) {
                        perimeter += getTurnContributionMultiplePairs(new int[]{r, c}, new int[][]{adjNodes.get(i), adjNodes.get(j)}, nodes);
                    }
                }
            }
        }

        return perimeter;
    }

    public static void solution(List<String> lines) {
        Set<List<Integer>> plantSet = new HashSet<>();

        for (int r = 0; r < lines.size(); r++) {
            for (int c = 0; c < lines.get(r).length(); c++) {
                plantSet.add(Arrays.asList(r, c));
            }
        }

        int totalPrice = 0;

        while (!plantSet.isEmpty()) {
            List<Integer> searchPlant = plantSet.iterator().next();
            plantSet.remove(searchPlant);
            Deque<List<Integer>> queue = new ArrayDeque<>();
            Set<List<Integer>> visited = new HashSet<>();

            queue.add(searchPlant);

            while (!queue.isEmpty()) {
                List<Integer> current = queue.pollLast();
                int r = current.get(0), c = current.get(1);
                visited.add(Arrays.asList(r, c));

                for (int[] direction : DIRECTIONS) {
                    int rN = r + direction[0];
                    int cN = c + direction[1];

                    if (rN < 0 || rN >= lines.size() || cN < 0 || cN >= lines.get(0).length()) continue;
                    if (lines.get(rN).charAt(cN) != lines.get(r).charAt(c)) continue;
                    if (visited.contains(Arrays.asList(rN, cN))) continue;

                    queue.add(Arrays.asList(rN, cN));
                }
            }

            int area = visited.size();
            int perimeter = getPerimeter(visited);

            totalPrice += area * perimeter;

            plantSet.removeAll(visited);
        }

        System.out.println(totalPrice);
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = readInputFile("E:\\Development\\Java\\adventofcode2024\\Day12p2\\src\\main\\resources\\input.txt");
        solution(lines);
    }
}
