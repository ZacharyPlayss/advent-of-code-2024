package zvds.aoc;

import zvds.domain.Computer;
import zvds.domain.ComputerConnection;
import zvds.domain.ComputerInterconnection;
import zvds.manager.ComputerConnectionManager;
import zvds.manager.InterconnectionManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class PartTwo implements PartInterface {
    @Override
    public void run(File input) {
        try {
            System.out.println("\n");
            //PART ONE CODE.
            ComputerConnectionManager ccm = new ComputerConnectionManager();
            InterconnectionManager icm = new InterconnectionManager();
            List<ComputerConnection> connections = parseConnections(input);
            HashMap<Computer, List<Computer>> connectionMap = ccm.createComputerConnections(connections);
            List<ComputerInterconnection> interconnections = icm.findInterconnectedSystems(connectionMap);
            Predicate<ComputerInterconnection> nameContainsT = interconnection ->
                    interconnection.getComputerA().getName().startsWith("t") ||
                            interconnection.getComputerB().getName().startsWith("t") ||
                            interconnection.getComputerC().getName().startsWith("t");
            List<ComputerInterconnection>filteredInterConnections = icm.filterInterConnectedLists(interconnections, nameContainsT);

            //PART TWO CODE
            List<Computer> largestInterconnection = icm.findLargestInterconnection(filteredInterConnections);
            for(Computer computer : largestInterconnection) {
                System.out.println(computer.getName());
            }
            Collections.sort(largestInterconnection, (a, b) -> a.getName().compareTo(b.getName()));
            String result = String.join(",", largestInterconnection.stream()
                    .map(Computer::getName)
                    .toArray(String[]::new));
            System.out.println("Password found is: "+  result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<ComputerConnection> parseConnections(File input) {
        List<ComputerConnection> connections = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(input.toPath());
            for (String line : lines) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    Computer computerA = new Computer(parts[0].trim());
                    Computer computerB = new Computer(parts[1].trim());
                    ComputerConnection connection = new ComputerConnection(computerA, computerB);
                    connections.add(connection);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connections;
    }
}
