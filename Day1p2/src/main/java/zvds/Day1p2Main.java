package zvds;

import zvds.reader.CsvReader;

import java.util.HashMap;

public class Day1p2Main {
    public static void main(String[] args) {
        HashMap<Integer, Integer> appearanceMap = new HashMap<Integer, Integer>();
        int[][] allArrays= CsvReader.readArraysFromCSV("E:\\Development\\Java\\adventofcode2024\\Day1p2\\src\\main\\resources\\input-sheet.csv");
        int[] array1 = allArrays[0];
        int[] array2 = allArrays[1];

        //int[] array1 = {3,4,2,1,3,3};
        //int[] array2 = {4,3,5,3,9,3};

        for(int i = 0 ; i < array1.length; i++){
            int amountOfMatches = 0;
            for(int j = 0; j < array2.length; j++){
                if(array1[i] == array2[j]){
                    amountOfMatches++;
                }
            }
            appearanceMap.put(array1[i], amountOfMatches);
        }

        int totalAppearanceCount = 0;
        for(int i =0; i < array1.length; i++){
            //System.out.println(array1[i] + " " +appearanceMap.get(array1[i]));
            int appearanceCount = array1[i] * appearanceMap.get(array1[i]);
            totalAppearanceCount += appearanceCount;
            System.out.println(array1[i] + " "+appearanceCount);
        }
        System.out.println(totalAppearanceCount);

    }
}