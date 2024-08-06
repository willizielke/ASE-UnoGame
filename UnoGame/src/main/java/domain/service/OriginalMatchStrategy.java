package domain.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("original")

@JsonIgnoreProperties(ignoreUnknown = true)
public class OriginalMatchStrategy implements MatchStrategy {
    @Override
    public boolean isBlackOnBlackCardAllowed() {
        return false;
    }

    @Override
    public boolean isSkipIfDrawedCardsBecauseOfPlusXCard() {
        return false;
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