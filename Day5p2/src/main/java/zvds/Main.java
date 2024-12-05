package zvds;

import zvds.domain.Input;
import zvds.domain.Rule;
import zvds.parser.InputParser;
import zvds.reader.TxtReader;
import zvds.sorter.ListRuleSorter;
import zvds.validator.RuleValidator;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String inputStr = TxtReader.readFileToString("E:\\Development\\Java\\adventofcode2024\\Day5p1\\src\\main\\resources\\input.txt");
        Input input = InputParser.parseInput(inputStr);

        Set<Rule> rules = input.getRules();
        List<List<Integer>> printRuns = input.getPrintRuns();

        int result = printRuns.stream()
                .filter(printRun -> !RuleValidator.validPrint(rules, printRun))
                .map(printRun -> ListRuleSorter.sortPrint(printRun, rules))
                .mapToInt(Main::middlePage)
                .sum();

        System.out.println("Result of Part 2: " + result);
    }

    public static int middlePage(List<Integer> pages) {
        if (pages.isEmpty()) throw new IllegalArgumentException("Empty print run");
        return pages.get(pages.size() / 2);
    }

}

