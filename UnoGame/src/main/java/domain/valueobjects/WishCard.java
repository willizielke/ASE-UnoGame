package domain.valueobjects;

import java.util.Objects;

import domain.enums.CardColor;
import domain.enums.CardName;

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
        return CardName.WISH.toString();
    }
}