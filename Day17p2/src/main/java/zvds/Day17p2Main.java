package zvds;

import java.io.*;
import java.util.*;
import java.util.function.IntFunction;
import java.util.regex.*;

public class Day17p2Main {

    enum Opcode {
        ADV, BXL, BST, JNZ, BXC, OUT, BDV, CDV
    }

    public static void main(String[] args) throws IOException {
        String input = readInputFromFile("E:\\Development\\Java\\adventofcode2024\\Day17p2\\src\\main\\resources\\input.txt");
        Day17p2Main solution = new Day17p2Main();
        System.out.println("Part One: " + solution.partOne(input));
        System.out.println("Part Two: " + solution.partTwo(input));
    }

    private static String readInputFromFile(String filename) throws IOException {
        StringBuilder input = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                input.append(line).append("\n");
            }
        }
        return input.toString();
    }

    public String partOne(String input) {
        var parsedData = parse(input);
        var result = run(parsedData.state, parsedData.program);
        return String.join(",", result.stream().map(String::valueOf).toArray(String[]::new));
    }
    public String partTwo(String input) {
        var parsedData = parse(input);
        var result = generateA(parsedData.program, parsedData.program);
        return String.valueOf(Collections.min(result));
    }
    public List<Long> run(List<Long> state, List<Long> program) {
        var combo = new IntFunction<Long>() {
            @Override
            public Long apply(int op) {
                return (op < 4) ? (long) op : state.get(op - 4);
            }
        };

        List<Long> result = new ArrayList<>();
        for (int ip = 0; ip < program.size(); ip += 2) {
            int opCode = program.get(ip).intValue();
            int operand = program.get(ip + 1).intValue();

            switch (Opcode.values()[opCode]) {
                case ADV:
                    state.set(0, state.get(0) >> combo.apply(operand).intValue());
                    break;
                case BDV:
                    state.set(1, state.get(0) >> combo.apply(operand).intValue());
                    break;
                case CDV:
                    state.set(2, state.get(0) >> combo.apply(operand).intValue());
                    break;
                case BXL:
                    state.set(1, state.get(1) ^ operand);
                    break;
                case BST:
                    state.set(1, combo.apply(operand) % 8);
                    break;
                case JNZ:
                    ip = (state.get(0) == 0) ? ip : operand - 2;
                    break;
                case BXC:
                    state.set(1, state.get(1) ^ state.get(2));
                    break;
                case OUT:
                    result.add(combo.apply(operand) % 8);
                    break;
            }
        }
        return result;
    }
    public List<Long> generateA(List<Long> program, List<Long> output) {
        List<Long> result = new ArrayList<>();
        if (output.isEmpty()) {
            result.add(0L);
            return result;
        }

        var generatedA = generateA(program, output.subList(1, output.size()));
        for (long aHigh : generatedA) {
            for (int aLow = 0; aLow < 8; aLow++) {
                long a = aHigh * 8 + aLow;
                if (run(new ArrayList<>(List.of(a, 0L, 0L)), program).equals(output)) {
                    result.add(a);
                }
            }
        }

        return result;
    }
    public ParseResult parse(String input) {
        String[] blocks = input.split("\n\n");
        List<Long> state = parseNumbers(blocks[0]);
        List<Long> program = parseNumbers(blocks[1]);
        return new ParseResult(state, program);
    }
    public List<Long> parseNumbers(String str) {
        List<Long> numbers = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\d+").matcher(str);
        while (matcher.find()) {
            numbers.add(Long.parseLong(matcher.group()));
        }
        return numbers;
    }
    static class ParseResult {
        List<Long> state;
        List<Long> program;
        ParseResult(List<Long> state, List<Long> program) {
            this.state = state;
            this.program = program;
        }
    }
}
