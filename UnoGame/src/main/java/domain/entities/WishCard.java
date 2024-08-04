package domain.entities;

import common.GlobalConstants;

public class WishCard extends Card {
    public WishCard() {
        super(20, GlobalConstants.BLACK);
    }
    @Override
    public String toString() {
        return GlobalConstants.WISHCARD;
    }
}