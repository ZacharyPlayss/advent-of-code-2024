package zvds.aoc;

import zvds.Day5p1Main;
import zvds.domain.Input;
import zvds.domain.Rule;
import zvds.parser.InputParser;
import zvds.validator.RuleValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;


public class PartOne implements PartInterface{
    @Override
    public void run(File input) {
        String inputString;
        try {
            inputString = Files.readString(input.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Input actualInput = InputParser.parseInput(inputString);

        Set<Rule> rules = actualInput.getRules();
        List<List<Integer>> printRuns = actualInput.getPrintRuns();
        int result = printRuns.stream()
                .filter(printRun -> RuleValidator.validPrint(rules, printRun))
                .mapToInt(Day5p1Main::middlePage)
                .sum();
        System.out.println("Result of Part 1: " + result);
    }


}
