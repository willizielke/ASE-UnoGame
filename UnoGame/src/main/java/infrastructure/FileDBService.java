package infrastructure;

import application.interfaces.IDataPersistence;
import domain.entities.Competition;
import domain.entities.Player;
import domain.service.MatchStrategy;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileDBService implements IDataPersistence {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath = "C:\\Users\\wzielke\\Desktop\\Ase-UnoGame\\UnoGame\\src\\file_db\\";
    private final String filePathPlayer = filePath + "players.json";
    private final String filePathCompetition = filePath + "competitions.json";
    private final String filePathMatchStrategy = filePath + "matchStrategies.json";
    private final PlayerRepository playerRepository = new PlayerRepository(objectMapper, filePathPlayer);
    private final CompetitionRepository competitionRepository = new CompetitionRepository(objectMapper,
            filePathCompetition);
    private final MatchStrategyRepository matchStrategyRepository = new MatchStrategyRepository(objectMapper,
            filePathMatchStrategy);

    @Override
    public boolean checkIfNameAlreadyExistsPlayer(String name) throws IOException {
        return playerRepository.checkIfNameAlreadyExists(name);
    }

    @Override
    public void savePlayer(String playerName) throws IOException {
        Player player = new Player(playerName, playerRepository.getPlayerId());
        playerRepository.savePlayer(player);
    }

    @Override
    public List<Player> readAllPlayers() throws IOException {
        return playerRepository.readAllPlayers();
    }

    @Override
    public void updatePlayer(Player updatedPlayer) throws IOException {
        playerRepository.updatePlayer(updatedPlayer);
    }

    @Override
    public Player loadPlayer(int id) throws IOException {
        return playerRepository.loadPlayer(id);
    }

    @Override
    public boolean checkIfNameAlreadyExistsCompetition(String name) throws IOException {
        return competitionRepository.checkIfNameAlreadyExists(name);
    }

    @Override
    public void saveCompetition(Competition competition) throws IOException {
        competitionRepository.saveCompetition(competition);
    }

    @Override
    public List<Competition> readAllCompetitions() throws IOException {
        return competitionRepository.readAllCompetitions();
    }

    @Override
    public Competition loadCompetition(String competitionName) throws IOException {
        return competitionRepository.loadCompetition(competitionName);
    }

    @Override
    public void updateCompetition(Competition updatedCompetition) throws IOException {
        competitionRepository.updateCompetition(updatedCompetition);
    }

    @Override
    public void saveStrategy(MatchStrategy matchStrategy) throws IOException {
        matchStrategyRepository.saveStrategy(matchStrategy);
    }

    @Override
    public List<MatchStrategy> readAllMatchStrategies() throws IOException {
        return matchStrategyRepository.readAllMatchStrategies();
    }

    @Override
    public MatchStrategy loadMatchStrategy(int number) throws IOException {
        return matchStrategyRepository.loadMatchStrategy(number);
    }

    @Override
    public void updateMatchStrategy(MatchStrategy matchStrategy, int number) throws IOException {
        matchStrategyRepository.updateMatchStrategy(matchStrategy, number);
    }
}