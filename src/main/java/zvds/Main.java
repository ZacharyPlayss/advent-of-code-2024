package zvds;

import zvds.manager.AsciiArtManager;
import zvds.manager.ModuleManager;
import zvds.modules.*;
import zvds.modules.Module;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Module> modules = Arrays.asList(
                new Day1p1Module(),
                new Day1p2Module(),
                new Day2p1Module(),
                new Day2p2Module(),
                new Day3p1Module(),
                new Day3p2Module(),
                new Day4p1Module(),
                new Day4p2Module()
        );
        AsciiArtManager asciiArtManager = new AsciiArtManager();
        ModuleManager moduleManager = new ModuleManager(modules, asciiArtManager);
        moduleManager.start();
    }
}
