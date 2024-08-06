package domain.entities;

import common.GlobalConstants;

public class WishCard extends Card {
    public WishCard() {
        super(20, GlobalConstants.BLACK);
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
        return GlobalConstants.WISHCARD;
    }
}