package domain.entities;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private Player player;
    private PlayerHistoryData playerHistoryData;

    @Before
    public void setUp() {
        playerHistoryData = new PlayerHistoryData();
        player = new Player("John Doe", 1);
    }

    @Test
    public void testPlayerName() {
        assertEquals("John Doe", player.getPlayerName());
    }

    @Test
    public void testPlayerId() {
        assertEquals(1, player.getId());
    }

    @Test
    public void testPlayerStatsInitialization() {
        assertNotNull(player.getPlayerStats());
    }

    @Test
    public void testSetPlayerStats() {
        player.setPlayerStats(playerHistoryData);
        assertEquals(playerHistoryData, player.getPlayerStats());
    }

    @Test
    public void testDefaultConstructor() {
        Player defaultPlayer = new Player();
        assertNull(defaultPlayer.getPlayerName());
        assertEquals(0, defaultPlayer.getId()); // Assuming default id is 0
        assertNotNull(defaultPlayer.getPlayerStats());
    }
}