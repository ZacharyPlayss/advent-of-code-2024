package zvds.aoc;

import zvds.domain.RaceTrack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PartOne implements PartInterface {
    @Override
    public void run(File input) {
        char[][] grid = parseFileToGrid(input);
        RaceTrack raceTrack = new RaceTrack(grid);

        List<int[]> shortestPath = raceTrack.getShortestPath();
        int picosecondsFastestPath = shortestPath.size() - 1;//-1 to not count startinpostiion as it isn't a move itself.

        //UNCOMMENT THIS TO SEE THE INITIAL FASTEST PATH WITH NO CHEATS APPLIED.
        //System.out.println("Fastest path without cheating : " + picosecondsFastestPath);

        Map<Integer, Integer> cheatCounts = raceTrack.findCheatPaths(2);
        List<Map.Entry<Integer, Integer>> sortedEntries = new ArrayList<>(cheatCounts.entrySet());
        sortedEntries.sort(Map.Entry.comparingByKey());

        // PRINT OUT FOR COUNT OF VALID CHEATS WITH THEIR IMPROVEMENT
        /*for (Map.Entry<Integer, Integer> entry : sortedEntries) {
            int improvement = entry.getKey();
            int count = entry.getValue();
            System.out.println("Valid cheats: " + count/2 + " improvement: " + improvement);
        }*/

        int cheatsOver100Picoseconds = cheatCounts.entrySet().stream()
                .filter(entry -> entry.getKey() >= 100)
                .mapToInt(Map.Entry::getValue)
                .sum();
        cheatsOver100Picoseconds = cheatsOver100Picoseconds/2;

        System.out.println("Number of cheats that save at least 100 picoseconds: " + cheatsOver100Picoseconds);

    }

    private char[][] parseFileToGrid(File input) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(input.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int rows = lines.size();
        int cols = lines.get(0).length();
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }
        return grid;
    }
}
