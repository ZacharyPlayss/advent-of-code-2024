package zvds.modules;

import zvds.Day7p1Main;

import java.io.IOException;

public class Day7p1Module implements Module {
    @Override
    public void launch() {
        try {
            Day7p1Main.main(new String[]{}); // Adjust as needed
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getTitle() {
        return "Day 7: Bridge Repair (part 1)";
    }

    @Override
    public String getName() {
        return "Day7p1";
    }
}
