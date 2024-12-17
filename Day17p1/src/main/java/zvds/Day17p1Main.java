package zvds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day17p1Main {
    private int registerA;
    private int registerB;
    private int registerC;
    private List<Integer> program;
    private int instructionPointer;
    private List<Integer> outputValues;

    public Day17p1Main(int registerA, int registerB, int registerC, List<Integer> program) {
        this.registerA = registerA;
        this.registerB = registerB;
        this.registerC = registerC;
        this.program = program;
        this.instructionPointer = 0;
        this.outputValues = new ArrayList<>();
    }

    private void adv(int operand) {
        int numerator = registerA;
        int denominator = (int) Math.pow(2, getComboOperandValue(operand));
        registerA = numerator / denominator;
        incrementPointer();
    }

    private void bdv(int operand) {
        int numerator = registerA;
        int denominator = (int) Math.pow(2, getComboOperandValue(operand));
        registerB = numerator / denominator;
        incrementPointer();
    }

    private void cdv(int operand) {
        int numerator = registerA;
        int denominator = (int) Math.pow(2, getComboOperandValue(operand));
        registerC = numerator / denominator;
        incrementPointer();
    }

    private void bxl(int operand) {
        registerB = registerB ^ operand;
        incrementPointer();
    }

    private void bst(int operand) {
        registerB = getComboOperandValue(operand) % 8;
        incrementPointer();
    }

    private void jnz(int operand) {
        if (registerA == 0) {
            incrementPointer();
            return;
        }
        instructionPointer = operand;
    }

    private void bxc(int operand) {
        registerB = registerB ^ registerC;
        incrementPointer();
    }

    private void out(int operand) {
        int operandValue = getComboOperandValue(operand);
        outputValues.add(operandValue % 8);
        incrementPointer();
    }

    private void incrementPointer() {
        instructionPointer += 2;
    }

    private int getComboOperandValue(int operand) {
        if (0 <= operand && operand <= 3) {
            return operand;
        }
        if (operand == 4) {
            return registerA;
        }
        if (operand == 5) {
            return registerB;
        }
        if (operand == 6) {
            return registerC;
        }
        throw new IllegalArgumentException("Not a valid program");
    }

    private void runInstruction(int opcode, int operand) {
        switch (opcode) {
            case 0:
                adv(operand);
                break;
            case 6:
                bdv(operand);
                break;
            case 7:
                cdv(operand);
                break;
            case 1:
                bxl(operand);
                break;
            case 2:
                bst(operand);
                break;
            case 3:
                jnz(operand);
                break;
            case 4:
                bxc(operand);
                break;
            case 5:
                out(operand);
                break;
            default:
                throw new IllegalArgumentException("Not a valid opcode");
        }
    }

    private void getOutput() {
        System.out.println(outputValues.stream().map(String::valueOf).collect(Collectors.joining(",")));
    }

    public void runProgram() {
        while (true) {
            if (instructionPointer >= program.size()) {
                break;
            }
            if (instructionPointer + 1 >= program.size()) {
                break;
            }
            int opcode = program.get(instructionPointer);
            int operand = program.get(instructionPointer + 1);
            runInstruction(opcode, operand);
        }
        getOutput();
    }

    public static List<String> readInputFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }
        }
        return lines;
    }

    public static void solution(List<String> lines) {
        int registerA = Integer.parseInt(lines.get(0).substring(12));
        int registerB = Integer.parseInt(lines.get(1).substring(12));
        int registerC = Integer.parseInt(lines.get(2).substring(12));
        List<Integer> program = Arrays.stream(lines.get(4).substring(9).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Day17p1Main machine = new Day17p1Main(registerA, registerB, registerC, program);
        machine.runProgram();
    }

    public static void main(String[] args) {
        try {
            List<String> lines = readInputFile("E:\\Development\\Java\\adventofcode2024\\Day17p1\\src\\main\\resources\\input.txt");
            solution(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
