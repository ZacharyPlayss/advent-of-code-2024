package zvds.modules;

import zvds.Day3p1Main;
import zvds.Day3p2Main;

public class Day3p2Module implements Module {
    @Override
    public void launch() {
        Day3p2Main.main(new String[]{}); // Adjust as needed
    }

    @Override
    public String getTitle() {
        return "Day 3: Mull It Over (part 2)";
    }

    @Override
    public String getName() {
        return "Day3p2";
    }
}
