package zvds;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14p1Main {
    public static void main(String[] args) throws IOException {
        List<String> data = Files.readAllLines(Paths.get("E:\\Development\\Java\\adventofcode2024\\Day14p1\\src\\main\\resources\\input.txt"));
        int[][] p = new int[data.size()][2];
        int[][] v = new int[data.size()][2];

        Pattern pattern = Pattern.compile("p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)");
        for (int i = 0; i < data.size(); i++) {
            Matcher matcher = pattern.matcher(data.get(i));
            if (matcher.find()) {
                p[i][1] = Integer.parseInt(matcher.group(1));
                p[i][0] = Integer.parseInt(matcher.group(2));
                v[i][1] = Integer.parseInt(matcher.group(3));
                v[i][0] = Integer.parseInt(matcher.group(4));
            }
        }

        int[] lengths = new int[2];
        for (int[] point : p) {
            lengths[0] = Math.max(lengths[0], point[0] + 1);
            lengths[1] = Math.max(lengths[1], point[1] + 1);
        }

        int searchlen = 10;
        int i = 0;
        boolean found = false;
        while (!found) {
            if (i == 100) {
                part1(lengths, p);
            }
            for (int j = 0; j < p.length; j++) {
                p[j][0] = (p[j][0] + v[j][0] + lengths[0]) % lengths[0];
                p[j][1] = (p[j][1] + v[j][1] + lengths[1]) % lengths[1];
            }
            i++;
            int[][] matrix = new int[lengths[0]][lengths[1]];
            for (int[] point : p) {
                matrix[point[0]][point[1]] = 1;
            }
            for (int[] row : matrix) {
                if (convolve(row, searchlen)) {
                    found = true;
                    part2Display(lengths, p);
                    System.out.println("part2: " + i);
                    break;
                }
            }
        }
    }

    private static void part1(int[] m, int[][] p) {
        int cy = m[0] / 2, cx = m[1] / 2;
        int[] res = new int[4];
        for (int[] point : p) {
            if (point[0] < cy && point[1] < cx) res[0]++;
            if (point[0] > cy && point[1] < cx) res[1]++;
            if (point[0] < cy && point[1] > cx) res[2]++;
            if (point[0] > cy && point[1] > cx) res[3]++;
        }
        System.out.println("part1: " + (res[0] * res[1] * res[2] * res[3]));
    }

    private static void part2Display(int[] m, int[][] p) {
        int[][] v = new int[m[0]][m[1]];
        for (int[] point : p) {
            v[point[0]][point[1]] = 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int[] row : v) {
            for (int cell : row) {
                sb.append(cell);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString().replaceAll("\\s", ""));
    }

    private static boolean convolve(int[] row, int searchlen) {
        int sum = 0;
        for (int i = 0; i < searchlen; i++) {
            sum += row[i];
        }
        if (sum == searchlen) return true;
        for (int i = searchlen; i < row.length; i++) {
            sum = sum - row[i - searchlen] + row[i];
            if (sum == searchlen) return true;
        }
        return false;
    }
}
