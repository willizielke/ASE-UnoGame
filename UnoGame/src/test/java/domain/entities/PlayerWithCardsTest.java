package domain.entities;

import domain.valueobjects.Card;
import domain.valueobjects.CardFake;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class PlayerWithCardsTest {

    private PlayerWithCards playerWithCards;

    @Before
    public void setUp() {
        List<Card> cards = new ArrayList<>();
        Player player = new Player();
        playerWithCards = new PlayerWithCards(player, cards);
    }

    @Test
    public void testAddCard() {
        CardFake card = new CardFake(10);
        playerWithCards.addCard(card);

        assertEquals(10, playerWithCards.getTotalCardPoints());
    }

    @Test
    public void testRemoveCard() {
        CardFake card1 = new CardFake(10);
        CardFake card2 = new CardFake(5);
        playerWithCards.addCard(card1);
        playerWithCards.addCard(card2);

        playerWithCards.removeCard(0);

        assertEquals(5, playerWithCards.getTotalCardPoints());
    }
}



