package infrastructure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.service.MatchStrategy;

public class MatchStrategyRepository {
    private final ObjectMapper objectMapper;
    private final String filePathMatchStrategy;

    public MatchStrategyRepository(ObjectMapper objectMapper, String filePathMatchStrategy) {
        this.objectMapper = objectMapper;
        this.filePathMatchStrategy = filePathMatchStrategy;
    }

    public void saveStrategy(MatchStrategy matchStrategy) throws IOException {
        List<MatchStrategy> matchStrategies = readAllMatchStrategies();
        matchStrategies.add(matchStrategy);
        objectMapper.writeValue(new File(filePathMatchStrategy), matchStrategies);
    }

    public List<MatchStrategy> readAllMatchStrategies() throws IOException {
        File file = new File(filePathMatchStrategy);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<MatchStrategy>>() {});
    }

    public MatchStrategy loadMatchStrategy(int number) throws IOException {
        List<MatchStrategy> matchStrategies = readAllMatchStrategies();
        if (number >= 0 && number < matchStrategies.size()) {
            return matchStrategies.get(number);
        } else {
            throw new IllegalArgumentException("Invalid number");
        }
    }

    public void updateMatchStrategy(MatchStrategy matchStrategy, int number) throws IOException {
        List<MatchStrategy> matchStrategies = readAllMatchStrategies();
        if (number >= 0 && number < matchStrategies.size()) {
            matchStrategies.set(number, matchStrategy);
            objectMapper.writeValue(new File(filePathMatchStrategy), matchStrategies);
        } else {
            throw new IllegalArgumentException("Invalid number");
        }
    }
}
