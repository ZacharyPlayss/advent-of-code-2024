package zvds;

import zvds.reader.TxtReader;

public class Day6p1Main {
    static int currentGuardX = -1;
    static int currentGuardY = -1;
    static int guardStateIndex = -1;
    static char[] possibleDirections = {'^', '>', 'v', '<'};
    static int[][] directionMoves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static char[][] input = TxtReader.readFileToChar2DArray("E:\\Development\\Java\\adventofcode2024\\Day6p1\\src\\main\\resources\\test-input.txt");

    public static void main(String[] args) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                System.out.print(input[i][j]);
                if (charArrayContains(input[i][j], possibleDirections)) {
                    currentGuardX = i;
                    currentGuardY = j;
                    guardStateIndex = GetCurrentGuardState(input[i][j], possibleDirections);
                }
            }
            System.out.println();
        }

        System.out.println("De guard staat op positie " + currentGuardX + " " + currentGuardY);
        System.out.println("De guard staat op positie en is in staat : " + possibleDirections[guardStateIndex]);

        while (containsGuard(input)) {
            moveGuard(input, currentGuardX, currentGuardY, guardStateIndex);

            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < input[i].length; j++) {
                    System.out.print(input[i][j]);
                }
                System.out.println();
            }

            System.out.println();
        }

        // Count the number of 'X' in the grid after the simulation
        int validSpotsCount = countValidSpots(input);
        System.out.println("Total valid spots visited: " + validSpotsCount);
    }

    private static void moveGuard(char[][] input, int currentGuardX, int currentGuardY, int guardStateIndex) {
        while (true) {
            char direction = possibleDirections[guardStateIndex];
            int[] moveCalculations = directionMoves[guardStateIndex];
            int newX = currentGuardX + moveCalculations[0];
            int newY = currentGuardY + moveCalculations[1];

            // Check if the new position is out of bounds
            if (newX < 0 || newY < 0 || newX >= input.length || newY >= input[0].length) {
                // Guard moves off the screen
                input[currentGuardX][currentGuardY] = 'X'; // Mark the last spot
                return;
            }

            // Check if the move is valid
            if (input[newX][newY] != '#') {
                input[currentGuardX][currentGuardY] = 'X'; // Mark the old position
                input[newX][newY] = direction; // Update the guard's new position
                setCurrentGuardX(newX);
                setCurrentGuardY(newY);
                return;
            } else {
                // Rotate the guard and retry
                rotateGuard(input, currentGuardX, currentGuardY);
                guardStateIndex = Day6p1Main.guardStateIndex;
            }
        }
    }

    private static void rotateGuard(char[][] input, int currentGuardX, int currentGuardY) {
        guardStateIndex = (guardStateIndex + 1) % possibleDirections.length;
        input[currentGuardX][currentGuardY] = possibleDirections[guardStateIndex];
    }

    private static int GetCurrentGuardState(char c, char[] possibleSymbols) {
        for (int i = 0; i < possibleSymbols.length; i++) {
            if (possibleSymbols[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private static boolean charArrayContains(char c, char[] array) {
        for (char x : array) {
            if (x == c) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsGuard(char[][] input) {
        for (char[] row : input) {
            for (char cell : row) {
                if (charArrayContains(cell, possibleDirections)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int countValidSpots(char[][] input) {
        int count = 0;
        for (char[] row : input) {
            for (char cell : row) {
                if (cell == 'X') {
                    count++;
                }
            }
        }
        return count;
    }

    public static int getCurrentGuardX() {
        return currentGuardX;
    }

    public static int getCurrentGuardY() {
        return currentGuardY;
    }

    public static void setCurrentGuardX(int currentGuardX) {
        Day6p1Main.currentGuardX = currentGuardX;
    }

    public static void setCurrentGuardY(int currentGuardY) {
        Day6p1Main.currentGuardY = currentGuardY;
    }

    public static void setGuardStateIndex(int guardStateIndex) {
        Day6p1Main.guardStateIndex = guardStateIndex;
    }
}
