package domain.entities;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import domain.service.CompetitionRules;
import domain.service.MatchRules;

import java.util.ArrayList;
import java.util.List;

public class CompetitionTest {

    private Competition competition;
    private Player player1;
    private Player player2;
    private Match match;
    private MatchRules matchRules;
    private CompetitionRules competitionRules;

    @Before
    public void setUp() {
        competition = new Competition();
        player1 = new Player();
        player2 = new Player();
        match = new Match();
        matchRules = new MatchRules();
        competitionRules = new CompetitionRules();
    }

    @Test
    public void testSetName() {
        competition.setName("Championship");
        assertEquals("Championship", competition.getName());
    }

    @Test
    public void testAddPlayer() {
        competition.addPlayer(player1);
        assertEquals(1, competition.getPlayers().size());
        assertTrue(competition.getPlayers().contains(player1));
    }

    @Test
    public void testSetPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        competition.setPlayers(players);
        assertEquals(2, competition.getPlayers().size());
        assertTrue(competition.getPlayers().contains(player1));
        assertTrue(competition.getPlayers().contains(player2));
    }

    @Test
    public void testSetMatchRules() {
        competition.setMatchRules(matchRules);
        assertEquals(matchRules, competition.getMatchRules());
    }

    @Test
    public void testSetCompetitionRules() {
        competition.setCompetitionRules(competitionRules);
        assertEquals(competitionRules, competition.getCompetitionRules());
    }

    @Test
    public void testAddMatch() {
        competition.addMatch(match);
        assertEquals(1, competition.getMatches().size());
        assertTrue(competition.getMatches().contains(match));
    }

    @Test
    public void testAddPlayerCurrentCompetitionStatistic() {
        competition.addPlayerCurrentCompetitionStatistic(player1);
        assertEquals(1, competition.getPlayerCurrentCompetitionStatistics().size());
    }

    @Test
    public void testSetWinnerId() {
        competition.setWinnerId(1);
        assertEquals(1, competition.getWinnerId());
    }
}