package domain.repositories;

import java.io.IOException;
import java.util.List;

import domain.entities.Competition;

public interface CompetitionRepository {
    void saveCompetition(Competition competition) throws IOException;

    List<Competition> readAllCompetitions() throws IOException;

    void updateCompetition(Competition updatedCompetition) throws IOException;

    boolean checkIfNameAlreadyExistsCompetition(String name) throws IOException;

    Competition loadCompetition(String competitionName) throws IOException;
}
