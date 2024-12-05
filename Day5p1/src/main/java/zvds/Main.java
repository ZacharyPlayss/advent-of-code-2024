package zvds;

import zvds.reader.TxtReader;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // Use TxtReader to read input from a file
        String inputStr = TxtReader.readFileToString("E:\\Development\\Java\\adventofcode2024\\Day5p1\\src\\main\\resources\\input.txt");
        Input input = parseInput(inputStr);

        // Part 1
        int pt1Result = pt1(input);
        System.out.println("Result of Part 1: " + pt1Result);

        // Part 2
        int pt2Result = pt2(input);
        System.out.println("Result of Part 2: " + pt2Result);
    }

    // Parse the input to extract rules and print runs
    public static Input parseInput(String inputStr) {
        // Adjust split logic to handle different newline conventions
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

    // Logic for Part 1
    public static int pt1(Input input) {
        Set<Rule> rules = input.getRules();
        List<List<Integer>> printRuns = input.getPrintRuns();

        return printRuns.stream()
                .filter(printRun -> Validator.validPrint(rules, printRun))
                .mapToInt(Main::middlePage)
                .sum();
    }

    // Logic for Part 2
    public static int pt2(Input input) {
        Set<Rule> rules = input.getRules();
        List<List<Integer>> printRuns = input.getPrintRuns();

        return printRuns.stream()
                .filter(printRun -> !Validator.validPrint(rules, printRun))
                .map(printRun -> sortPrint(printRun, rules))
                .mapToInt(Main::middlePage)
                .sum();
    }

    // Calculate the middle page of a list
    public static int middlePage(List<Integer> pages) {
        if (pages.isEmpty()) throw new IllegalArgumentException("Empty print run");
        return pages.get(pages.size() / 2);
    }

    // Sort a print run based on applicable rules
    public static List<Integer> sortPrint(List<Integer> printRun, Set<Rule> rules) {
        return printRun.stream()
                .sorted((a, b) -> rules.contains(new Rule(a, b)) ? -1 : 1)
                .collect(Collectors.toList());
    }
}

// Class to represent a rule
class Rule implements Comparable<Rule> {
    private final int before;
    private final int after;

    public Rule(int before, int after) {
        this.before = before;
        this.after = after;
    }

    public int getBefore() { return before; }
    public int getAfter() { return after; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return before == rule.before && after == rule.after;
    }

    @Override
    public int hashCode() {
        return Objects.hash(before, after);
    }

    @Override
    public int compareTo(Rule other) {
        int cmp = Integer.compare(this.before, other.before);
        if (cmp == 0) {
            return Integer.compare(this.after, other.after);
        }
        return cmp;
    }

    @Override
    public String toString() {
        return before + "|" + after;
    }
}

// Class to represent the input structure
class Input {
    private final Set<Rule> rules;
    private final List<List<Integer>> printRuns;

    public Input(Set<Rule> rules, List<List<Integer>> printRuns) {
        this.rules = rules;
        this.printRuns = printRuns;
    }

    public Set<Rule> getRules() { return rules; }

    public List<List<Integer>> getPrintRuns() { return printRuns; }
}

// Utility class for validation
class Validator {

    // Check if a print run is valid based on the rules
    public static boolean validPrint(Set<Rule> rules, List<Integer> printRun) {
        for (int i = 0; i < printRun.size(); i++) {
            int current = printRun.get(i);
            for (int j = i + 1; j < printRun.size(); j++) {
                Rule rule = new Rule(current, printRun.get(j));
                if (!rules.contains(rule)) {
                    return false; // If a rule is violated
                }
            }
        }
        return true; // All rules satisfied
    }
}