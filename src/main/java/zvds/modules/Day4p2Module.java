package zvds.modules;

import zvds.Day4p2Main;

public class Day4p2Module implements Module {
    @Override
    public void launch() {
        Day4p2Main.main(new String[]{}); // Adjust as needed
    }

    @Override
    public String getTitle() {
        return "Day 4: Mull It Over (part 2)";
    }

    @Override
    public String getName() {
        return "Day4p2";
    }
}
