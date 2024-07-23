package domain.service;

public class LocalMatchStrategy implements MatchStrategy {
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