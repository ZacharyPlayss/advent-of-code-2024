package zvds.validator;

import zvds.domain.Rule;

import java.util.List;
import java.util.Set;

public class RuleValidator {
    public static boolean validPrint(Set<Rule> rules, List<Integer> printRun) {
        for (int i = 0; i < printRun.size(); i++) {
            int current = printRun.get(i);
            for (int j = i + 1; j < printRun.size(); j++) {
                Rule rule = new Rule(current, printRun.get(j));
                if (!rules.contains(rule)) {
                    return false;
                }
            }
        }
        return true;
    }
}