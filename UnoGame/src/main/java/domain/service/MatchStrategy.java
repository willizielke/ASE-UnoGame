package domain.service;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = OriginalMatchStrategy.class, name = "original"),
    @JsonSubTypes.Type(value = LocalMatchStrategy.class, name = "local"),
    @JsonSubTypes.Type(value = CustomMatchStrategy.class, name = "custom")
})

public interface MatchStrategy {
    boolean isBlackOnBlackCardAllowed();
    boolean isSkipIfDrawedCardsBecauseOfPlusXCard();
    boolean reverseIsSkipIfOnlyTwoPlayers();
    int getCardsToDrawWhenForgotToSayUno();
    int getCardsToDrawWhenForgotToSayUnoUno();
}