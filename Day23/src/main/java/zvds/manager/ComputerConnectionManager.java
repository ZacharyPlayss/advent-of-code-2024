package zvds.manager;

import zvds.domain.Computer;
import zvds.domain.ComputerConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComputerConnectionManager {
    public HashMap<Computer, List<Computer>> createComputerConnections(List<ComputerConnection> inputConnections) {
        HashMap<Computer, List<Computer>> computerConnections = new HashMap<>();
        for (ComputerConnection connection : inputConnections) {
            Computer computerA = connection.getComputerA();
            Computer computerB = connection.getComputerB();
            computerConnections.putIfAbsent(computerA, new ArrayList<>());
            computerConnections.get(computerA).add(computerB);
            computerConnections.putIfAbsent(computerB, new ArrayList<>());
            computerConnections.get(computerB).add(computerA);
        }
        return computerConnections;
    }
}
