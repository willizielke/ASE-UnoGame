package domain.service;

public interface MatchStrategy {
    boolean isBlackOnBlackCardAllowed();
    boolean isSkipIfDrawedCardsBecauseOfPlusXCard();
    boolean reverseIsSkipIfOnlyTwoPlayers();
    int getCardsToDrawWhenForgotToSayUno();
    int getCardsToDrawWhenForgotToSayUnoUno();
}