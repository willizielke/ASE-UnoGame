package domain.entities;

import java.util.List;

import domain.valueobjects.Card;

public class Deck {
    private List<Card> cards;

    public Deck() {
    }

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Card remove(int index) {
        return cards.remove(index);
    }
}
