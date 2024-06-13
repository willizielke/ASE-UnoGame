package application.interfaces;

import java.io.IOException;
import java.util.List;

import domain.entities.Competition;
import domain.entities.Player;
import domain.service.MatchStrategy;

public interface IDataPersistence {
    boolean checkIfNameAlreadyExistsPlayer(String name) throws IOException;
    void savePlayer(String playerName) throws IOException;
    List<Player> readAllPlayers() throws IOException;
    void updatePlayer(Player updatedPlayer) throws IOException;
    Player loadPlayer(int id) throws IOException;

    boolean checkIfNameAlreadyExistsCompetition(String name) throws IOException;
    void saveCompetition(Competition competition) throws IOException;
    List<Competition> readAllCompetitions() throws IOException;
    Competition loadCompetition(String competitionName) throws IOException;
    void updateCompetition(Competition updatedCompetition) throws IOException;

    void saveStrategy(MatchStrategy matchStrategy) throws IOException;
    List<MatchStrategy> readAllMatchStrategies() throws IOException;
    MatchStrategy loadMatchStrategy(int number) throws IOException;
    void updateMatchStrategy(MatchStrategy matchStrategy, int number) throws IOException;

}
