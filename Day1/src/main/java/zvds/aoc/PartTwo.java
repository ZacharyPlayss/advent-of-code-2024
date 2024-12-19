package zvds.aoc;

import zvds.calculator.appearance.AppearanceCalculator;
import zvds.calculator.appearance.SimpleAppearanceCalculator;
import zvds.reader.TxtReader;

import java.io.File;
import java.util.List;

public class PartTwo implements PartInterface{
    @Override
    public void run(File input) {
        int[][] allArrays = TxtReader.readFileTo2DArrayAsColumns(input);
        int[] array1 = allArrays[0];
        int[] array2 = allArrays[1];

        AppearanceCalculator appearanceCounter = new SimpleAppearanceCalculator();
        int totalAppearanceCount = appearanceCounter.calculateAppearances(array1, array2);

        System.out.println("Similarity score: " + totalAppearanceCount);
    }
}
