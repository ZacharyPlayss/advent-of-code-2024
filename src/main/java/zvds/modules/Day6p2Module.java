package zvds.modules;

import zvds.Day6p2Main;

import java.io.IOException;

public class Day6p2Module implements Module {
    @Override
    public void launch() {
        try {
            Day6p2Main.main(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getTitle() {
        return "Day 6: Guard Gallivant (part 2)";
    }

    @Override
    public String getName() {
        return "Day6p2";
    }
}
