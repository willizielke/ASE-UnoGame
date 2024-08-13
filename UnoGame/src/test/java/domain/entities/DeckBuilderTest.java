package domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import domain.enums.CardColor;
import domain.enums.CardName;
import domain.valueobjects.Card;
import domain.valueobjects.NumberCard;
import domain.valueobjects.Plus4Card;
import domain.valueobjects.SpecialCard;
import domain.valueobjects.WishCard;

public class DeckBuilderTest {

    private DeckBuilder deckBuilder;

    @Before
    public void setUp() {
        deckBuilder = new DeckBuilder();
    }

    @Test
    public void testAddNumberCard() {
        deckBuilder.addNumberCard(CardColor.RED, 5);
        Deck deck = deckBuilder.build();
        List<Card> cards = deck.getCards();
        assertEquals(1, cards.size());
        assertTrue(cards.get(0) instanceof NumberCard);
        assertEquals(CardColor.RED.toString(), ((NumberCard) cards.get(0)).getColor());
        assertEquals(5, ((NumberCard) cards.get(0)).getNumber());
    }

    @Test
    public void testAddSpecialCard() {
        deckBuilder.addSpecialCard(CardColor.BLUE, CardName.SKIP);
        Deck deck = deckBuilder.build();
        List<Card> cards = deck.getCards();
        assertEquals(1, cards.size());
        assertTrue(cards.get(0) instanceof SpecialCard);
        assertEquals(CardColor.BLUE.toString(), ((SpecialCard) cards.get(0)).getColor());
        assertEquals(CardName.SKIP, ((SpecialCard) cards.get(0)).getSymbol());
    }

    @Test
    public void testAddWishCard() {
        deckBuilder.addWishCard();
        Deck deck = deckBuilder.build();
        List<Card> cards = deck.getCards();
        assertEquals(1, cards.size());
        assertTrue(cards.get(0) instanceof WishCard);
    }

    @Test
    public void testAddPlus4Card() {
        deckBuilder.addPlus4Card();
        Deck deck = deckBuilder.build();
        List<Card> cards = deck.getCards();
        assertEquals(1, cards.size());
        assertTrue(cards.get(0) instanceof Plus4Card);
    }

    @Test
    public void testCreateDeck() {
        Deck deck = DeckBuilder.createDeck();
        assertNotNull(deck);
        List<Card> cards = deck.getCards();
        assertEquals(108, cards.size());

        // Ensure all card types are present
        Set<Class<?>> cardTypes = new HashSet<>();
        for (Card card : cards) {
            cardTypes.add(card.getClass());
        }
        assertTrue(cardTypes.contains(NumberCard.class));
        assertTrue(cardTypes.contains(SpecialCard.class));
        assertTrue(cardTypes.contains(WishCard.class));
        assertTrue(cardTypes.contains(Plus4Card.class));
    }

    @Test
    public void testShuffle() {
        Deck deck1 = DeckBuilder.createDeck();
        Deck deck2 = DeckBuilder.createDeck();
        boolean isShuffled = false;
        for (int i = 0; i < deck1.getCards().size(); i++) {
            if (!deck1.getCards().get(i).equals(deck2.getCards().get(i))) {
                isShuffled = true;
                break;
            }
        }
        assertTrue(isShuffled);
    }
}
