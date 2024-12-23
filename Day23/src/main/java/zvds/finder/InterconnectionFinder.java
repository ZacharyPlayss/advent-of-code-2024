package zvds.finder;

import zvds.domain.Computer;
import zvds.domain.ComputerInterconnection;

import java.util.*;
import java.util.stream.Collectors;

public class InterconnectionFinder implements FinderInterface<ComputerInterconnection> {

    @Override
    public List<ComputerInterconnection> find(Object... args) {
        if (args.length != 1 || !(args[0] instanceof HashMap)) {
            throw new IllegalArgumentException("Expected arguments: HashMap<Computer, List<Computer>>");
        }

        HashMap<Computer, List<Computer>> connectionMap = (HashMap<Computer, List<Computer>>) args[0];
        return findInterconnections(connectionMap);
    }

    public List<ComputerInterconnection> findInterconnections(HashMap<Computer, List<Computer>> connectionMap) {
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
                    if (isInterconnected(connectionsB, connectionsC, computerB, computerC)) {
                        addUniqueInterconnection(interconnectedSystems, uniqueInterconnections, computerA, computerB, computerC);
                    }
                }
            }
        }
        return interconnectedSystems;
    }

    private boolean isInterconnected(List<Computer> connectionsB, List<Computer> connectionsC, Computer computerB, Computer computerC) {
        return connectionsB != null && connectionsB.contains(computerC) && connectionsC != null && connectionsC.contains(computerB);
    }

    private void addUniqueInterconnection(List<ComputerInterconnection> interconnectedSystems, Set<String> uniqueInterconnections,
                                          Computer computerA, Computer computerB, Computer computerC) {
        List<Computer> sortedConnection = Arrays.asList(computerA, computerB, computerC);
        sortedConnection.sort(Comparator.comparing(Computer::getName));
        String uniqueKey = sortedConnection.stream().map(Computer::getName).collect(Collectors.joining("-"));
        if (!uniqueInterconnections.contains(uniqueKey)) {
            uniqueInterconnections.add(uniqueKey);
            interconnectedSystems.add(new ComputerInterconnection(sortedConnection.get(0), sortedConnection.get(1), sortedConnection.get(2)));
        }
    }
}