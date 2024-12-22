package zvds.aoc;

import zvds.calculator.SecretNumberCalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PartTwo implements PartInterface {
    @Override
    public void run(File input) {
        List<Integer> integers = parseFile(input);
        SecretNumberCalculator snc = new SecretNumberCalculator();
        List<long[]> allPrices = new ArrayList<>();

        for (int i : integers) {
            long[] predictedNumbers = snc.predictNextSecretNumbers(i, 2000);
            allPrices.add(snc.extractOnesPosition(predictedNumbers));
        }

        List<List<Long>> allSequences = generateAllSequences();
        long maxBananas = 0;
        List<Long> bestSequence = null;

        for (List<Long> sequence : allSequences) {
            long bananas = evaluateSequence(sequence, allPrices);
            if (bananas > maxBananas) {
                maxBananas = bananas;
                bestSequence = sequence;
            }
        }

        System.out.println("Best sequence: " + bestSequence);
        System.out.println("Max bananas: " + maxBananas);

        // Print individual prices for each buyer
        for (long[] prices : allPrices) {
            System.out.println(findFirstOccurrence(bestSequence, prices));
        }
    }

    private List<List<Long>> generateAllSequences() {
        List<List<Long>> sequences = new ArrayList<>();
        for (long a = -9; a <= 9; a++) {
            for (long b = -9; b <= 9; b++) {
                for (long c = -9; c <= 9; c++) {
                    for (long d = -9; d <= 9; d++) {
                        sequences.add(Arrays.asList(a, b, c, d));
                    }
                }
            }
        }
        return sequences;
    }

    private long evaluateSequence(List<Long> sequence, List<long[]> allPrices) {
        long totalBananas = 0;
        for (long[] prices : allPrices) {
            totalBananas += findFirstOccurrence(sequence, prices);
        }
        return totalBananas;
    }

    private long findFirstOccurrence(List<Long> sequence, long[] prices) {
        for (int i = 0; i < prices.length - 4; i++) {
            if (matchesSequence(sequence, prices, i)) {
                return prices[i + 4];
            }
        }
        return 0;
    }

    private boolean matchesSequence(List<Long> sequence, long[] prices, int start) {
        for (int i = 0; i < 4; i++) {
            if (prices[start + i + 1] - prices[start + i] != sequence.get(i)) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> parseFile(File input) {
        List<Integer> integers = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                try {
                    integers.add(Integer.parseInt(line));
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid number: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return integers;
    }
}
