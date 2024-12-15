package zvds;

import java.io.*;
import java.util.*;

public class Day15p1Main {
    static class Coord {
        int r, c;

        Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Coord add(Coord other) {
            return new Coord(this.r + other.r, this.c + other.c);
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", r, c);
        }
    }

    static class Grid {
        List<List<Character>> grid;
        List<Coord> commands;
        Coord pos;

        Grid(List<List<Character>> grid, List<Coord> commands) {
            this.grid = grid;
            this.commands = commands;
            this.pos = findStart();
        }

        private Coord findStart() {
            for (int r = 0; r < grid.size(); r++) {
                List<Character> row = grid.get(r);
                for (int c = 0; c < row.size(); c++) {
                    if (row.get(c) == '@') {
                        grid.get(r).set(c, '.');
                        return new Coord(r, c);
                    }
                }
            }
            throw new IllegalStateException("No start position found.");
        }

        void printGrid() {
            for (int r = 0; r < grid.size(); r++) {
                for (int c = 0; c < grid.get(r).size(); c++) {
                    if (pos.r == r && pos.c == c) {
                        System.out.print('@');
                    } else {
                        System.out.print(grid.get(r).get(c));
                    }
                }
                System.out.println();
            }
        }

        void move(Coord dir) {
            System.out.println("dir=" + dir);
            Coord next = pos.add(dir);
            if (grid.get(next.r).get(next.c) == '.') {
                pos = next;
                return;
            }
            if (grid.get(next.r).get(next.c) == '#') {
                return;
            }
            Coord end = null;
            Coord cur = next;
            while (true) {
                cur = cur.add(dir);
                char charAtCur = grid.get(cur.r).get(cur.c);
                if (charAtCur == '.') {
                    end = cur;
                    break;
                }
                if (charAtCur == 'O') {
                    continue;
                }
                break;
            }
            if (end != null) {
                grid.get(end.r).set(end.c, grid.get(next.r).get(next.c));
                grid.get(next.r).set(next.c, '.');
                pos = next;
            }
        }

        int calculate() {
            int sum = 0;
            for (int r = 0; r < grid.size(); r++) {
                for (int c = 0; c < grid.get(r).size(); c++) {
                    if (grid.get(r).get(c) == 'O') {
                        sum += 100 * r + c;
                    }
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("E:\\Development\\Java\\adventofcode2024\\Day15p1\\src\\main\\resources\\input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.strip());
            }
        }

        List<List<Character>> grid = new ArrayList<>();
        int i = 0;
        for (; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isEmpty()) {
                break;
            }
            List<Character> row = new ArrayList<>();
            for (char ch : line.toCharArray()) {
                row.add(ch);
            }
            grid.add(row);
        }

        Map<Character, Coord> dirs = Map.of(
                '^', new Coord(-1, 0),
                '>', new Coord(0, 1),
                'v', new Coord(1, 0),
                '<', new Coord(0, -1)
        );

        List<Coord> commands = new ArrayList<>();
        for (; i < lines.size(); i++) {
            String line = lines.get(i);
            for (char c : line.toCharArray()) {
                if (dirs.containsKey(c)) {
                    commands.add(dirs.get(c));
                }
            }
        }

        Grid world = new Grid(grid, commands);
        world.printGrid();

        for (Coord dir : commands) {
            world.move(dir);
        }

        System.out.println("world.calculate()=" + world.calculate());

        System.out.println("Should be: test=10092, small=2028");
    }
}
