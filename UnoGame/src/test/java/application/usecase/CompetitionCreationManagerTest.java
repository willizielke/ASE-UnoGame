package application.usecase;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

import domain.entities.Competition;
import domain.entities.Player;
import domain.repositories.PlayerRepositoryFake;
import domain.service.CompetitionRules;
import domain.service.MatchRules;
import domain.repositories.CompetitionRepositoryFake;
import presentation.InputHandlerFake;

public class CompetitionCreationManagerTest {

    private CompetitionCreationManager competitionCreationManager;
    private PlayerRepositoryFake playerRepositoryFake;
    private CompetitionRepositoryFake competitionRepositoryFake;
    private InputHandlerFake inputHandlerFake;

    @Before
    public void setUp() {
        playerRepositoryFake = new PlayerRepositoryFake();
        competitionRepositoryFake = new CompetitionRepositoryFake();
        inputHandlerFake = new InputHandlerFake();
        competitionCreationManager = new CompetitionCreationManager(playerRepositoryFake, competitionRepositoryFake, inputHandlerFake);
    }

    @Test
    public void testCreatePlayer() throws Exception {
        inputHandlerFake.setName("TestPlayer");
        Player player = competitionCreationManager.createPlayer();
        assertNotNull(player);
        assertEquals("TestPlayer", player.getPlayerName());
        assertTrue(playerRepositoryFake.checkIfNameAlreadyExistsPlayer("TestPlayer"));
    }

    @Test
    public void testLoadPlayer() throws Exception {
        Player player = new Player("TestPlayer", 0);
        playerRepositoryFake.savePlayer(player);
        inputHandlerFake.setName("TestPlayer");
        Player loadedPlayer = competitionCreationManager.loadPlayer();
        assertNotNull(loadedPlayer);
        assertEquals("TestPlayer", loadedPlayer.getPlayerName());
    }

    @Test
    public void testCreateOrLoadPlayer() throws Exception {
        inputHandlerFake.setName("TestPlayer");
        Player player = competitionCreationManager.createOrLoadPlayer();
        assertNotNull(player);
        assertEquals("TestPlayer", player.getPlayerName());
    }

    @Test
    public void testPlayerIsAlreadyInTheCompetition() {
        Player player1 = new Player("Player1", 1);
        Player player2 = new Player("Player2", 2);
        List<Player> players = new ArrayList<>();
        players.add(player1);

        assertTrue(competitionCreationManager.playerIsAlreadyInTheCompetition(player1, players));
        assertFalse(competitionCreationManager.playerIsAlreadyInTheCompetition(player2, players));
    }

    @Test
    public void testGetMatchRules() {
        inputHandlerFake.setName("TestPlayer");
        MatchRules matchRules = competitionCreationManager.getMatchRules();
        assertNotNull(matchRules);
    }

    @Test
    public void testGetCompetitionRules() {
        inputHandlerFake.setName("TestPlayer");
        CompetitionRules competitionRules = competitionCreationManager.getCompetitionRules();
        assertNotNull(competitionRules);
    }
}
