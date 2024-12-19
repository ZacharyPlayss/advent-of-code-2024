package zvds.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtReader {
    public static List<List<String>> readFileToPatternLists(File fileName) {
        List<String> header = new ArrayList<>();
        List<String> patterns = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (isFirstLine) {
                    header.addAll(Arrays.asList(line.split(",\\s*")));
                    isFirstLine = false;
                } else {
                    patterns.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        List<List<String>> result = new ArrayList<>();
        result.add(header);
        result.add(patterns);
        return result;
    }
}
