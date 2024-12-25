package zvds.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PartTwo implements PartInterface{
    private final Map<String, Long> cache = new HashMap<>();

    @Override
    public void run(File input) {
        String inputString = null;
        try {
            inputString = Files.readString(Path.of(input.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Result for part 2: " + runSolution(inputString, 75));
    }

    public String runSolution(String input, int n) {
        long[] stones = Arrays.stream(input.split(" ")).mapToLong(Long::parseLong).toArray();

        long sum = 0;
        for (long stone : stones) {
            sum += blink(stone, n);
        }

        return String.format("%,d", sum);
    }

    private long blink(long stone, int n) {
        String cacheKey = stone + "," + n;
        if (cache.containsKey(cacheKey)) return cache.get(cacheKey);

        if (n == 0) {
            cache.put(cacheKey, 1L);
            return 1;
        }

        if (stone == 0) {
            long res = blink(1, n - 1);
            cache.put(cacheKey, res);
            return res;
        }

        int nDigits = (int) Math.floor(Math.log10(stone) + 1);
        if (nDigits > 0 && nDigits % 2 == 0) {
            long l = stone / (long) Math.pow(10, nDigits / 2);
            long r = stone % (long) Math.pow(10, nDigits / 2);
            long res = blink(l, n - 1) + blink(r, n - 1);
            cache.put(cacheKey, res);
            return res;
        }

        long res = blink(stone * 2024, n - 1);
        cache.put(cacheKey, res);
        return res;
    }

}
