package zvds.modules;

import zvds.Day6p1Main;

public class Day6p1Module implements Module {
    @Override
    public void launch() {
        Day6p1Main.main(new String[]{}); // Adjust as needed
    }

    @Override
    public String getTitle() {
        return "Day 6: Guard Gallivant (part 1)";
    }

    @Override
    public String getName() {
        return "Day6p1";
    }
}
