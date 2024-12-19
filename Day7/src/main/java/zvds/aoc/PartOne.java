package zvds.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PartOne implements PartInterface{
    @Override
    public void run(File input) {
        String inputString;
        try {
            inputString = Files.readString(input.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] lines = inputString.split("\\n");

        double totalCallibrationResult = 0;
        DecimalFormat df = new DecimalFormat("#.########");

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

    private boolean canAchieveTarget(List<Double> numbers, double target) {
        return tryOperations(numbers, 0, 0.0, target);
    }
    private boolean tryOperations(List<Double> numbers, int index, double currentResult, double target) {
        if (index == numbers.size()) {
            return Math.abs(currentResult - target) < 1e-9;
        }
        double num = numbers.get(index);
        if (tryOperations(numbers, index + 1, currentResult + num, target)) {
            return true;
        }
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
