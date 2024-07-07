package domain.service;

public class MatchRules {
    private MatchStrategy strategy;

    public MatchRules(MatchStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(MatchStrategy strategy) {
        this.strategy = strategy;
    }
}