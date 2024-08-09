package domain.valueobjects;

import java.util.Objects;

import domain.entities.CardColor;
import domain.entities.CardNames;

public class Plus4Card extends Card {
    public Plus4Card() {
        super(40, CardColor.BLACK);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Plus4Card;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoints(), getColor());
    }

    @Override
    public String toString() {
        return CardNames.PLUS4.toString();
    }
}