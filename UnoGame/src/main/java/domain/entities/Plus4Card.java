package domain.entities;

public class Plus4Card extends Card {
    public Plus4Card() {
        super(40, CardColor.BLACK);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Plus4Card)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return CardNames.PLUS4.toString();
    }
}