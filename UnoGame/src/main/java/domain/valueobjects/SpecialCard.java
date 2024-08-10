package domain.valueobjects;

import java.util.Objects;

import domain.enums.CardColor;
import domain.enums.CardName;

public class SpecialCard extends Card {
    private final CardName symbol;

    public SpecialCard(CardColor color, CardName symbol) {
        super(10, color);
        this.symbol = symbol;
    }

    public SpecialCard() {
        super();
        this.symbol = null;
    }

    public CardName getSymbol() {
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