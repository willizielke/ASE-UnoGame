package domain.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.enums.CardColor;
import domain.enums.CardName;
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

    public DeckBuilder addNumberCard(CardColor color, int number) {
        cards.add(new NumberCard(color, number));
        return this;
    }

    public DeckBuilder addSpecialCard(CardColor color, CardName symbol) {
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
            if (color == CardColor.BLACK) {
                continue;
            }
            for (int number = 1; number <= 9; number++) {
                deckBuilder.addNumberCard(color, number);
                deckBuilder.addNumberCard(color, number);
            }
            deckBuilder.addSpecialCard(color, CardName.ZERO)
                    .addSpecialCard(color, CardName.ZERO)
                    .addSpecialCard(color, CardName.SKIP)
                    .addSpecialCard(color, CardName.SKIP)
                    .addSpecialCard(color, CardName.REVERSE)
                    .addSpecialCard(color, CardName.REVERSE)
                    .addSpecialCard(color, CardName.PLUS2)
                    .addSpecialCard(color, CardName.PLUS2);
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
        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
