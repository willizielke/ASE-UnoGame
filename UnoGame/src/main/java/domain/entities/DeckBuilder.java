package domain.entities;

import java.util.ArrayList;
import java.util.List;

import domain.valueobjects.Card;
import domain.valueobjects.NumberCard;
import domain.valueobjects.Plus4Card;
import domain.valueobjects.SpecialCard;
import domain.valueobjects.WishCard;

public class DeckBuilder {
    private List<Card> cards;

    public DeckBuilder() {
        this.cards = new ArrayList<>();
    }

    public DeckBuilder(List<Card> cards) {
        this.cards = cards;
    }

    public DeckBuilder addNumberCard(CardColor color, int number) {
        cards.add(new NumberCard(color, number));
        return this;
    }

    public DeckBuilder addSpecialCard(CardColor color, CardNames symbol) {
        cards.add(new SpecialCard(color, symbol));
        return this;
    }

    public DeckBuilder addWishCard() {
        cards.add(new WishCard());
        return this;
    }

    public DeckBuilder addPlus4Card() {
        cards.add(new Plus4Card());
        return this;
    }

    public Deck build() {
        return new Deck(cards);
    }

    public static Deck createDeck() {
        DeckBuilder deckBuilder = new DeckBuilder();
        for (CardColor color : CardColor.values()) {
            if(color == CardColor.BLACK) {
                continue;
            }
            for (int number = 1; number <= 9; number++) {
                deckBuilder.addNumberCard(color, number);
                deckBuilder.addNumberCard(color, number);
            }
            deckBuilder.addSpecialCard(color, CardNames.ZERO)
                    .addSpecialCard(color, CardNames.ZERO)
                    .addSpecialCard(color, CardNames.SKIP)
                    .addSpecialCard(color, CardNames.SKIP)
                    .addSpecialCard(color, CardNames.REVERSE)
                    .addSpecialCard(color, CardNames.REVERSE)
                    .addSpecialCard(color, CardNames.PLUS2)
                    .addSpecialCard(color, CardNames.PLUS2);
        }
        deckBuilder.addWishCard()
                .addWishCard()
                .addWishCard()
                .addWishCard()
                .addPlus4Card()
                .addPlus4Card()
                .addPlus4Card()
                .addPlus4Card();

        Deck deck = deckBuilder.build();
        return shuffle(deck);
    }

    public static Deck shuffle(Deck deck) {
        List<Card> cards = deck.getCards();
        List<Card> shuffledCards = new ArrayList<>();
        while (!cards.isEmpty()) {
            int randomIndex = (int) (Math.random() * cards.size());
            shuffledCards.add(cards.get(randomIndex));
            cards.remove(randomIndex);
        }
        return new Deck(shuffledCards);
    }
}
