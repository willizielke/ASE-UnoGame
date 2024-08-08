package infrastructure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.repositories.MatchStrategyRepository;
import domain.service.MatchStrategy;

public class FileMatchStrategyRepository implements MatchStrategyRepository {
    private final ObjectMapper objectMapper;
    private final String filePath;

    public FileMatchStrategyRepository(ObjectMapper objectMapper, String filePath) {
        this.objectMapper = objectMapper;
        this.filePath = filePath;
    }

    @Override
    public void saveMatchStrategy(MatchStrategy matchStrategy) throws IOException {
        List<MatchStrategy> matchStrategies = readAllMatchStrategies();
        matchStrategies.add(matchStrategy);
        objectMapper.writeValue(new File(filePath), matchStrategies);
    }

    @Override
    public List<MatchStrategy> readAllMatchStrategies() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<MatchStrategy>>() {
        });
    }

    @Override
    public void updateMatchStrategy(int id, MatchStrategy matchStrategy) throws IOException {
        List<MatchStrategy> matchStrategies = readAllMatchStrategies();
        matchStrategies.set(id, matchStrategy);

        objectMapper.writeValue(new File(filePath), matchStrategies);
    }

}
