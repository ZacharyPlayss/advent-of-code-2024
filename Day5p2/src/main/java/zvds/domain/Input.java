package zvds.domain;

import java.util.List;
import java.util.Set;

public class Input {
        private final Set<Rule> rules;
        private final List<List<Integer>> printRuns;

        public Input(Set<Rule> rules, List<List<Integer>> printRuns) {
            this.rules = rules;
            this.printRuns = printRuns;
        }

        public Set<Rule> getRules() { return rules; }

        public List<List<Integer>> getPrintRuns() { return printRuns;}
}
