package zvds.aoc;

import zvds.domain.Lock;
import zvds.domain.ObjectType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PartOne implements PartInterface {
    @Override
    public void run(File input) {
        try {
            List<String> lines = Files.readAllLines(input.toPath());
            String inputContent = String.join("\n", lines);
            String result = solve(inputContent);
            System.out.println(result);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    private String solve(String input) {
        List<Lock> objects = getObjects(input);
        List<Lock> keys = new ArrayList<>();
        List<Lock> locks = new ArrayList<>();

        for (Lock obj : objects) {
            if (obj.getType() == ObjectType.KEY) {
                keys.add(obj);
            } else {
                locks.add(obj);
            }
        }

        int totalOverlaps = 0;
        for (Lock key : keys) {
            for (Lock lock : locks) {
                if (key.overlaps(lock)) {
                    totalOverlaps++;
                }
            }
        }

        return String.valueOf(totalOverlaps);
    }

    private List<Lock> getObjects(String input) {
        String[] schematics = input.split("\n\n");
        List<Lock> objects = new ArrayList<>();

        for (String schematic : schematics) {
            String[] lines = schematic.split("\n");
            ObjectType type = lines[0].charAt(0) == '#' ? ObjectType.LOCK : ObjectType.KEY;
            List<String> reducedSchematic = new ArrayList<>();
            for (int i = 1; i < lines.length - 1; i++) {
                reducedSchematic.add(lines[i]);
            }
            List<Integer> value = new ArrayList<>();
            for (int j = 0; j < reducedSchematic.size(); j++) {
                int columnSum = 0;
                for (int i = 0; i < reducedSchematic.get(j).length(); i++) {
                    if (reducedSchematic.get(i).charAt(j) == '#') {
                        columnSum += 1;
                    }
                }
                value.add(columnSum);
            }
            objects.add(new Lock(type, value));
        }

        return objects;
    }

}
