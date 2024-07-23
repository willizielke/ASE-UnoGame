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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Card card = (Card) obj;

        return points == card.points &&
                (color != null ? color.equals(card.color) : card.color == null);
    }

    @Override
    public String toString() {
        return "";
    }
}
