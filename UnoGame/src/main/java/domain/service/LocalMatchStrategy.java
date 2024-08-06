package domain.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("local")
@JsonIgnoreProperties(ignoreUnknown = true)

public class LocalMatchStrategy implements MatchStrategy {
    public LocalMatchStrategy() {
    }
    @Override
    public boolean isBlackOnBlackCardAllowed() {
        return true;
    }

    @Override
    public boolean isSkipIfDrawedCardsBecauseOfPlusXCard() {
        return true;
    }

    @Override
    public boolean reverseIsSkipIfOnlyTwoPlayers() {
        return true;
    }

    @Override
    public int getCardsToDrawWhenForgotToSayUno() {
        return 1;
    }

    @Override
    public int getCardsToDrawWhenForgotToSayUnoUno() {
        return 2;
    }
}