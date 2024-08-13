package domain.entities;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import domain.valueobjects.Card;
import domain.valueobjects.Plus4Card;
import domain.valueobjects.WishCard;

import java.util.ArrayList;
import java.util.List;

public class DeckTest {

    private Deck deck;
    private Card card1;
    private Card card2;

    @Before
    public void setUp() {
        card1 = new WishCard();
        card2 = new Plus4Card();
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        deck = new Deck(cards);
    }

    @Test
    public void testSetCards() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(card1);
        deck.setCards(newCards);
        assertEquals(1, deck.getCards().size());
        assertTrue(deck.getCards().contains(card1));
    }

    @Test
    public void testGetCards() {
        List<Card> cards = deck.getCards();
        assertEquals(2, cards.size());
        assertTrue(cards.contains(card1));
        assertTrue(cards.contains(card2));
    }

    @Test
    public void testRemove() {
        Card removedCard = deck.remove(0);
        assertEquals(card1, removedCard);
        assertEquals(1, deck.getCards().size());
        assertFalse(deck.getCards().contains(card1));
    }
}