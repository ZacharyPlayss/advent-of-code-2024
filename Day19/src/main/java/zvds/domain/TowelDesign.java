package zvds.domain;

import java.util.ArrayList;
import java.util.List;

public class TowelDesign {
    private String desiredDesign;
    private List<TowelPattern> requiredPatterns;
    private boolean isPossible;
    private long patternCombinationCount;

    public TowelDesign(String desiredDesign) {
        this.desiredDesign = desiredDesign;
        this.requiredPatterns = new ArrayList<TowelPattern>();
        this.isPossible = false;
    }

    public void setPossible(boolean possible) {
        isPossible = possible;
    }

    public boolean isPossible() {
        return isPossible;
    }

    public void addRequiredPattern(TowelPattern pattern) {
        this.requiredPatterns.add(pattern);
    }

    public String getDesiredDesign() {
        return desiredDesign;
    }

    public long getPatternCombinationCount() {
        return patternCombinationCount;
    }

    public void setPatternCombinationCount(long patternCombinationCount) {
        this.patternCombinationCount = patternCombinationCount;
    }
}
