package zvds.calculator.appearance;

import java.util.HashMap;

public class SimpleAppearanceCalculator implements AppearanceCalculator {

    @Override
    public int calculateAppearances(int[] array1, int[] array2) {
        HashMap<Integer, Integer> appearanceMap = new HashMap<>();
        for (int i = 0; i < array1.length; i++) {
            int amountOfMatches = 0;
            for (int j = 0; j < array2.length; j++) {
                if (array1[i] == array2[j]) {
                    amountOfMatches++;
                }
            }
            appearanceMap.put(array1[i], amountOfMatches);
        }
        int totalAppearanceCount = 0;
        for (int i = 0; i < array1.length; i++) {
            int appearanceCount = array1[i] * appearanceMap.get(array1[i]);
            totalAppearanceCount += appearanceCount;
        }
        return totalAppearanceCount;
    }
}