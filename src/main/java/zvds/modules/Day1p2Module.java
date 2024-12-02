package zvds.modules;

import zvds.Day1p2Main;

public class Day1p2Module implements Module {
    @Override
    public void launch() {
        Day1p2Main.main(new String[]{}); // Adjust as needed
    }

    @Override
    public String getTitle() {
        return "Day 1: Historian Hysteria (part 2)";
    }

    @Override
    public String getName() {
        return "Day1p2";
    }
}
