package domain.entities;

public class SpecialCard extends Card {
    private String symbol;

    public SpecialCard(String color, String symbol) {
        super(10, color);
        this.symbol = symbol;
    }
}
