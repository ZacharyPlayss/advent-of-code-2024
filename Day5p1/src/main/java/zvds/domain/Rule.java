package zvds.domain;

import java.util.Objects;

public class Rule implements Comparable<Rule> {
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
