package domain.entities;

public abstract class Card {
    private int points;
    private String color;

    public Card(int points, String color) {
        this.points = points;
        this.color = color;
    }
}
