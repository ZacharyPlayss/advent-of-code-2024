package zvds;

import java.io.*;
import java.util.*;

public class Main {
    static List<String> unvisitedNodes = new ArrayList<>();
    static Map<String, Integer> nodes = new HashMap<>();
    static char[][] grid;
    static int sr = -1, sc = -1, er = -1, ec = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Development\\Java\\adventofcode2024\\Day16p1\\src\\main\\resources\\input.txt"));
        List<String> lines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        int rows = lines.size();
        int cols = lines.get(0).length();
        grid = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = lines.get(r).charAt(c);
                if (grid[r][c] == 'S') {
                    sr = r;
                    sc = c;
                    grid[r][c] = '.';
                } else if (grid[r][c] == 'E') {
                    er = r;
                    ec = c;
                    grid[r][c] = '.';
                }
            }
        }

        // Initialize nodes with -1 cost and add them to unvisitedNodes
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                if (grid[r][c] == '.') {
                    addNode(r, c, -1, 0);
                    addNode(r, c, 1, 0);
                    addNode(r, c, 0, -1);
                    addNode(r, c, 0, 1);
                }
            }
        }
        addNode(sr, sc, 0, 1, 0);

        // Process the nodes to find the cheapest path
        while (!unvisitedNodes.isEmpty()) {
            String node = getCheapestUnvisitedNode();
            unvisitedNodes.remove(node);

            int[] nv = getNodeValues(node);
            int cr = nv[0], cc = nv[1], dr = nv[2], dc = nv[3];

            if (cr == er && cc == ec) {
                System.out.println(nodes.get(node));
                break;
            }

            // Continue in the current direction
            processNode(cr + dr, cc + dc, dr, dc, nodes.get(node) + 1);

            // Turn counterclockwise
            processNode(cr, cc, -dc, dr, nodes.get(node) + 1000);

            // Turn clockwise
            processNode(cr, cc, dc, -dr, nodes.get(node) + 1000);
        }
    }

    private static void addNode(int r, int c, int dr, int dc) {
        addNode(r, c, dr, dc, -1);
    }

    private static void addNode(int r, int c, int dr, int dc, int cost) {
        String key = getNodeKey(r, c, dr, dc);
        nodes.put(key, cost);
        unvisitedNodes.add(key);
    }

    private static String getNodeKey(int r, int c, int dr, int dc) {
        return r + "," + c + "," + dr + "," + dc;
    }

    private static int[] getNodeValues(String node) {
        String[] parts = node.split(",");
        return new int[]{
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3])
        };
    }

    private static String getCheapestUnvisitedNode() {
        String cheapestNode = null;
        int cheapestCost = Integer.MAX_VALUE;

        for (String node : unvisitedNodes) {
            int cost = nodes.getOrDefault(node, -1);
            if (cost >= 0 && cost < cheapestCost) {
                cheapestNode = node;
                cheapestCost = cost;
            }
        }

        return cheapestNode;
    }

    private static void processNode(int r, int c, int dr, int dc, int cost) {
        String key = getNodeKey(r, c, dr, dc);
        if (nodes.containsKey(key) && (nodes.get(key) == -1 || nodes.get(key) > cost)) {
            nodes.put(key, cost);
        }
    }
}
