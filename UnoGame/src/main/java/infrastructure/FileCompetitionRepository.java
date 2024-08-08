package infrastructure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.entities.Competition;
import domain.repositories.CompetitionRepository;

public class FileCompetitionRepository implements CompetitionRepository {
    private final ObjectMapper objectMapper;
    private final String filePath;

    public FileCompetitionRepository(ObjectMapper objectMapper, String filePath) {
        this.objectMapper = objectMapper;
        this.filePath = filePath;
    }

    @Override
    public boolean checkIfNameAlreadyExistsCompetition(String name) throws IOException {
        List<Competition> competitions = readAllCompetitions();
        for (Competition competition : competitions) {
            if (competition.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveCompetition(Competition competition) throws IOException {
        List<Competition> competitions = readAllCompetitions();
        competitions.add(competition);
        objectMapper.writeValue(new File(filePath), competitions);
    }

    @Override
    public List<Competition> readAllCompetitions() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Competition>>() {
        });
    }

    @Override
    public Competition loadCompetition(String competitionName) throws IOException {
        List<Competition> competitions = readAllCompetitions();
        for (Competition competition : competitions) {
            if (competition.getName().equals(competitionName)) {
                return competition;
            }
        }
        return null;
    }

    @Override
    public void updateCompetition(Competition updatedCompetition) throws IOException {
        List<Competition> competitions = readAllCompetitions();
        for (int i = 0; i < competitions.size(); i++) {
            if (competitions.get(i).getName().equals(updatedCompetition.getName())) {
                competitions.set(i, updatedCompetition);
                break;
            }
        }
        objectMapper.writeValue(new File(filePath), competitions);
    }

}
