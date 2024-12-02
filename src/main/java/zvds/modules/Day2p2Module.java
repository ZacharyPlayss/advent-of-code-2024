package zvds.modules;

import zvds.Day2p2Main;

public class Day2p2Module implements Module {
    @Override
    public void launch() {
        Day2p2Main.main(new String[]{}); // Adjust as needed
    }

    @Override
    public String getTitle() {
        return "Day 2: Red-Nosed Reports (part 2)";
    }

    @Override
    public String getName() {
        return "Day2p2";
    }
}
