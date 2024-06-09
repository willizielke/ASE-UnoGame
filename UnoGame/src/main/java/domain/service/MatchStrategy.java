package domain.service;

public interface MatchStrategy {
    boolean isBlackOnBlackCardAllowed();
    boolean isSkipIfDrawedCardsBecauseOfPlusXCard();
    boolean isReverseIsSkipIfOnlyTwoPlayers();
    int getCardsToDrawWhenForgotToSayUno();
    int getCardsToDrawWhenForgotToSayUnoUno();
}