package zvds.domain;

import java.util.*;

public class RaceTrack {
    char[][] grid;
    int[] startPosition;
    int[] endPosition;
    List<int[]> shortestPath;
    public RaceTrack(char[][] grid) {
        this.grid = grid;

        for (int i = 0; i < grid.length;i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 'S'){
                    this.startPosition= new int[]{i,j};
                }if(grid[i][j] == 'E'){
                    this.endPosition= new int[]{i,j};
                }
            }
        }
        shortestPath = findShortestPathWithSteps();
    }

    public List<int[]> findShortestPathWithSteps() {
        if (startPosition == null || endPosition == null) {
            throw new IllegalStateException("Start or end position not defined.");
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(startPosition);
        boolean[][] visited = new boolean[rows][cols];
        visited[startPosition[0]][startPosition[1]] = true;

        Map<String, String> path = new HashMap<>();
        path.put(Arrays.toString(startPosition), null);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == endPosition[0] && y == endPosition[1]) {
                return reconstructPath(path, startPosition, endPosition);
            }

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                    if (grid[newX][newY] != '#' && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        queue.add(new int[]{newX, newY});
                        path.put(Arrays.toString(new int[]{newX, newY}), Arrays.toString(current));
                    }
                }
            }
        }
        return null;
    }

    private List<int[]> reconstructPath(Map<String, String> path, int[] start, int[] end) {
        List<int[]> fullPath = new LinkedList<>();
        String current = Arrays.toString(end);

        while (current != null) {
            String[] parts = current.replace("[", "").replace("]", "").split(", ");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            fullPath.add(0, new int[]{x, y});
            current = path.get(current);
        }
        return fullPath;
    }

    public Map<Integer, Integer> findCheatPaths(int maxWallsToCheat) {
        Map<Integer, Integer> cheatCounts = new HashMap<>();
        if (shortestPath == null) return cheatCounts;

        int originalPathLength = shortestPath.size();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // Directions: up, down, left, right

        Set<String> alreadyCheckedCheats = new HashSet<>();  // To track already checked cheats

        // Iterate through each position in the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // Skip wall positions as cheat start
                if (grid[i][j] == '#') continue;

                // Use DFS to find all possible cheat paths
                dfsCheatPaths(i, j, new boolean[grid.length][grid[0].length], new ArrayList<>(), 0, maxWallsToCheat, originalPathLength, cheatCounts, alreadyCheckedCheats);
            }
        }

        return cheatCounts;
    }

    private void dfsCheatPaths(int x, int y, boolean[][] visited, List<int[]> currentPath, int wallsCheatCount,
                               int maxWallsToCheat, int originalPathLength, Map<Integer, Integer> cheatCounts,
                               Set<String> alreadyCheckedCheats) {
        if (wallsCheatCount > maxWallsToCheat) return;

        if (wallsCheatCount > 0 && grid[x][y] != '#') {
            // We've finished a cheat path
            String cheatKey = currentPath.get(0)[0] + "," + currentPath.get(0)[1] + "->" + x + "," + y;
            if (!alreadyCheckedCheats.contains(cheatKey)) {
                alreadyCheckedCheats.add(cheatKey);

                // Temporarily apply the cheat
                char[] originalChars = new char[currentPath.size()];
                for (int i = 0; i < currentPath.size(); i++) {
                    int[] pos = currentPath.get(i);
                    originalChars[i] = grid[pos[0]][pos[1]];
                    grid[pos[0]][pos[1]] = '.';
                }

                // Recalculate the shortest path with the cheat
                List<int[]> newPath = findShortestPathWithSteps();

                // If a valid new path exists, calculate the time saved
                if (newPath != null) {
                    int lengthDifference = originalPathLength - newPath.size();
                    if (lengthDifference > 0) {
                        // Increment the count for this length difference
                        cheatCounts.put(lengthDifference, cheatCounts.getOrDefault(lengthDifference, 0) + 1);
                    }
                }

                // Restore the original grid
                for (int i = 0; i < currentPath.size(); i++) {
                    int[] pos = currentPath.get(i);
                    grid[pos[0]][pos[1]] = originalChars[i];
                }
            }
        }

        visited[x][y] = true;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && !visited[newX][newY]) {
                currentPath.add(new int[]{newX, newY});
                dfsCheatPaths(newX, newY, visited, currentPath, wallsCheatCount + (grid[newX][newY] == '#' ? 1 : 0),
                        maxWallsToCheat, originalPathLength, cheatCounts, alreadyCheckedCheats);
                currentPath.remove(currentPath.size() - 1);
            }
        }
        visited[x][y] = false;
    }

    private int[] getCheatPath(int startX, int startY, int[] direction, int wallsToCheap) {
        int[] cheatPath = new int[wallsToCheap * 2];
        int x = startX, y = startY;
        int wallsFound = 0;

        for (int i = 0; i < wallsToCheap; i++) {
            x += direction[0];
            y += direction[1];

            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return null;

            if (grid[x][y] == '#') {
                cheatPath[wallsFound * 2] = x;
                cheatPath[wallsFound * 2 + 1] = y;
                wallsFound++;
            }

            if (wallsFound == wallsToCheap) {
                // Check if the end position is valid (not a wall)
                int endX = x + direction[0];
                int endY = y + direction[1];
                if (endX >= 0 && endX < grid.length && endY >= 0 && endY < grid[0].length && grid[endX][endY] != '#') {
                    return cheatPath;
                }
            }
        }

        return null;
    }


    public List<int[]> getShortestPath() {
        return shortestPath;
    }
}
