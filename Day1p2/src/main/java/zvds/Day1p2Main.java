package zvds;

import zvds.calculator.appearance.AppearanceCalculator;
import zvds.calculator.appearance.SimpleAppearanceCalculator;
import zvds.reader.CsvReader;

import java.util.HashMap;

public class Day1p2Main {
    public static void main(String[] args) {
        int[][] allArrays = CsvReader.readArraysFromCSV("E:\\Development\\Java\\adventofcode2024\\Day1p2\\src\\main\\resources\\input-sheet.csv");
        int[] array1 = allArrays[0];
        int[] array2 = allArrays[1];

        AppearanceCalculator appearanceCounter = new SimpleAppearanceCalculator();
        int totalAppearanceCount = appearanceCounter.calculateAppearances(array1, array2);

        System.out.println("Similarity score: " + totalAppearanceCount);
    }
}