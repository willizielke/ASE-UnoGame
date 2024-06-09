package domain.service;

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
    public boolean isReverseIsSkipIfOnlyTwoPlayers() {
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