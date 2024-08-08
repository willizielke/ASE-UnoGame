package infrastructure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.entities.Player;
import domain.repositories.PlayerRepository;

public class FilePlayerRepository implements PlayerRepository {
    private final ObjectMapper objectMapper;
    private final String filePath;

    public FilePlayerRepository(ObjectMapper objectMapper, String filePath) {
        this.objectMapper = objectMapper;
        this.filePath = filePath;

    }

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
    public void savePlayer(Player player) throws IOException {
        List<Player> players = readAllPlayers();
        players.add(player);
        objectMapper.writeValue(new File(filePath), players);
    }

    @Override
    public List<Player> readAllPlayers() throws IOException {
        File file = new File(filePath);
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
        objectMapper.writeValue(new File(filePath), players);
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
}
