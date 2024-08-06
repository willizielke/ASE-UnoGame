package domain.entities;

public class SpecialCard extends Card {
    private CardNames symbol;

    public SpecialCard() {
    }

    public SpecialCard(CardColor color, CardNames symbol) {
        super(10, color);
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SpecialCard)) {
            return false;
        }
        SpecialCard other = (SpecialCard) obj;

        return symbol != null ? symbol.equals(other.symbol) && getColor().equals(other.getColor()) : other.symbol == null;
    }

    public CardNames getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol + " " + getColor();
    }
}
