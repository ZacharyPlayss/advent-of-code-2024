package zvds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day13p1Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("E:\\Development\\Java\\adventofcode2024\\Day13p1\\src\\main\\resources\\input.txt"));
        List<String[]> lines = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            lines.add(line.split(" "));
        }
        br.close();
        List<List<String[]>> groups = new ArrayList<>();
        for (int i = 0; i < (lines.size() + 1) / 4; i++) {
            List<String[]> group = new ArrayList<>();
            group.add(lines.get(4 * i));
            group.add(lines.get(4 * i + 1));
            group.add(lines.get(4 * i + 2));
            groups.add(group);
        }
        List<int[]> data = new ArrayList<>();
        for (List<String[]> group : groups) {
            String[] a = group.get(0);
            String[] b = group.get(1);
            String[] p = group.get(2);
            int ax = Integer.parseInt(a[2].substring(2, a[2].length() - 1));
            int ay = Integer.parseInt(a[3].substring(2));
            int bx = Integer.parseInt(b[2].substring(2, b[2].length() - 1));
            int by = Integer.parseInt(b[3].substring(2));
            int x = Integer.parseInt(p[1].substring(2, p[1].length() - 1));
            int y = Integer.parseInt(p[2].substring(2));
            data.add(new int[]{ax, ay, bx, by, x, y});
        }
        int res = 0;
        for (int[] values : data) {
            int ax = values[0], ay = values[1], bx = values[2], by = values[3], x = values[4], y = values[5];

            double denominator = (ay * bx - ax * by);
            double i = (bx * y - by * x) / denominator;
            double j = (x - i * ax) / bx;

            if ((x - i * ax) % bx == 0 && (bx * y - by * x) % denominator == 0) {
                res += 3 * (int) i + (int) j;
            }
        }
        System.out.println(res);
    }
}