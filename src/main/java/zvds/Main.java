package zvds;

import zvds.manager.AsciiArtManager;
import zvds.manager.ModuleManager;
import zvds.modules.Day1p1Module;
import zvds.modules.Day1p2Module;
import zvds.modules.Day2p1Module;
import zvds.modules.Day2p2Module;
import zvds.modules.Module;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Module> modules = Arrays.asList(
                new Day1p1Module(),
                new Day1p2Module(),
                new Day2p1Module(),
                new Day2p2Module()
        );
        AsciiArtManager asciiArtManager = new AsciiArtManager();
        ModuleManager moduleManager = new ModuleManager(modules, asciiArtManager);
        moduleManager.start();
    }
}
