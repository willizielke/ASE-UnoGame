package domain.repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import domain.entities.Competition;

public class CompetitionRepositoryFake implements CompetitionRepository {
    private List<Competition> competitions = new ArrayList<>();

    @Override
    public void saveCompetition(Competition competition) throws IOException {
        competitions.add(competition);
    }

    @Override
    public List<Competition> readAllCompetitions() throws IOException {
        return new ArrayList<>(competitions);
    }

    @Override
    public void updateCompetition(Competition updatedCompetition) throws IOException {
        for (int i = 0; i < competitions.size(); i++) {
            if (competitions.get(i).getName().equals(updatedCompetition.getName())) {
                competitions.set(i, updatedCompetition);
                return;
            }
        }
    }

    @Override
    public boolean checkIfNameAlreadyExistsCompetition(String name) throws IOException {
        return competitions.stream().anyMatch(competition -> competition.getName().equals(name));
    }

    @Override
    public Competition loadCompetition(String competitionName) throws IOException {
        return competitions.stream().filter(competition -> competition.getName().equals(competitionName)).findFirst().orElse(null);
    }
}