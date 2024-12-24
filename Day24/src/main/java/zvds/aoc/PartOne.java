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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PartOne implements PartInterface {
    private List<Wire> wires;
    private List<Connection> connectionList;

    @Override
    public void run(File input) {
        try {
            List<String> lines = Files.readAllLines(input.toPath());
            parseInput(lines);

            simulateCircuit();

            System.out.println("\nWires (starting with 'z') after execution:");
            List<Wire> zWires = wires.stream()
                    .filter(wire -> wire.getName().startsWith("z"))
                    .sorted((w1, w2) -> {
                        int num1 = Integer.parseInt(w1.getName().substring(1));
                        int num2 = Integer.parseInt(w2.getName().substring(1));
                        return Integer.compare(num1, num2);
                    })
                    .toList();

            for (Wire wire : zWires) {
                System.out.println(wire.toString());
            }

            StringBuilder binaryNumberBuilder = new StringBuilder();
            for (int i = zWires.size() - 1; i >= 0; i--) {
                binaryNumberBuilder.append(zWires.get(i).getValue());
            }

            String binaryNumber = binaryNumberBuilder.toString();
            System.out.println("\nGenerated Binary Number: " + binaryNumber);

            long decimalValue = Long.parseLong(binaryNumber, 2);
            System.out.println("Decimal Value: " + decimalValue);

        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }

    private void simulateCircuit() {
        boolean changed;
        do {
            changed = false;
            for (Connection connection : connectionList) {
                if (connection.runThroughGate()) {
                    changed = true;
                    System.out.println(connection + " -> " + connection.getOutput().getValue());
                }
            }
        } while (changed);
    }

    private void resetCircuit() {
        for (Wire wire : wires) {
            if (!wire.getName().matches("x\\d+|y\\d+")) {
                wire.setValue(null);
            }
        }
        for (Connection connection : connectionList) {
            connection.reset();
        }
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
