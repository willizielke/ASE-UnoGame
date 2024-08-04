package domain.entities;

import common.GlobalConstants;

public class Plus4Card extends Card {
    public Plus4Card() {
        super(40, GlobalConstants.BLACK);
    }
    @Override
    public String toString() {
        return GlobalConstants.PLUS4CARD;
    }
}