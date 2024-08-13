package application.usecase;

import org.junit.Test;
import static org.junit.Assert.*;
import domain.entities.Player;
import domain.repositories.PlayerRepositoryFake;
import presentation.InputHandlerFake;

public class MatchCreationManagerTest {
    @Test
    public void testCreatePlayer() throws Exception {
        // Arrange
        PlayerRepositoryFake playerRepositoryFake = new PlayerRepositoryFake();
        InputHandlerFake inputHandlerFake = new InputHandlerFake();
        MatchCreationManager manager = new MatchCreationManager(playerRepositoryFake, inputHandlerFake);

        inputHandlerFake.setName("TestPlayer");

        // Act
        Player player = manager.createPlayer();

        // Assert
        assertNotNull(player);
        assertEquals(1, playerRepositoryFake.readAllPlayers().size());
        assertEquals("TestPlayer", player.getPlayerName());
    }
}
