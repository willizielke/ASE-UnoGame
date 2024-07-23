package domain.entities;

public class SpecialCard extends Card {
    private String symbol;

    public SpecialCard(String color, String symbol) {
        super(10, color);
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SpecialCard)) {
            return false;
        }
        SpecialCard other = (SpecialCard) obj;

        return symbol != null ? symbol.equals(other.symbol) : other.symbol == null;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol + " " + getColor();
    }
}
