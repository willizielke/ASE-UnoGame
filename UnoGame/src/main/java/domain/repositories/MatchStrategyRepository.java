package domain.repositories;

import java.util.List;

import domain.service.MatchStrategy;

public interface MatchStrategyRepository {
    void saveMatchStrategy(MatchStrategy matchStrategy) throws Exception;

    List<MatchStrategy> readAllMatchStrategies() throws Exception;

    void updateMatchStrategy(int id, MatchStrategy updatedMatchStrategy) throws Exception;
}
