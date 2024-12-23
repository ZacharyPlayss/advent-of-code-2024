package zvds.finder;

import zvds.domain.Computer;

import java.util.*;

public class GroupFinder implements FinderInterface<Set<Computer>>{
    @Override
    public List<Set<Computer>> find(Object... args) {
        if (args.length != 2 || !(args[0] instanceof Map) || !(args[1] instanceof Set)) {
            throw new IllegalArgumentException("Expected arguments: Map<Computer, Set<Computer>>, Set<Computer>");
        }
        Map<Computer, Set<Computer>> connectionMap = (Map<Computer, Set<Computer>>) args[0];
        Set<Computer> allComputers = (Set<Computer>) args[1];
        return findAllInterconnectedGroups(connectionMap, allComputers);
    }
    private List<Set<Computer>> findAllInterconnectedGroups(Map<Computer, Set<Computer>> connectionMap, Set<Computer> allComputers) {
        List<Set<Computer>> allGroups = new ArrayList<>();
        Set<Computer> unassignedComputers = new HashSet<>(allComputers);

        while (!unassignedComputers.isEmpty()) {
            Computer startComputer = unassignedComputers.iterator().next();
            Set<Computer> currentGroup = new HashSet<>();
            currentGroup.add(startComputer);

            boolean groupExpanded;
            do {
                groupExpanded = false;
                for (Computer computer : new HashSet<>(unassignedComputers)) {
                    if (isFullyConnectedToGroup(computer, currentGroup, connectionMap)) {
                        currentGroup.add(computer);
                        unassignedComputers.remove(computer);
                        groupExpanded = true;
                    }
                }
            } while (groupExpanded);

            allGroups.add(currentGroup);
            unassignedComputers.removeAll(currentGroup);
        }

        return allGroups;
    }

    private boolean isFullyConnectedToGroup(Computer computer, Set<Computer> group, Map<Computer, Set<Computer>> connectionMap) {
        for (Computer groupMember : group) {
            if (!connectionMap.get(computer).contains(groupMember) || !connectionMap.get(groupMember).contains(computer)) {
                return false;
            }
        }
        return true;
    }

    public Set<Computer> findLargestGroup(List<Set<Computer>> groups) {
        return groups.stream()
                .max(Comparator.comparingInt(Set::size))
                .orElse(new HashSet<>());
    }
}