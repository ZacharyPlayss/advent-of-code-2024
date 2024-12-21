package zvds.domain.keypad;

public class NumericKeyPad implements KeyPadInterface {

    private final String[][] layout;

    public NumericKeyPad(String[][] layout) {
        this.layout = layout;
    }

    @Override
    public String[][] getLayout() {
        return this.layout;
    }

    public void printKeypad() {
        for (String[] row : layout) {
            for (String key : row) {
                if (key == null) {
                    System.out.print("    ");
                } else {
                    System.out.print(" " + key + " ");
                }
            }
            System.out.println();
        }
    }

}
