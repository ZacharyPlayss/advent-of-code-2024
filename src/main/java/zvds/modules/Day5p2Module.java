package zvds.modules;

import zvds.Day5p2Main;

public class Day5p2Module implements Module {
    @Override
    public void launch() {
        Day5p2Main.main(new String[]{}); // Adjust as needed
    }

    @Override
    public String getTitle() {
        return "Day 5: Print Queue (part 2)";
    }

    @Override
    public String getName() {
        return "Day5p2";
    }
}
