package domain.entities;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlayerCurrentCompetitionDataTest {

    private PlayerCurrentCompetitionData playerCurrentCompetitionData;
    private Player player;

    @Before
    public void setUp() {
        player = new Player("John Doe", 1);
        playerCurrentCompetitionData = new PlayerCurrentCompetitionData(player);
    }

    @Test
    public void testPlayerInitialization() {
        assertEquals(player, playerCurrentCompetitionData.getPlayer());
    }

    @Test
    public void testMatchWinCountInitialization() {
        assertEquals(0, playerCurrentCompetitionData.getMatchWinCount());
    }

    @Test
    public void testAccumulatedPointsInitialization() {
        assertEquals(0, playerCurrentCompetitionData.getAccumulatedPoints());
    }

    @Test
    public void testTimesPointsResetedInitialization() {
        assertEquals(0, playerCurrentCompetitionData.getTimesPointsReseted());
    }

    @Test
    public void testSetPlayer() {
        Player newPlayer = new Player("Jane Doe", 2);
        playerCurrentCompetitionData.setPlayer(newPlayer);
        assertEquals(newPlayer, playerCurrentCompetitionData.getPlayer());
    }

    @Test
    public void testSetMatchWinCount() {
        playerCurrentCompetitionData.setMatchWinCount(5);
        assertEquals(5, playerCurrentCompetitionData.getMatchWinCount());
    }

    @Test
    public void testSetAccumulatedPoints() {
        playerCurrentCompetitionData.setAccumulatedPoints(100);
        assertEquals(100, playerCurrentCompetitionData.getAccumulatedPoints());
    }

    @Test
    public void testSetTimesPointsReseted() {
        playerCurrentCompetitionData.setTimesPointsReseted(3);
        assertEquals(3, playerCurrentCompetitionData.getTimesPointsReseted());
    }

    @Test
    public void testDefaultConstructor() {
        PlayerCurrentCompetitionData defaultData = new PlayerCurrentCompetitionData();
        assertNull(defaultData.getPlayer());
        assertEquals(0, defaultData.getMatchWinCount());
        assertEquals(0, defaultData.getAccumulatedPoints());
        assertEquals(0, defaultData.getTimesPointsReseted());
    }
}