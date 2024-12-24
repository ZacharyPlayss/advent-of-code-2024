package zvds.aoc;

import zvds.domain.Connection;
import zvds.domain.Wire;
import zvds.domain.XorGate;
import zvds.domain.binarygate.AndGate;
import zvds.domain.binarygate.GateInterface;
import zvds.domain.binarygate.OrGate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class PartTwo implements PartInterface {
    private List<Wire> wires;
    private List<Connection> connectionList;

    @Override
    public void run(File input) {
        try {
            List<String> lines = Files.readAllLines(input.toPath());
            parseInput(lines);
            List<String> mismatchedWires = findMismatchedWires();

            Collections.sort(mismatchedWires);
            System.out.println("Mismatched wires: " + String.join(",", mismatchedWires));
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }

    private List<String> findMismatchedWires() {
        Set<String> mismatchedWires = new HashSet<>();
        for (Connection conn : connectionList) {
            String input1 = conn.getInput1().getName();
            String input2 = conn.getInput2().getName();
            String output = conn.getOutput().getName();

            if (input1.startsWith("x") && input2.startsWith("y") &&
                    input1.substring(1).equals(input2.substring(1))) {
                String expectedOutput = "z" + input1.substring(1);
                if (!output.equals(expectedOutput)) {
                    mismatchedWires.add(output);
                    mismatchedWires.add(expectedOutput);
                }

                if (mismatchedWires.size() == 8) {
                    break;
                }
            }
        }
        return new ArrayList<>(mismatchedWires);
    }

    private void parseInput(List<String> lines) {
        wires = new ArrayList<>();
        connectionList = new ArrayList<>();
        boolean parsingConnections = false;

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            if (!parsingConnections) {
                if (line.contains("->")) {
                    parsingConnections = true;
                } else {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String name = parts[0].trim();
                        int value = Integer.parseInt(parts[1].trim());
                        wires.add(new Wire(name, value));
                    }
                }
            }

            if (parsingConnections) {
                String[] parts = line.split("->");
                if (parts.length == 2) {
                    String operation = parts[0].trim();
                    String outputWireName = parts[1].trim();

                    String[] operationParts = operation.split(" ");
                    if (operationParts.length == 3) {
                        String input1Name = operationParts[0].trim();
                        String gateName = operationParts[1].trim();
                        String input2Name = operationParts[2].trim();

                        Wire input1 = findOrCreateWireByName(input1Name);
                        Wire input2 = findOrCreateWireByName(input2Name);
                        Wire output = findOrCreateWireByName(outputWireName);

                        GateInterface gate = createGate(gateName);

                        Connection connection = new Connection(input1, input2, output, gate);
                        connectionList.add(connection);
                    }
                }
            }
        }
        connectionList.sort(Comparator.comparing(connection -> connection.getOutput().getName()));
    }

    private Wire findWireByName(String name) {
        return wires.stream()
                .filter(wire -> wire.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private Wire findOrCreateWireByName(String name) {
        Wire wire = findWireByName(name);
        if (wire == null) {
            wire = new Wire(name); // Create without initial value
            wires.add(wire);
        }
        return wire;
    }

    private GateInterface createGate(String gateName) {
        switch (gateName.toUpperCase()) {
            case "AND":
                return new AndGate();
            case "OR":
                return new OrGate();
            case "XOR":
                return new XorGate();
            default:
                throw new IllegalArgumentException("Unsupported gate: " + gateName);
        }
    }
}
