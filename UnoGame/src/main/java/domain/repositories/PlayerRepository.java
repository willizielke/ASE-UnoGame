package domain.repositories;

import java.util.List;

import domain.entities.Player;

public interface PlayerRepository {
    boolean checkIfNameAlreadyExistsPlayer(String name) throws Exception;

    void savePlayer(Player player) throws Exception;

    List<Player> readAllPlayers() throws Exception;

    void updatePlayer(Player updatedPlayer) throws Exception;

    Player loadPlayer(int id) throws Exception;
}
