package zvds.controller;

import zvds.domain.keypad.DirectionalKeypad;
import zvds.domain.keypad.KeyPadInterface;
import zvds.domain.keypad.NumericKeyPad;

public class RobotController {
    private final KeyPadInterface keyPad;
    String name;
    private int currentRow;
    private int currentCol;

    public RobotController(KeyPadInterface keyPad,String name){
        this.keyPad = keyPad;
        this.name = name;
        if(keyPad instanceof NumericKeyPad){
            this.currentRow = 3;
            this.currentCol = 2;
        }
        else{
            this.currentRow = 0;
            this.currentCol = 2;
        }
    }
    public String processCommands(String commands){
        StringBuilder result = new StringBuilder();
        for(char command : commands.toCharArray()){
            switch (command) {
                case '^':
                    move(-1, 0);
                    break;
                case 'v':
                    move(1, 0);
                    break;
                case '<':
                    move(0, -1);
                    break;
                case '>':
                    move(0, 1);
                    break;
                case 'A':
                    result.append(pressButton());
                    break;
                default:
                    System.out.println("Invalid command: " + command);
            };
        }
        return result.toString();
    }
    private void move(int rowDelta, int colDelta) {
        int newRow = currentRow + rowDelta;
        int newCol = currentCol + colDelta;

        String[][] layout = keyPad.getLayout();

        if (newRow >= 0 && newRow < layout.length &&
                newCol >= 0 && newCol < layout[newRow].length &&
                layout[newRow][newCol] != null) {
            currentRow = newRow;
            currentCol = newCol;
        } else {
            System.out.println(name + " cannot move in that direction: Out of bounds or invalid.");
        }
    }

    private String pressButton() {
        String[][] layout = keyPad.getLayout();
        String button = layout[currentRow][currentCol];
        return button;
    }
}
