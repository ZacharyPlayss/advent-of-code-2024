package zvds.modules;

import zvds.Day1p1Main;

public class Day1p1Module implements Module {
    @Override
    public void launch() {
        Day1p1Main.main(new String[]{});
    }

    @Override
    public String getTitle() {
        return "Day 1: Historian Hysteria (part 1)";
    }

    @Override
    public String getName() {
        return "Day1p1";
    }
}
