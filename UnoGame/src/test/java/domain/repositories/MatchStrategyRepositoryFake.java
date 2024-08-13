package domain.repositories;

import java.util.ArrayList;
import java.util.List;
import domain.service.MatchStrategy;

public class MatchStrategyRepositoryFake implements MatchStrategyRepository {
    private List<MatchStrategy> matchStrategies = new ArrayList<>();

    @Override
    public void saveMatchStrategy(MatchStrategy matchStrategy) {
        matchStrategies.add(matchStrategy);
    }

    @Override
    public List<MatchStrategy> readAllMatchStrategies() {
        return new ArrayList<>(matchStrategies);
    }

    @Override
    public void updateMatchStrategy(int id, MatchStrategy updatedMatchStrategy) {
    }
}