package zvds.builder;

import zvds.domain.Computer;
import zvds.domain.ComputerInterconnection;

import java.util.*;
import java.util.stream.Collectors;

public class ConnectionMapBuilder {
    public Map<Computer, Set<Computer>> buildConnectionMap(List<ComputerInterconnection> interconnections) {
        Map<Computer, Set<Computer>> connectionMap = new HashMap<>();
        for (ComputerInterconnection interconnection : interconnections) {
            Computer[] computers = {interconnection.getComputerA(), interconnection.getComputerB(), interconnection.getComputerC()};
            for (Computer computer : computers) {
                connectionMap.putIfAbsent(computer, new HashSet<>());
                for (Computer other : computers) {
                    if (!computer.equals(other)) {
                        connectionMap.get(computer).add(other);
                    }
                }
            }
        }
        return connectionMap;
    }

    public void printConnectionMap(Map<Computer, Set<Computer>> connectionMap) {
        System.out.println("Connection Map:");
        for (Map.Entry<Computer, Set<Computer>> entry : connectionMap.entrySet()) {
            System.out.println(entry.getKey().getName() + " -> " +
                    entry.getValue().stream().map(Computer::getName).collect(Collectors.joining(", ")));
        }
    }
}