package infrastructure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.entities.Player;

public class PlayerRepository {
    ObjectMapper objectMapper;
    String filePathPlayer;

    public PlayerRepository(ObjectMapper objectMapper, String filePathPlayer) {
        this.objectMapper = objectMapper;
        this.filePathPlayer = filePathPlayer;
    }

    public boolean checkIfNameAlreadyExists(String name) throws IOException {
        List<Player> players = readAllPlayers();
        for (Player player : players) {
            if (player.getPlayerName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void savePlayer(Player player) throws IOException {
        List<Player> players = readAllPlayers();
        players.add(player);
        objectMapper.writeValue(new File(filePathPlayer), players);
    }

    public List<Player> readAllPlayers() throws IOException {
        File file = new File(filePathPlayer);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Player>>() {
        });
    }

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

    public Player loadPlayer(int id) throws IOException {
        List<Player> players = readAllPlayers();
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    public int getPlayerId() throws IOException {
        return readAllPlayers().size();
    }

    public boolean checkIfNameAlreadyExistsPlayer(String name) throws IOException {
        List<Player> players = readAllPlayers();
        for (Player player : players) {
            if (player.getPlayerName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
