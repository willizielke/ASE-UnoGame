package domain.entities;

public abstract class Card {
    private int points;
    private String color;

    public int getPoints() {
        return points;
    }

    public String getColor() {
        return color;
    }

    public Card(int points, String color) {
        this.points = points;
        this.color = color;
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();
}
