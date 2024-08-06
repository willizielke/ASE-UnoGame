package domain.entities;

public class NumberCard extends Card {
    private int number;

    public NumberCard(String color, int number) {
        super(number, color);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof NumberCard)){
            return false;
        }

        NumberCard other = (NumberCard) obj;
        return number == other.number && getColor().equals(other.getColor());
    }

    @Override
    public String toString() {
        return number + " " + getColor();
    }
}