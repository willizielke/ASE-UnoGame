package domain.valueobjects;

import java.util.Objects;

import domain.entities.CardColor;
import domain.entities.CardNames;

public class WishCard extends Card {
    public WishCard() {
        super(20, CardColor.BLACK);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WishCard;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoints(), getColor());
    }

    @Override
    public String toString() {
        return CardNames.WISH.toString();
    }
}