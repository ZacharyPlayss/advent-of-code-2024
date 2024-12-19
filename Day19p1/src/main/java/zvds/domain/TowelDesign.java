package zvds.domain;

import java.util.ArrayList;
import java.util.List;

public class TowelDesign {
    private String desiredDesign;
    private List<TowelPattern> requiredPatterns;
    private List<List<TowelPattern>> allRequiredPatterns;
    private boolean isPossible;

    public TowelDesign(String desiredDesign) {
        this.desiredDesign = desiredDesign;
        this.requiredPatterns = new ArrayList<TowelPattern>();
        this.allRequiredPatterns = new ArrayList<>();
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

    public void addAllRequiredPatterns(List<TowelPattern> patterns) {
        this.allRequiredPatterns.add(new ArrayList<>(patterns));
    }

    public List<List<TowelPattern>> getAllRequiredPatterns() {
        return allRequiredPatterns;
    }

}
