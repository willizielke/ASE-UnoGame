package domain.repositories;

import java.util.ArrayList;
import java.util.List;
import domain.entities.Player;

public class PlayerRepositoryFake implements PlayerRepository {
    private List<Player> players = new ArrayList<>();

    @Override
    public List<Player> readAllPlayers() {
        return new ArrayList<>(players);
    }

    @Override
    public void savePlayer(Player player) {
        players.add(player);
    }

    @Override
    public boolean checkIfNameAlreadyExistsPlayer(String playerName) {
        return players.stream().anyMatch(player -> player.getPlayerName().equals(playerName));
    }

    @Override
    public Player loadPlayer(int id) {
        return players.stream().filter(player -> player.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void updatePlayer(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == player.getId()) {
                players.set(i, player);
                return;
            }
        }
    }
}


