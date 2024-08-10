package domain.valueobjects;

import java.util.Objects;

import domain.enums.CardColor;

public class NumberCard extends Card {
    private final int number;

    public NumberCard(CardColor color, int number) {
        super(number, color);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NumberCard)) {
            return false;
        }
        NumberCard other = (NumberCard) obj;
        return number == other.number && getColor().equals(other.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, getColor());
    }

    @Override
    public String toString() {
        return number + " " + getColor();
    }
}