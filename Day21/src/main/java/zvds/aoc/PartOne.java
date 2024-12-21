package zvds.aoc;

import zvds.controller.RobotController;
import zvds.domain.keypad.DirectionalKeypad;
import zvds.domain.keypad.NumericKeyPad;
import zvds.navigator.KeypadNavigator;
import zvds.navigator.NumericKeypadNavigator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartOne implements PartInterface {
    private final String[][] numericKeyPadLayout = {
            {"7", "8", "9"},
            {"4", "5", "6"},
            {"1", "2", "3"},
            {null, "0", "A"}
    };
    private final String[][] directionalKeyPadLayout = {
            {null, "^", "A"},
            {"<", "v", ">"}
    };
    @Override
    public void run(File input) {
        NumericKeyPad nkp = new NumericKeyPad(numericKeyPadLayout);
        DirectionalKeypad dkp = new DirectionalKeypad(directionalKeyPadLayout);
        NumericKeypadNavigator kpn = new NumericKeypadNavigator(nkp);
        KeypadNavigator kpn1 = new KeypadNavigator(nkp);
        KeypadNavigator kpn2 = new KeypadNavigator(dkp);
        KeypadNavigator kpn3 = new KeypadNavigator(dkp);


        RobotController robotLayer1 = new RobotController(nkp, "R1");
        RobotController robotLayer2 = new RobotController(dkp,"R2");
        RobotController controllerLayer3 = new RobotController(dkp,"R3");

        nkp.printKeypad();
        System.out.println();
        dkp.printKeypad();

        List<String> lines = null;
        try {
            lines = Files.readAllLines(input.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, String> commandMap = new HashMap<>();
        for(int i = 0; i < lines.size(); i++){
            String inputCommand = lines.get(i);
            String commands = kpn1.calculateCommands(inputCommand);
            String layer2Commands = kpn2.calculateCommands(commands);
            String layer3Commands = kpn3.calculateCommands(layer2Commands);
            System.out.println(inputCommand);
            System.out.println("Commands " + commands);
            System.out.println("Commands 2 " + layer2Commands);
            System.out.println("Commands 3 " +layer3Commands);

            String executedLayer3Commands = controllerLayer3.processCommands(layer3Commands);
            String executedLayer2Commands = robotLayer2.processCommands(executedLayer3Commands);
            String executedLayer1Commands = robotLayer1.processCommands(executedLayer2Commands);
            commandMap.put(inputCommand, layer3Commands);
        }

        for (Map.Entry<String, String> entry : commandMap.entrySet()) {
            String inputCommand = entry.getKey();
            String layer3Command = entry.getValue();
            System.out.println(inputCommand + ": " + layer3Command);
        }

        System.out.println("Complexity of this input is : " + calculateComplexity(commandMap));
    }
    //EXPCETED : 163086 (due to my python solution)
    private long calculateComplexity(Map<String, String> inputMap) {
        long totalComplexity = 0;
        for (Map.Entry<String, String> entry : inputMap.entrySet()) {
            String inputCommand = entry.getKey();
            String directionalCommand = entry.getValue();
            String numericPart = inputCommand.replaceAll("[^0-9]", "");
            int numericValue = Integer.parseInt(numericPart);

            int buttonPressesLength = directionalCommand.length();
            System.out.println((buttonPressesLength - 2) + " " +  numericValue);
            long complexity = (buttonPressesLength - 2)* numericValue;
            totalComplexity += complexity;
        }
        return totalComplexity;
    }
}
