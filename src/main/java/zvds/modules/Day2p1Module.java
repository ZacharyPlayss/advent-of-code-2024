package zvds.modules;

import zvds.Day2p1Main;

public class Day2p1Module implements Module {
    @Override
    public void launch() {
        Day2p1Main.main(new String[]{}); // Adjust as needed
    }

    @Override
    public String getTitle() {
        return "Day 2: Red-Nosed Reports (part 1)";
    }

    @Override
    public String getName() {
        return "Day2p1";
    }
}
