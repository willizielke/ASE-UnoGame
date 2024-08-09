package domain.valueobjects;

import java.util.Objects;

import domain.entities.CardColor;
import domain.entities.CardNames;

public class SpecialCard extends Card {
    private final CardNames symbol;

    public SpecialCard(CardColor color, CardNames symbol) {
        super(10, color);
        this.symbol = symbol;
    }

    public CardNames getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SpecialCard)) {
            return false;
        }
        SpecialCard other = (SpecialCard) obj;
        return symbol.equals(other.symbol) && getColor().equals(other.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, getColor());
    }

    @Override
    public String toString() {
        return symbol + " " + getColor();
    }
}