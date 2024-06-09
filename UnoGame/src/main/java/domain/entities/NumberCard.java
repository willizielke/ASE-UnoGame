package domain.entities;

public class NumberCard extends Card {
    private int number;
    
    public NumberCard(String color, int number) {
        super(number, color);
        this.number = number;
    }
}