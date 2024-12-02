package zvds.manager;

import zvds.Main;

import java.io.InputStream;
import java.util.Scanner;

public class AsciiArtManager {
    public void displayAsciiArt(String path) {
        try (InputStream inputStream = Main.class.getResourceAsStream(path)) {
            if (inputStream == null) {
                System.out.println("No ASCII art found at " + path);
                return;
            }

            Scanner fileScanner = new Scanner(inputStream);
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error displaying ASCII art: " + e.getMessage());
        }
    }
}
