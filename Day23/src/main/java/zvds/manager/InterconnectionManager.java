package zvds.manager;

import zvds.domain.Computer;
import zvds.domain.ComputerConnection;
import zvds.domain.ComputerInterconnection;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InterconnectionManager {

    public List<ComputerInterconnection> findInterconnectedSystems(HashMap<Computer, List<Computer>> connectionMap) {
        List<ComputerInterconnection> interconnectedSystems = new ArrayList<>();
        Set<String> uniqueInterconnections = new HashSet<>();
        for (Computer computerA : connectionMap.keySet()) {
            List<Computer> connectionsA = connectionMap.get(computerA);
            for (int i = 0; i < connectionsA.size(); i++) {
                Computer computerB = connectionsA.get(i);
                List<Computer> connectionsB = connectionMap.get(computerB);
                for (int j = i + 1; j < connectionsA.size(); j++) {
                    Computer computerC = connectionsA.get(j);
                    List<Computer> connectionsC = connectionMap.get(computerC);
                    if (connectionsB != null && connectionsB.contains(computerC) && connectionsC != null && connectionsC.contains(computerB)) {
                        List<Computer> sortedConnection = Arrays.asList(computerA, computerB, computerC);
                        sortedConnection.sort(Comparator.comparing(Computer::getName)); // Sort alphabetically
                        String uniqueKey = sortedConnection.get(0).getName() + "-" +
                                sortedConnection.get(1).getName() + "-" +
                                sortedConnection.get(2).getName();
                        if (!uniqueInterconnections.contains(uniqueKey)) {
                            uniqueInterconnections.add(uniqueKey);
                            interconnectedSystems.add(new ComputerInterconnection(sortedConnection.get(0),
                                    sortedConnection.get(1),
                                    sortedConnection.get(2)));
                        }
                    }
                }
            }
        }
        return interconnectedSystems;
    }

    public List<ComputerInterconnection> filterInterConnectedLists(List<ComputerInterconnection> interconnectedSystems, Predicate<ComputerInterconnection> ruleSet) {
        List<ComputerInterconnection> filteredInterconnections = new ArrayList<>();
        for (ComputerInterconnection interconnection : interconnectedSystems) {
            if (ruleSet.test(interconnection)) {
                filteredInterconnections.add(interconnection);
            }
        }
        return filteredInterconnections;
    }

    public List<Computer> findLargestInterconnection(List<ComputerInterconnection> interconnections) {
        HashMap<Computer, Set<Computer>> connectionMap = new HashMap<>();
        Set<Computer> allComputers = new HashSet<>();
        for (ComputerInterconnection interconnection : interconnections) {
            Computer[] computers = {interconnection.getComputerA(), interconnection.getComputerB(), interconnection.getComputerC()};
            for (Computer computer : computers) {
                connectionMap.putIfAbsent(computer, new HashSet<>());
                allComputers.add(computer);
            }
            for (int i = 0; i < computers.length; i++) {
                for (int j = 0; j < computers.length; j++) {
                    if (i != j) {
                        connectionMap.get(computers[i]).add(computers[j]);
                    }
                }
            }
        }
        Set<Computer> largestGroup = new HashSet<>();
        for (Computer computer : allComputers) {
            Set<Computer> group = new HashSet<>();
            group.add(computer);
            for (Computer other : allComputers) {
                if (!other.equals(computer) && isFullyConnected(group, other, connectionMap)) {
                    group.add(other);
                }
            }
            if (group.size() > largestGroup.size()) {
                largestGroup = group;
            }
        }
        List<Computer> result = new ArrayList<>(largestGroup);
        result.sort(Comparator.comparing(Computer::getName));
        return result;
    }
    private boolean isFullyConnected(Set<Computer> group, Computer newComputer, HashMap<Computer, Set<Computer>> connectionMap) {
        for (Computer computer : group) {
            if (!connectionMap.get(computer).contains(newComputer) || !connectionMap.get(newComputer).contains(computer)) {
                return false;
            }
        }
        return true;
    }


}
