package zvds.aoc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PartOne implements PartInterface{
    @Override
    public void run(File input) {
        String[] map = convertInputToMap(input);

        Set<String> antinodes = findAntinodes(map);
        System.out.println("Unique Antinode Locations: " + antinodes.size());
        printMapWithAntinodes(map, antinodes);
    }

    private String[] convertInputToMap(File fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private static char[][] printMapWithAntinodes(String[] map, Set<String> antinodes) {
        char[][] grid = new char[map.length][map[0].length()];

        for (int i = 0; i < map.length; i++) {
            grid[i] = map[i].toCharArray();
        }

        for (String antinode : antinodes) {
            String[] parts = antinode.split(",");
            int antinodeX = Integer.parseInt(parts[0]);
            int antinodeY = Integer.parseInt(parts[1]);

            if (grid[antinodeX][antinodeY] == '.') {
                grid[antinodeX][antinodeY] = '#';
            }
        }

        for (int i = 0; i < grid.length; i++) {
            System.out.println(new String(grid[i]));
        }
        return grid;
    }
    private Set<String> findAntinodes(String[] map) {
        Set<String> antinodes = new HashSet<>();

        for (int x1 = 0; x1 < map.length; x1++) {
            for (int y1 = 0; y1 < map[x1].length(); y1++) {
                char freq1 = map[x1].charAt(y1);
                if (freq1 != '.') {
                    for (int x2 = 0; x2 < map.length; x2++) {
                        for (int y2 = 0; y2 < map[x2].length(); y2++) {
                            char freq2 = map[x2].charAt(y2);
                            if (freq1 == freq2 && !(x1 == x2 && y1 == y2)) {
                                addAntinodes(antinodes, x1, y1, x2, y2, map.length, map[0].length());
                            }
                        }
                    }
                }
            }
        }

        return antinodes;
    }
    private void addAntinodes(Set<String> antinodes, int x1, int y1, int x2, int y2, int mapHeight, int mapWidth) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int antinode1X = x1 - dx;
        int antinode1Y = y1 - dy;
        int antinode2X = x2 + dx;
        int antinode2Y = y2 + dy;

        if (isInBounds(antinode1X, antinode1Y, mapHeight, mapWidth)) {
            antinodes.add(antinode1X + "," + antinode1Y);
        }

        if (isInBounds(antinode2X, antinode2Y, mapHeight, mapWidth)) {
            antinodes.add(antinode2X + "," + antinode2Y);
        }
    }
    private boolean isInBounds(int x, int y, int mapHeight, int mapWidth) {
        return x >= 0 && x < mapHeight && y >= 0 && y < mapWidth;
    }

}
