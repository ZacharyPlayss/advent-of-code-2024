package zvds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day13p2Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("E:\\Development\\Java\\adventofcode2024\\Day13p2\\src\\main\\resources\\input.txt"));
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

        List<long[]> data = new ArrayList<>();
        for (List<String[]> group : groups) {
            String[] a = group.get(0);
            String[] b = group.get(1);
            String[] p = group.get(2);
            long ax = Long.parseLong(a[2].substring(2, a[2].length() - 1));
            long ay = Long.parseLong(a[3].substring(2));
            long bx = Long.parseLong(b[2].substring(2, b[2].length() - 1));
            long by = Long.parseLong(b[3].substring(2));
            long x = Long.parseLong(p[1].substring(2, p[1].length() - 1)) + 10000000000000L;
            long y = Long.parseLong(p[2].substring(2)) + 10000000000000L;
            data.add(new long[]{ax, ay, bx, by, x, y});
        }

        long res = 0;
        for (long[] values : data) {
            long ax = values[0], ay = values[1], bx = values[2], by = values[3], x = values[4], y = values[5];

            double denominator = (ay * bx - ax * by);
            double i = (bx * y - by * x) / denominator;
            double j = (x - i * ax) / bx;
            if ((x - i * ax) % bx == 0 && (bx * y - by * x) % denominator == 0) {
                res += 3 * (long) i + (long) j;
            }
        }

        System.out.println(res);
    }
}