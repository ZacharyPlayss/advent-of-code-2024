package zvds;

import java.io.*;
import java.util.*;

public class Day11p1Main {
    private final Map<String, Long> cache = new HashMap<>();

    public static void main(String[] args) {
        Day11p1Main solution = new Day11p1Main();

        // Read input from a file
        String input = solution.readFile("E:\\Development\\Java\\adventofcode2024\\Day11p1\\src\\main\\resources\\input.txt");

        System.out.println("Result for part a: " + solution.runSolution(input, 25));
        System.out.println("Result for part b: " + solution.runSolution(input, 75));
    }

    public String runSolution(String input, int n) {
        // Parse the input string into an array of long values
        long[] stones = Arrays.stream(input.split(" ")).mapToLong(Long::parseLong).toArray();

        long sum = 0;
        for (long stone : stones) {
            sum += blink(stone, n);
        }

        return String.format("%,d", sum); // Formatting the result as a comma-separated string
    }

    private long blink(long stone, int n) {
        String cacheKey = stone + "," + n; // Create a unique key for the cache
        if (cache.containsKey(cacheKey)) return cache.get(cacheKey); // Return the cached result if available

        if (n == 0) {
            cache.put(cacheKey, 1L);
            return 1; // Base case: return 1 if n is 0
        }

        if (stone == 0) {
            long res = blink(1, n - 1); // Handle the case where stone is 0
            cache.put(cacheKey, res);
            return res;
        }

        int nDigits = (int) Math.floor(Math.log10(stone) + 1); // Number of digits in the stone
        if (nDigits > 0 && nDigits % 2 == 0) {
            long l = stone / (long) Math.pow(10, nDigits / 2); // Left part of the stone
            long r = stone % (long) Math.pow(10, nDigits / 2); // Right part of the stone
            long res = blink(l, n - 1) + blink(r, n - 1); // Recursive calls for left and right parts
            cache.put(cacheKey, res);
            return res;
        }

        long res = blink(stone * 2024, n - 1); // Recursive call with the modified stone value
        cache.put(cacheKey, res);
        return res;
    }

    private String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString().trim(); // Return the file content as a string
    }
}
