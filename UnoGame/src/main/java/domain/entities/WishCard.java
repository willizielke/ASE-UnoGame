package domain.entities;

public class WishCard extends Card {
    public WishCard() {
        super(20, CardColor.BLACK);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WishCard)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return CardNames.WISH.toString();
    }
}