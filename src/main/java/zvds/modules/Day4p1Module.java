package zvds.modules;

import zvds.Day4p1Main;

public class Day4p1Module implements Module {
    @Override
    public void launch() {
        Day4p1Main.main(new String[]{}); // Adjust as needed
    }

    @Override
    public String getTitle() {
        return "Day 4: Mull It Over (part 1)";
    }

    @Override
    public String getName() {
        return "Day4p1";
    }
}
