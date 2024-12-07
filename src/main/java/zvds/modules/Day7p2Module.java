package zvds.modules;

import zvds.Day7p2Main;

import java.io.IOException;

public class Day7p2Module implements Module {
    @Override
    public void launch() {
        try {
            Day7p2Main.main(new String[]{}); // Adjust as needed
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getTitle() {
        return "Day 7: Bridge Repair (part 2)";
    }

    @Override
    public String getName() {
        return "Day7p2";
    }
}