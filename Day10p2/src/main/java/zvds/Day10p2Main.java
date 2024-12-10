package zvds;

import java.io.*;
import java.util.*;

public class Day10p2Main {
    public static void main(String[] args) throws IOException {
        String inputFile = "E:\\Development\\Java\\adventofcode2024\\Day10p1\\src\\main\\resources\\input.txt";
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        String[] D = lines.toArray(new String[0]);
        int p2 = solve(D);
        System.out.println("Part 2: " + p2);
    }

    private static int solve(String[] D) {
        Set<int[]> heads = new HashSet<>();
        List<int[]> end = new ArrayList<>();

        for (int r = 0; r < D.length; r++) {
            for (int c = 0; c < D[r].length(); c++) {
                if (D[r].charAt(c) == '0') {
                    heads.add(new int[]{r, c});
                }
            }
        }
        for (int[] h : heads) {
            Queue<int[]> q = new LinkedList<>();
            Set<String> visited = new HashSet<>(); // To track visited cells for p1
            q.add(h);

            // Start BFS
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                String currKey = curr[0] + "," + curr[1];

                if (D[curr[0]].charAt(curr[1]) == '9') {
                    end.add(curr);
                }

                // Check surrounding squares
                int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
                for (int[] d : directions) {
                    int nr = curr[0] + d[0];
                    int nc = curr[1] + d[1];
                    if (nr >= 0 && nr < D.length && nc >= 0 && nc < D[0].length()
                            && D[nr].charAt(nc) != '.') {
                        int diff = D[nr].charAt(nc) - D[curr[0]].charAt(curr[1]);
                        if (diff == 1) {
                            String nextKey = nr + "," + nc;
                            q.add(new int[]{nr, nc});
                            visited.add(nextKey);
                        }
                    }
                }
            }
        }

        return end.size();
    }
}
