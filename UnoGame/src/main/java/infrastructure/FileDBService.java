package infrastructure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.interfaces.IDataPersistence;
import domain.entities.Competition;
import domain.entities.Player;
import domain.service.MatchStrategy;

public class FileDBService implements IDataPersistence {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath = "C:\\Users\\wzielke\\Desktop\\Ase-UnoGame\\UnoGame\\src\\file_db\\";
    private final String filePathPlayer = filePath + "players.json";
    private final String filePathCompetition = filePath + "competitions.json";
    private final String filePathMatchStrategy = filePath + "matchStrategies.json";

    @Override
    public boolean checkIfNameAlreadyExistsPlayer(String name) throws IOException {
        List<Player> players = readAllPlayers();
        for (Player player : players) {
            if (player.getPlayerName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void savePlayer(String playerName) throws IOException {
        Player player = new Player(playerName, getPlayerId());
        List<Player> players = readAllPlayers();
        players.add(player);
        objectMapper.writeValue(new File(filePathPlayer), players);
    }

    @Override
    public Player saveAndReturnPlayer(String playerName) throws IOException {
        Player player = new Player(playerName, getPlayerId());
        List<Player> players = readAllPlayers();
        players.add(player);
        objectMapper.writeValue(new File(filePathPlayer), players);
        return player;
    }

    public int getPlayerId() throws IOException {
        return readAllPlayers().size();
    }

    @Override
    public List<Player> readAllPlayers() throws IOException {
        File file = new File(filePathPlayer);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Player>>() {
        });
    }

    @Override
    public void updatePlayer(Player updatedPlayer) throws IOException {
        List<Player> players = readAllPlayers();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == updatedPlayer.getId()) {
                players.set(i, updatedPlayer);
                break;
            }
        }
        objectMapper.writeValue(new File(filePathPlayer), players);
    }

    @Override
    public Player loadPlayer(int id) throws IOException {
        List<Player> players = readAllPlayers();
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
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
        objectMapper.writeValue(new File(filePathCompetition), competitions);
    }

    @Override
    public List<Competition> readAllCompetitions() throws IOException {
        File file = new File(filePathCompetition);
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
        objectMapper.writeValue(new File(filePathCompetition), competitions);
    }

    @Override
    public void saveStrategy(MatchStrategy matchStrategy) throws IOException {
        List<MatchStrategy> matchStrategies = readAllMatchStrategies();
        matchStrategies.add(matchStrategy);
        objectMapper.writeValue(new File(filePathMatchStrategy), matchStrategies);
    }

    @Override
    public List<MatchStrategy> readAllMatchStrategies() throws IOException {
        File file = new File(filePathMatchStrategy);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<MatchStrategy>>() {
        });
    }

    @Override
    public MatchStrategy loadMatchStrategy(int number) throws IOException {
        List<MatchStrategy> matchStrategies = readAllMatchStrategies();
        if (number >= 0 && number < matchStrategies.size()) {
            return matchStrategies.get(number);
        } else {
            throw new IllegalArgumentException("Invalid number");
        }
    }

    @Override
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
