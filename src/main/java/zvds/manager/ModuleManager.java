package zvds.manager;

import zvds.modules.Module;
import java.util.List;
import java.util.Scanner;

public class ModuleManager {
    private List<Module> modules;
    private AsciiArtManager asciiArtManager;

    public ModuleManager(List<Module> modules, AsciiArtManager asciiArtManager) {
        this.modules = modules;
        this.asciiArtManager = asciiArtManager;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Select a puzzle to run its code: (0 or STOP to stop)\n");

            for (int i = 0; i < modules.size(); i++) {
                System.out.println((i + 1) + ". " + modules.get(i).getTitle());
            }

            System.out.println();
            System.out.print("Selected ID: ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (userInput.equals("0") || userInput.equals("stop")) {
                asciiArtManager.displayAsciiArt("/ascii/Goodbye.txt");
                System.out.println("Thanks for checking out the code!\nSee you next time!");
                break;
            }

            try {
                int choice = Integer.parseInt(userInput);

                if (choice < 1 || choice > modules.size()) {
                    System.out.println("Invalid ID! Please select a valid ID.");
                } else {
                    Module selectedModule = modules.get(choice - 1);
                    asciiArtManager.displayAsciiArt("/ascii/" + selectedModule.getName() + ".txt");
                    selectedModule.launch();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid ID number or type 'stop' to exit.");
            }
        }
    }
}
