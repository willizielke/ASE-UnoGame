package application.usecase;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import domain.entities.*;
import domain.enums.CardColor;
import domain.repositories.*;
import domain.service.MatchRules;
import domain.service.OriginalMatchStrategy;
import domain.valueobjects.*;
import presentation.InputHandlerFake;

public class MatchProcessManagerTest {

    private MatchProcessManager matchProcessManager;
    private PlayerRepositoryFake playerRepositoryFake;
    private InputHandlerFake inputHandlerFake;
    private Match match;
    private Player player1;
    private Player player2;
    private PlayerWithCards playerWithCards1;
    private PlayerWithCards playerWithCards2;
    private Deck deck;

    @Before
    public void setUp() {
        playerRepositoryFake = new PlayerRepositoryFake();
        inputHandlerFake = new InputHandlerFake();
        matchProcessManager = new MatchProcessManager(playerRepositoryFake, inputHandlerFake);

        player1 = new Player("Player_1", 1);
        player2 = new Player("Player_2", 2);

        List<Card> cards1 = new ArrayList<>();
        cards1.add(new NumberCard(CardColor.RED, 5));
        cards1.add(new NumberCard(CardColor.GREEN, 3));

        List<Card> cards2 = new ArrayList<>();
        cards2.add(new NumberCard(CardColor.BLUE, 7));
        cards2.add(new NumberCard(CardColor.YELLOW, 2));

        playerWithCards1 = new PlayerWithCards(player1, cards1);
        playerWithCards2 = new PlayerWithCards(player2, cards2);

        List<PlayerWithCards> playersWithCardsList = new ArrayList<>();
        playersWithCardsList.add(playerWithCards1);
        playersWithCardsList.add(playerWithCards2);

        deck = DeckBuilder.createDeck();
        match = new Match(playersWithCardsList, deck, new MatchRules(new OriginalMatchStrategy()));
    }

    @Test
    public void testWhoStarts() {
        int playerCount = 4;
        int startingPlayer = matchProcessManager.whoStarts(playerCount);
        assertTrue(startingPlayer >= 0 && startingPlayer < playerCount);
    }

    @Test
    public void testMatchIsNotOver() {
        assertTrue(matchProcessManager.matchIsNotOver(match));
    }

    @Test
    public void testCheckIfCorrectSyntax() {
        List<Card> playerCards = playerWithCards1.getPlayerCards();
        List<Integer> validNumbers = new ArrayList<>();
        validNumbers.add(1);

        assertTrue(matchProcessManager.checkIfCorrectSyntax(validNumbers, playerCards));

        List<Integer> invalidNumbers = new ArrayList<>();
        invalidNumbers.add(3);

        assertFalse(matchProcessManager.checkIfCorrectSyntax(invalidNumbers, playerCards));
    }

    @Test
    public void testDeckHasCards() {
        assertTrue(matchProcessManager.deckHasCards(deck));
        deck.getCards().clear();
        assertFalse(matchProcessManager.deckHasCards(deck));
    }
}