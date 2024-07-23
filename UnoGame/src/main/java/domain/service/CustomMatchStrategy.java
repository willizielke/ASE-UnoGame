package domain.service;

public class CustomMatchStrategy implements MatchStrategy {
    private boolean blackOnBlackCardAllowed;
    private boolean skipIfDrawedCardsBecauseOfPlusXCard;
    private boolean reverseIsSkipIfOnlyTwoPlayers;
    private int cardsToDrawWhenForgotToSayUno;
    private int cardsToDrawWhenForgotToSayUnoUno;

    @Override
    public boolean isBlackOnBlackCardAllowed() {
        return blackOnBlackCardAllowed;
    }

    @Override
    public boolean isSkipIfDrawedCardsBecauseOfPlusXCard() {
        return skipIfDrawedCardsBecauseOfPlusXCard;
    }

    @Override
    public boolean reverseIsSkipIfOnlyTwoPlayers() {
        return reverseIsSkipIfOnlyTwoPlayers;
    }

    @Override
    public int getCardsToDrawWhenForgotToSayUno() {
        return cardsToDrawWhenForgotToSayUno;
    }

    @Override
    public int getCardsToDrawWhenForgotToSayUnoUno() {
        return cardsToDrawWhenForgotToSayUnoUno;
    }
}
