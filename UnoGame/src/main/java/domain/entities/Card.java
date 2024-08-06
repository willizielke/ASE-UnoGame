package domain.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = NumberCard.class, name = "numberCard"),
        @JsonSubTypes.Type(value = Plus4Card.class, name = "plus4Card"),
        @JsonSubTypes.Type(value = SpecialCard.class, name = "specialCard"),
        @JsonSubTypes.Type(value = WishCard.class, name = "wishCard")
})
public abstract class Card {
    private int points;
    private String color;

    public int getPoints() {
        return points;
    }

    public String getColor() {
        return color;
    }

    public Card(int points, CardColor color) {
        this.points = points;
        this.color = color.toString();
    }

    public Card() {
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();
}
