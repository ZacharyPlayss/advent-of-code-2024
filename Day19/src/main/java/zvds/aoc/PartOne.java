package zvds.aoc;

import zvds.domain.TowelDesign;
import zvds.manager.TowelDesignManager;
import zvds.manager.TowelPatternManager;
import zvds.reader.TxtReader;

import java.io.File;
import java.util.List;

public class PartOne implements PartInterface{
    @Override
    public void run(File input) {
        List<List<String>> inputLists = TxtReader.readFileToPatternLists(input);
        TowelPatternManager patternManager = new TowelPatternManager();
        TowelDesignManager designManager = new TowelDesignManager(patternManager);

        patternManager.addTowelPatterns(inputLists.get(0));
        designManager.addTowelDesigns(inputLists.get(1));

        List<TowelDesign> validDesigns = designManager.getValidDesigns();
        System.out.println("There are: " + validDesigns.size() + " valid designs.");
    }
}
