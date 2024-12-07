package zvds;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Day7p1Main {
    public static void main(String[] args) throws IOException {
        // Read input file content
        String input = Files.readString(Paths.get("E:\\Development\\Java\\adventofcode2024\\Day7p1\\src\\main\\resources\\input.txt"));

        // Split input into lines
        String[] lines = input.split("\\n");

        double totalCallibrationResult = 0;
        DecimalFormat df = new DecimalFormat("#.########"); // Ensure up to 8 decimal places without scientific notation

        // Process each line
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length != 2) {
                System.err.println("Invalid line format: " + line);
                continue;
            }

            // Parse toGetResult
            double toGetResult = Double.parseDouble(parts[0].trim());

            // Parse the numbers into a list
            String[] numberStrings = parts[1].trim().split(" ");
            List<Double> numbers = new ArrayList<>();
            for (String numStr : numberStrings) {
                numbers.add(Double.parseDouble(numStr.trim()));
            }

            // Try every combination of addition and multiplication to get the result
            if (canAchieveTarget(numbers, toGetResult)) {
                System.out.println("A combination of operations works for " + numbers + " to get " + df.format(toGetResult));
                totalCallibrationResult += toGetResult;
            } else {
                System.out.println("No combination of operations works for " + numbers + " to get " + df.format(toGetResult));
            }
        }
        System.out.println("\n###################\nCALIBRATION COMPLETE\n###################");
        System.out.println("TOTAL CALIBRATION SCORE:" + df.format(totalCallibrationResult));
    }

    // Helper method to check all combinations of operations to achieve the result
    private static boolean canAchieveTarget(List<Double> numbers, double target) {
        return tryOperations(numbers, 0, 0.0, target);
    }

    // Recursive method to try all combinations of addition and multiplication
    private static boolean tryOperations(List<Double> numbers, int index, double currentResult, double target) {
        if (index == numbers.size()) {
            return Math.abs(currentResult - target) < 1e-9;
        }

        double num = numbers.get(index);

        // Try addition
        if (tryOperations(numbers, index + 1, currentResult + num, target)) {
            return true;
        }

        // Try multiplication (if not the first operation, to avoid multiplying by 0)
        if (index == 0) {
            if (tryOperations(numbers, index + 1, num, target)) {
                return true;
            }
        } else {
            if (tryOperations(numbers, index + 1, currentResult * num, target)) {
                return true;
            }
        }

        return false;
    }
}