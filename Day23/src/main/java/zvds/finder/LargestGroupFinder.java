package zvds.finder;

import zvds.domain.Computer;

import java.util.*;

public class LargestGroupFinder implements FinderInterface<Set<Computer>> {

    @Override
    public List<Set<Computer>> find(Object... args) {
        if (args.length != 1 || !(args[0] instanceof List)) {
            throw new IllegalArgumentException("Expected argument: List<Set<Computer>>");
        }
        List<Set<Computer>> groups = (List<Set<Computer>>) args[0];
        return Collections.singletonList(findLargestGroup(groups));
    }

    private Set<Computer> findLargestGroup(List<Set<Computer>> groups) {
        return groups.stream()
                .max(Comparator.comparingInt(Set::size))
                .orElse(new HashSet<>());
    }
}
