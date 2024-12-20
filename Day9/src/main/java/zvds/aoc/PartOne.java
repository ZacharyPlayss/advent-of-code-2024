package zvds.aoc;

import zvds.aoc.PartInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class PartOne implements PartInterface {
    @Override
    public void run(File input) {
        String inputString = null;
        try {
            inputString = Files.readString(input.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        inputString = generateDiskMap(inputString);
        String sorted = sortFiles(inputString);
        System.out.println("Input:  " + input);
        System.out.println("Sorted: " + sorted);
        DecimalFormat df = new DecimalFormat("0");
        System.out.println("The checksum is: " + df.format(calculateSum(sorted)));
    }

    public String generateDiskMap(String input) {
        StringBuilder result = new StringBuilder();

        // Loop through the input in pairs (file length, free space length)
        int fileIndex = 0;
        for (int i = 0; i < input.length(); i++) {
            // Get the file length (current digit)
            int fileLength = Character.getNumericValue(input.charAt(i));
            if (fileLength < 0 || fileLength > 9) {
                throw new IllegalArgumentException("Invalid character in input: " + input.charAt(i));
            }

            // Append the file blocks to the result
            for (int j = 0; j < fileLength; j++) {
                result.append(fileIndex); // File represented by its index
            }
            fileIndex++;

            // If there's a next digit (free space length)
            if (i + 1 < input.length()) {
                int freeSpaceLength = Character.getNumericValue(input.charAt(++i));
                if (freeSpaceLength < 0 || freeSpaceLength > 9) {
                    throw new IllegalArgumentException("Invalid character in input: " + input.charAt(i));
                }

                // Append the free space to the result
                for (int j = 0; j < freeSpaceLength; j++) {
                    result.append("."); // Free space represented by dots
                }
            }
        }

        return result.toString();
    }

    private double calculateSum(String sortedString){
        double count = 0;
        try{
            char[] sortedCharacters = sortedString.toCharArray();
            for(int i = 0; i < sortedCharacters.length; i++){
                count += (Double.parseDouble(String.valueOf(sortedCharacters[i])) * i);
            }
        }catch (Exception e){
            System.out.println("something went wrong");
        }
        return count;
    }

    public  String sortFiles(String fileString) {
        // Convert the string to a mutable character array
        char[] fileArray = fileString.toCharArray();

        // Repeat until no dots are between numbers
        while (containsDotBetweenNumbers(fileArray)) {
            // Find the first dot
            int firstDot = findFirstDot(fileArray);
            // Find the last number
            int lastNumberIndex = findLastNumberIndex(fileArray);
            // Move the last number to the first dot
            fileArray[firstDot] = fileArray[lastNumberIndex];
            fileArray[lastNumberIndex] = '.';
        }

        return new String(fileArray);
    }

    // Helper method to check if there is any dot between numbers
    private  boolean containsDotBetweenNumbers(char[] fileArray) {
        for (int i = 0; i < fileArray.length - 1; i++) {
            if (fileArray[i] == '.' && Character.isDigit(fileArray[i + 1])) {
                return true;
            }
        }
        return false;
    }

    // Helper method to find the index of the first dot
    private  int findFirstDot(char[] fileArray) {
        for (int i = 0; i < fileArray.length; i++) {
            if (fileArray[i] == '.') {
                return i;
            }
        }
        throw new IllegalStateException("No dot found in the array.");
    }

    // Helper method to find the index of the last number
    private  int findLastNumberIndex(char[] fileArray) {
        for (int i = fileArray.length - 1; i >= 0; i--) {
            if (Character.isDigit(fileArray[i])) {
                return i;
            }
        }
        throw new IllegalStateException("No number found in the array.");
    }
}
