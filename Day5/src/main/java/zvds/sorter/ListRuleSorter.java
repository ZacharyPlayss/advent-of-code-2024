package zvds.sorter;

import zvds.domain.Rule;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ListRuleSorter {
    public static List<Integer> sortPrint(List<Integer> printRun, Set<Rule> rules) {
        return printRun.stream()
                .sorted((a, b) -> rules.contains(new Rule(a, b)) ? -1 : 1)
                .collect(Collectors.toList());
    }
}
