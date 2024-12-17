package zvds;

import zvds.calculator.appearance.AppearanceCalculator;
import zvds.calculator.appearance.SimpleAppearanceCalculator;
import zvds.reader.TxtReader;

import java.io.File;


public class Day1p2Main {
    public static void main(String[] args) {
        File input = fetchInputFile("input.txt");
        int[][] allArrays = TxtReader.readFileTo2DArrayAsColumns(input);
        int[] array1 = allArrays[0];
        int[] array2 = allArrays[1];

        AppearanceCalculator appearanceCounter = new SimpleAppearanceCalculator();
        int totalAppearanceCount = appearanceCounter.calculateAppearances(array1, array2);

        System.out.println("Similarity score: " + totalAppearanceCount);
    }

    private static File fetchInputFile(String fileName){
        ClassLoader classLoader = Day1p2Main.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}