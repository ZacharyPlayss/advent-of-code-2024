package zvds;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Day7p2Main {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("E:\\Development\\Java\\adventofcode2024\\Day7p1\\src\\main\\resources\\input.txt"));

        String[] lines = input.split("\\n");

        double totalCallibrationResult = 0;
        DecimalFormat df = new DecimalFormat("#.########"); // Ensure up to 8 decimal places without scientific notation

        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length != 2) {
                System.err.println("Invalid line format: " + line);
                continue;
            }

            double toGetResult = Double.parseDouble(parts[0].trim());

            String[] numberStrings = parts[1].trim().split(" ");
            List<Double> numbers = new ArrayList<>();
            for (String numStr : numberStrings) {
                numbers.add(Double.parseDouble(numStr.trim()));
            }

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

    private static boolean canAchieveTarget(List<Double> numbers, double target) {
        return tryOperations(numbers, 0, 0.0, target);
    }

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

        // Try concatenation (convert numbers to strings, concatenate, then parse back to a number)
        if (index > 0) { // Concatenation is only meaningful if there is a previous number
            String concatenatedStr = String.valueOf((long) currentResult) + String.valueOf((long) num);
            double concatenatedNum = Double.parseDouble(concatenatedStr);
            if (tryOperations(numbers, index + 1, concatenatedNum, target)) {
                return true;
            }
        }

        return false;
    }
}