package zvds.manager;

import zvds.domain.TowelPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TowelPatternManager {
    List<TowelPattern> patterns;

    public TowelPatternManager() {
        this.patterns = new ArrayList<>();
    }

    public TowelPatternManager(List<TowelPattern> patterns) {
        this.patterns = patterns;
    }
    public void addTowelPatterns(List<String> patterns){
        for(int i = 0; i < patterns.size(); i++){
            TowelPattern pattern = new TowelPattern(patterns.get(i));
            this.patterns.add(pattern);
        }
    }

    public void addTowelPattern(TowelPattern pattern) {
        patterns.add(pattern);
    }

    public List<TowelPattern> getTowelPatterns(){
        return this.patterns;
    }
    public List<String> getTowelPatternStrings(){
        List<String> patternStrings = this.patterns.stream().map(TowelPattern::getPattern).collect(Collectors.toList());
        return patternStrings;
    }
}
