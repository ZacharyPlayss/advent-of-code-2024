package zvds.aoc;

import zvds.calculator.SecretNumberCalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartOne implements PartInterface {

    @Override
    public void run(File input) {
        List<Integer> integers = parseFile(input);
        SecretNumberCalculator snc = new SecretNumberCalculator();
        long[] buyersFinalPrice = new long[integers.size()];
        for(int i =0; i < integers.size(); i++){
            long[] predictedNumbers = snc.predictNextSecretNumbers(integers.get(i), 2000);
            buyersFinalPrice[i] = predictedNumbers[1999];
        }
        long totalCount = 0;
        for(int i =0; i < buyersFinalPrice.length; i++){
            totalCount += buyersFinalPrice[i];
        }
        System.out.println("The sum of all final prices by each buyer: " + totalCount);

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
