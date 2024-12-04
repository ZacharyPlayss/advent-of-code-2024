package zvds.modules;

import zvds.Day3p1Main;

public class Day3p1Module implements Module {
    @Override
    public void launch() {
        Day3p1Main.main(new String[]{}); // Adjust as needed
    }

    @Override
    public String getTitle() {
        return "Day 3: Mull It Over (part 1)";
    }

    @Override
    public String getName() {
        return "Day3p1";
    }
}
