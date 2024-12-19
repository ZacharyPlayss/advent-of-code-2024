package zvds.parser;

import zvds.domain.Input;
import zvds.domain.Rule;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InputParser {
    public static Input parseInput(String inputStr) {
        String[] sections = inputStr.split("\\r?\\n\\r?\\n");
        if (sections.length < 2) {
            throw new IllegalArgumentException("Invalid input format. Ensure there is a blank line separating rules and print runs.");
        }

        Set<Rule> rules = Arrays.stream(sections[0].split("\\r?\\n"))
                .map(line -> {
                    String[] parts = line.split("\\|");
                    return new Rule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                })
                .collect(Collectors.toSet());

        List<List<Integer>> printRuns = Arrays.stream(sections[1].split("\\r?\\n"))
                .map(line -> Arrays.stream(line.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        return new Input(rules, printRuns);
    }
}
