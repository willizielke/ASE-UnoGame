package domain.entities;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import domain.service.MatchRules;
import domain.valueobjects.Card;
import domain.valueobjects.WishCard;

import java.util.ArrayList;
import java.util.List;

public class MatchTest {

    private Match match;
    private PlayerWithCards player1;
    private PlayerWithCards player2;
    private Deck deck;
    private MatchRules matchRules;
    private Card card;

    @Before
    public void setUp() {
        player1 = new PlayerWithCards();
        player2 = new PlayerWithCards();
        List<PlayerWithCards> playersWithCardsList = new ArrayList<>();
        playersWithCardsList.add(player1);
        playersWithCardsList.add(player2);
        deck = new Deck();
        matchRules = new MatchRules();
        match = new Match(playersWithCardsList, deck, matchRules);
        card = new WishCard();
    }

    @Test
    public void testSetPlayersWithCardsList() {
        List<PlayerWithCards> newPlayersWithCardsList = new ArrayList<>();
        newPlayersWithCardsList.add(player1);
        match.setPlayersWithCardsList(newPlayersWithCardsList);
        assertEquals(1, match.getPlayersWithCardsList().size());
        assertTrue(match.getPlayersWithCardsList().contains(player1));
    }

    @Test
    public void testSetWinnerId() {
        match.setWinnerId(1);
        assertEquals(1, match.getWinnerId());
    }

    @Test
    public void testSetMatchRules() {
        MatchRules newMatchRules = new MatchRules();
        match.setMatchRules(newMatchRules);
        assertEquals(newMatchRules, match.getMatchRules());
    }

    @Test
    public void testSetDeck() {
        Deck newDeck = new Deck();
        match.setDeck(newDeck);
        assertEquals(newDeck, match.getDeck());
    }

    @Test
    public void testSetPlayedCards() {
        List<Card> newPlayedCards = new ArrayList<>();
        newPlayedCards.add(card);
        match.setPlayedCards(newPlayedCards);
        assertEquals(1, match.getPlayedCards().size());
        assertTrue(match.getPlayedCards().contains(card));
    }

    @Test
    public void testAddCard() {
        match.addCard(card);
        assertEquals(1, match.getPlayedCards().size());
        assertTrue(match.getPlayedCards().contains(card));
    }
}