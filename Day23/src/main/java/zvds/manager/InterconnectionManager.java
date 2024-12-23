package zvds.manager;

import zvds.builder.ConnectionMapBuilder;
import zvds.domain.Computer;
import zvds.domain.ComputerInterconnection;
import zvds.finder.FinderInterface;
import zvds.finder.GroupFinder;
import zvds.finder.InterconnectionFinder;
import zvds.finder.LargestGroupFinder;
import zvds.sorter.ComputerSorter;


import java.util.*;
import java.util.function.Predicate;

public class InterconnectionManager {

    private final ConnectionMapBuilder connectionMapBuilder;
    private final FinderInterface<ComputerInterconnection> interconnectionFinder;
    private final FinderInterface<Set<Computer>> groupFinder;
    private final FinderInterface<Set<Computer>> largestGroupFinder;
    private final ComputerSorter computerSorter;

    public InterconnectionManager() {
        this.connectionMapBuilder = new ConnectionMapBuilder();
        this.interconnectionFinder = new InterconnectionFinder();
        this.largestGroupFinder = new LargestGroupFinder();
        this.groupFinder = new GroupFinder();
        this.computerSorter = new ComputerSorter();
    }

    public List<ComputerInterconnection> findInterconnectedSystems(HashMap<Computer, List<Computer>> connectionMap) {
        return interconnectionFinder.find(connectionMap);
    }

    public List<ComputerInterconnection> filterInterConnectedLists(List<ComputerInterconnection> interconnectedSystems, Predicate<ComputerInterconnection> ruleSet) {
        return interconnectedSystems.stream()
                .filter(ruleSet)
                .toList();
    }

    public List<Computer> findLargestInterconnection(List<ComputerInterconnection> interconnections) {
        Map<Computer, Set<Computer>> connectionMap = connectionMapBuilder.buildConnectionMap(interconnections);
        //connectionMapBuilder.printConnectionMap(connectionMap);

        Set<Computer> allComputers = new HashSet<>(connectionMap.keySet());
        List<Set<Computer>> allGroups = groupFinder.find(connectionMap, allComputers);

        Set<Computer> largestGroup = largestGroupFinder.find(allGroups).get(0);
        return computerSorter.sortComputersByName(largestGroup);
    }
}
