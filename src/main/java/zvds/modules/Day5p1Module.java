package zvds.modules;

import zvds.Day5p1Main;

public class Day5p1Module implements Module {
    @Override
    public void launch() {
        Day5p1Main.main(new String[]{}); // Adjust as needed
    }

    @Override
    public String getTitle() {
        return "Day 5: Print Queue (part 1)";
    }

    @Override
    public String getName() {
        return "Day5p1";
    }
}
