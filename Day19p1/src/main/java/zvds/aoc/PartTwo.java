package zvds.aoc;

import zvds.domain.TowelDesign;
import zvds.manager.TowelDesignManager;
import zvds.manager.TowelPatternManager;
import zvds.reader.TxtReader;

import java.io.File;
import java.util.List;

public class PartTwo implements PartInterface{
    @Override
    public void run(File input) {
        List<List<String>> inputLists = TxtReader.readFileToPatternLists(input);
        TowelPatternManager patternManager = new TowelPatternManager();
        TowelDesignManager designManager = new TowelDesignManager(patternManager);

        patternManager.addTowelPatterns(inputLists.get(0));
        designManager.addTowelDesigns(inputLists.get(1));


        for(TowelDesign design : designManager.getDesigns()) {
            designManager.findAllCreationPatterns(design);
        }

        System.out.println(designManager.getDesigns().get(0).getAllRequiredPatterns().size());
        List<TowelDesign> validDesigns = designManager.getValidDesigns();
        int totalCount = 0;
        for(int i =0; i <validDesigns.size(); i++){
            totalCount += validDesigns.get(i).getAllRequiredPatterns().size();
        }
        System.out.println("Different combination count: " + totalCount);
    }
}
