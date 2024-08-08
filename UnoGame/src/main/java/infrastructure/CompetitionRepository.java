
package infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.entities.Competition;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompetitionRepository {
    private final ObjectMapper objectMapper;
    private final String filePathCompetition;

    public CompetitionRepository(ObjectMapper objectMapper, String filePathCompetition) {
        this.objectMapper = objectMapper;
        this.filePathCompetition = filePathCompetition;
    }

    public boolean checkIfNameAlreadyExists(String name) throws IOException {
        List<Competition> competitions = readAllCompetitions();
        for (Competition competition : competitions) {
            if (competition.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void saveCompetition(Competition competition) throws IOException {
        List<Competition> competitions = readAllCompetitions();
        competitions.add(competition);
        objectMapper.writeValue(new File(filePathCompetition), competitions);
    }

    public List<Competition> readAllCompetitions() throws IOException {
        File file = new File(filePathCompetition);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Competition>>() {
        });
    }

    public Competition loadCompetition(String competitionName) throws IOException {
        List<Competition> competitions = readAllCompetitions();
        for (Competition competition : competitions) {
            if (competition.getName().equals(competitionName)) {
                return competition;
            }
        }
        return null;
    }

    public void updateCompetition(Competition updatedCompetition) throws IOException {
        List<Competition> competitions = readAllCompetitions();
        for (int i = 0; i < competitions.size(); i++) {
            if (competitions.get(i).getName().equals(updatedCompetition.getName())) {
                competitions.set(i, updatedCompetition);
                break;
            }
        }
        objectMapper.writeValue(new File(filePathCompetition), competitions);
    }
}