package zvds.domain.keypad;

public class DirectionalKeypad implements KeyPadInterface{
    private final String[][] layout;

    public DirectionalKeypad(String[][] layout) {
        this.layout = layout;
    }

    @Override
    public String[][] getLayout() {
        return layout;
    }

    public void printKeypad() {
        for (String[] row : layout) {
            for (String key : row) {
                if (key == null) {
                    System.out.print("    "); // Empty space
                } else {
                    System.out.print(" " + key + " ");
                }
            }
            System.out.println(); // Move to the next row
        }
    }
}
