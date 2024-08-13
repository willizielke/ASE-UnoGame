package domain.entities;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import domain.service.MatchRules;
import domain.service.OriginalMatchStrategy;
import domain.valueobjects.Card;

import java.util.List;

public class FastMatchTest {

    private FastMatch fastMatch;
    private final int playerCount = 4;

    @Before
    public void setUp() {
        fastMatch = new FastMatch(playerCount);
    }

    @Test
    public void testPlayerCount() {
        List<PlayerWithCards> playersWithCardsList = fastMatch.getPlayersWithCardsList();
        assertEquals(playerCount, playersWithCardsList.size());
    }

    @Test
    public void testInitialCardDistribution() {
        List<PlayerWithCards> playersWithCardsList = fastMatch.getPlayersWithCardsList();
        for (PlayerWithCards playerWithCards : playersWithCardsList) {
            assertEquals(7, playerWithCards.getPlayerCards().size());
        }
    }

    @Test
    public void testDeckAfterDistribution() {
        Deck deck = fastMatch.getDeck();
        int expectedRemainingCards = 108 - (playerCount * 7);
        assertEquals(expectedRemainingCards, deck.getCards().size());
    }

    @Test
    public void testMatchRules() {
        MatchRules matchRules = fastMatch.getMatchRules();
        assertNotNull(matchRules);
        assertTrue(matchRules.getStrategy() instanceof OriginalMatchStrategy);
    }

    @Test
    public void testPlayedCardsInitialization() {
        List<Card> playedCards = fastMatch.getPlayedCards();
        assertNotNull(playedCards);
        assertEquals(0, playedCards.size());
    }
}