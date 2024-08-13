package domain.entities;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class PlayerHistoryDataTest {

    private PlayerHistoryData playerHistoryData;

    @Before
    public void setUp() {
        playerHistoryData = new PlayerHistoryData();
    }

    @Test
    public void testInitialValues() {
        assertEquals(0, playerHistoryData.getCompetitionCount());
        assertEquals(0, playerHistoryData.getCompetitionWinCount());
        assertEquals(0, playerHistoryData.getMatchCount());
        assertEquals(0, playerHistoryData.getMatchWinCount());
        assertEquals(0, playerHistoryData.getMatchLoseCount());
        assertEquals(0, playerHistoryData.getAccumulatedPoints());
        assertEquals(0, playerHistoryData.getPointsPerMatch(), 0.001);
    }

    @Test
    public void testSetAndGetCompetitionCount() {
        playerHistoryData.setCompetitionCount(5);
        assertEquals(5, playerHistoryData.getCompetitionCount());
    }

    @Test
    public void testSetAndGetCompetitionWinCount() {
        playerHistoryData.setCompetitionWinCount(3);
        assertEquals(3, playerHistoryData.getCompetitionWinCount());
    }

    @Test
    public void testSetAndGetMatchCount() {
        playerHistoryData.setMatchCount(10);
        assertEquals(10, playerHistoryData.getMatchCount());
    }

    @Test
    public void testSetAndGetMatchWinCount() {
        playerHistoryData.setMatchWinCount(7);
        assertEquals(7, playerHistoryData.getMatchWinCount());
    }

    @Test
    public void testSetAndGetMatchLoseCount() {
        playerHistoryData.setMatchLoseCount(3);
        assertEquals(3, playerHistoryData.getMatchLoseCount());
    }

    @Test
    public void testSetAndGetAccumulatedPoints() {
        playerHistoryData.setAccumulatedPoints(100);
        assertEquals(100, playerHistoryData.getAccumulatedPoints());
    }

    @Test
    public void testSetAndGetPointsPerMatch() {
        playerHistoryData.setPointsPerMatch(10.5);
        assertEquals(10.5, playerHistoryData.getPointsPerMatch(), 0.001);
    }
}