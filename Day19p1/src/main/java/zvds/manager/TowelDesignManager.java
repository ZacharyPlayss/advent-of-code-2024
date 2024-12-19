package zvds.manager;
import zvds.domain.TowelDesign;
import zvds.domain.TowelPattern;

import java.util.*;
import java.util.stream.Collectors;

public class TowelDesignManager {
    private TowelPatternManager patternManager;
    private List<TowelDesign> designs;

    public TowelDesignManager(TowelPatternManager patternManager) {
        this.patternManager = patternManager;
        this.designs = new ArrayList<>();
    }
    public TowelDesignManager(TowelPatternManager patternManager, List<TowelDesign> designs) {
        this.patternManager = patternManager;
        this.designs = designs;

        for(int i =0; i < designs.size(); i++) {
            validateDesign(designs.get(i));
        }
    }

    public void addTowelDesigns(List<String> designs){
        for (String designString : designs) {
            if (!designString.trim().isEmpty()) {
                TowelDesign design = new TowelDesign(designString);
                validateDesign(design);
                this.designs.add(design);
            }
        }
    }

    public List<TowelDesign> getDesigns() {
        return designs;
    }
    public List<TowelDesign> getValidDesigns() {
        return this.designs.stream()
                .filter(TowelDesign::isPossible)
                .collect(Collectors.toList());
    }

    public boolean validateDesign(TowelDesign design) {
        String designString = design.getDesiredDesign().toLowerCase();
        List<String> patterns = patternManager.getTowelPatternStrings();
        patterns.sort((a, b) -> Integer.compare(b.length(), a.length()));

        List<String> usedPatterns = new ArrayList<>();
        boolean possible = isCreatable(designString, patterns, usedPatterns);

        design.setPossible(possible);
        if (possible) {
            for (String pattern : usedPatterns) {
                design.addRequiredPattern(new TowelPattern(pattern));
            }
        }

        return possible;
    }

    private boolean isCreatable(String design, List<String> patterns, List<String> usedPatterns) {
        int n = design.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        int[] prev = new int[n + 1];
        String[] patternUsed = new String[n + 1];

        for (int i = 1; i <= n; i++) {
            for (String pattern : patterns) {
                int len = pattern.length();
                if (i >= len && design.substring(i - len, i).equals(pattern) && dp[i - len]) {
                    dp[i] = true;
                    prev[i] = i - len;
                    patternUsed[i] = pattern;
                    break;
                }
            }
        }

        if (dp[n]) {
            int index = n;
            while (index > 0) {
                usedPatterns.add(0, patternUsed[index]);
                index = prev[index];
            }
            return true;
        }

        return false;
    }

    public boolean findAllCreationPatterns(TowelDesign design) {
        String designString = design.getDesiredDesign().toLowerCase();
        List<String> patterns = patternManager.getTowelPatternStrings();
        patterns.sort((a, b) -> Integer.compare(b.length(), a.length()));

        Map<String, Long> memo = new HashMap<>();
        long combinationCount = countUniqueCreationPatternsDP(designString, patterns, memo);

        boolean possible = combinationCount > 0;
        design.setPossible(possible);
        if (possible) {
            design.setPatternCombinationCount(combinationCount);
        }

        return possible;
    }

    private long countUniqueCreationPatternsDP(String design, List<String> patterns, Map<String, Long> memo) {
        if (memo.containsKey(design)) {
            return memo.get(design);
        }

        if (design.isEmpty()) {
            return 1L;
        }

        long count = 0;
        for (String pattern : patterns) {
            if (design.startsWith(pattern)) {
                String remaining = design.substring(pattern.length());
                count += countUniqueCreationPatternsDP(remaining, patterns, memo);
            }
        }

        memo.put(design, count);
        return count;
    }

}