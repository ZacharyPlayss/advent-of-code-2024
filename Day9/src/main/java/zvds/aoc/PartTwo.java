package zvds.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PartTwo implements PartInterface{
    @Override
    public void run(File input) {
        String inputString = null;
        try {
            inputString = Files.readString(input.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean isFile = true;
        int fileId = 0;
        List<Integer> ids = new ArrayList<>();
        while (!inputString.isEmpty() && Character.isDigit(inputString.charAt(0))) {
            int num = inputString.charAt(0) - '0';

            if (isFile) {
                for (int i = 0; i < num; i++) {
                    ids.add(fileId);
                }
                fileId++;
            } else {
                for (int i = 0; i < num; i++) {
                    ids.add(-1); // Use -1 for free space
                }
            }

            isFile = !isFile;
            inputString = inputString.substring(1);
        }

        int src = ids.size() - 1;
        while (src >= 0) {
            if (ids.get(src) < 0) {
                src--;
                continue;
            }

            int src1 = src;
            while (src1 > 0 && ids.get(src1 - 1).equals(ids.get(src))) {
                src1--;
            }

            int n = src - src1 + 1;
            int run = 0;

            for (int d = 0; d < src1; d++) {
                if (ids.get(d) >= 0) {
                    run = 0;
                } else {
                    run++;
                }

                int runStart = d - run + 1;
                if (run == n) {
                    for (int i = 0; i < n; i++) {
                        ids.set(runStart + i, ids.get(src1 + i));
                        ids.set(src1 + i, -1);
                    }
                    break;
                }
            }

            src = src1 - 1;
        }
        long sum = 0;
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) >= 0) {
                sum += (long) i * ids.get(i);
            }
        }
        System.out.println(sum);
    }
}
