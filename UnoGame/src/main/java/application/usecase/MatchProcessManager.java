package application.usecase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import common.GlobalConstants;
import common.GlobalMethods;
import common.Messages;
import domain.entities.Card;
import domain.entities.Deck;
import domain.entities.DeckBuilder;
import domain.entities.Match;
import domain.entities.NumberCard;
import domain.entities.PlayerWithCards;
import domain.entities.Plus4Card;
import domain.entities.SpecialCard;
import domain.entities.WishCard;
import presentation.input.InputHandler;

public class MatchProcessManager {
    private Match match;
    private PlayerWithCards playerWithCards;
    private boolean hasAlreadyPulled;
    private boolean moveToNextPlayer;
    private Card lastCard;
    private int nextPlayer;
    private String wishedColor;
    private int cardsToDraw = 1;
    private boolean isClockwise = true;
    private Card playedCard;

    public void startMatch(Match match) {
        this.match = match;
        this.nextPlayer = whoStarts(match.getPlayersWithCardsList().size());
        match.playedCards.add(match.deck.remove(0));
        this.lastCard = match.playedCards.get(match.playedCards.size() - 1);

        while (matchIsNotOver(match)) {
            playTurn();
        }
    }

    private void playTurn() {
        this.hasAlreadyPulled = false;
        this.moveToNextPlayer = false;
        this.playerWithCards = match.getPlayersWithCardsList().get(nextPlayer);

        do {
            GlobalMethods.clearConsole();
            lastCard = match.playedCards.get(match.playedCards.size() - 1);
            printNextPlayerMove();

            List<Integer> numbers = InputHandler.getNumbers(playerWithCards.getPlayerCards().size());

            if (numbers.get(0) == 0 && !hasAlreadyPulled) {
                handleCardPull(cardsToDraw);
            } else if (numbers.get(0) == 0) {
                moveToNextPlayer();
            } else {
                if (!checkInput(numbers, playerWithCards.getPlayerCards())) {
                    Messages.printInvalidInputMessage();
                    InputHandler.getInput();
                    continue;
                }
                handleCardPlay(numbers);
            }

        } while (!moveToNextPlayer);
    }

    public boolean playerCantPlayCard() {
        return false;
    }

    private void handleCardPull(int cardsToDraw) {
        if (!hasAlreadyPulled) {
            pullCardFromDeck(cardsToDraw);
            hasAlreadyPulled = true;
            if (cardsToDraw > 1 && match.getMatchRules().getStrategy().isSkipIfDrawedCardsBecauseOfPlusXCard()) {
                moveToNextPlayer();
            }
        } else {
            moveToNextPlayer();
        }
    }

    private void handleCardPlay(List<Integer> numbers) {
        numbers.sort(Collections.reverseOrder()); // Sort the numbers in descending order
        for (int index : numbers) {
            match.playedCards.add(playerWithCards.removeCard(index - 1));
        }
        handleCardAction(numbers.size());
        moveToNextPlayer();
    }

    private void handleCardAction(int size) {
        if (playedCard instanceof SpecialCard) {
            SpecialCard specialCard = (SpecialCard) playedCard;
            switch (specialCard.getSymbol()) {
                case GlobalConstants.PLUS2:
                    if (cardsToDraw == 1) {
                        cardsToDraw = (2 * size);
                    } else {
                        cardsToDraw += (2 * size);
                    }
                    break;
                case GlobalConstants.SKIP:
                    if (size > 1) {
                        moveToNextPlayer();
                    }
                    moveToNextPlayer();
                    break;
                case GlobalConstants.REVERSE:
                    if (size == 1) {
                        isClockwise = !isClockwise;
                    }
                    if (match.getMatchRules().getStrategy().reverseIsSkipIfOnlyTwoPlayers()) {
                        moveToNextPlayer();
                    }
                    break;
                case GlobalConstants.ZERO:
                    handleChangeCards();
                    break;
                default:
                    break;
            }
        } else if (playedCard instanceof Plus4Card) {
            Messages.printWishColorMessage();
            int wishedColorNr = InputHandler.getNumberBetween(1, 4);
            switch (wishedColorNr) {
                case 1:
                    wishedColor = GlobalConstants.RED;
                    break;
                case 2:
                    wishedColor = GlobalConstants.GREEN;
                    break;
                case 3:
                    wishedColor = GlobalConstants.BLUE;
                    break;
                case 4:
                    wishedColor = GlobalConstants.YELLOW;
                    break;
                default:
                    break;
            }
            wishedColor = GlobalConstants.COLORS.get(wishedColorNr - 1);
            if (cardsToDraw == 1) {
                cardsToDraw = (4 * size);
            } else {
                cardsToDraw += (4 * size);
            }
        } else if (playedCard instanceof WishCard) {
            Messages.printWishColorMessage();
            int wishedColorNr = InputHandler.getNumberBetween(1, 4);
            switch (wishedColorNr) {
                case 1:
                    wishedColor = GlobalConstants.RED;
                    break;
                case 2:
                    wishedColor = GlobalConstants.GREEN;
                    break;
                case 3:
                    wishedColor = GlobalConstants.BLUE;
                    break;
                case 4:
                    wishedColor = GlobalConstants.YELLOW;
                    break;
                default:
                    break;
            }
            wishedColor = GlobalConstants.COLORS.get(wishedColorNr - 1);
        }
    }

    private void handleChangeCards() {
        List<PlayerWithCards> playersWithCardsList = match.getPlayersWithCardsList();
        int personsSize = playersWithCardsList.size();
        List<PlayerWithCards> updatedPlayersWithCardsList = new ArrayList<>(personsSize);

        for (int i = 0; i < personsSize; i++) {
            PlayerWithCards currentPlayer = playersWithCardsList.get(i);
            int nextPlayerIndex;
            if (isClockwise) {
                nextPlayerIndex = (i + 1) % personsSize;
            } else {
                nextPlayerIndex = (i - 1 + personsSize) % personsSize;
            }
            PlayerWithCards nextPlayer = playersWithCardsList.get(nextPlayerIndex);
            PlayerWithCards updatedNextPlayer = new PlayerWithCards(nextPlayer.getPlayer(),
                    currentPlayer.getPlayerCards());
            updatedPlayersWithCardsList.add(updatedNextPlayer);
        }
        match.setPlayersWithCardsList(updatedPlayersWithCardsList);
        moveToLastPlayer();
    }

    private void moveToNextPlayer() {
        if (isClockwise) {
            this.nextPlayer = (nextPlayer + 1) % match.getPlayersWithCardsList().size();
        } else {
            this.nextPlayer = (nextPlayer - 1 + match.getPlayersWithCardsList().size())
                    % match.getPlayersWithCardsList().size();
        }
        this.moveToNextPlayer = true;
    }

    private void moveToLastPlayer() {
        if (isClockwise) {
            this.nextPlayer = (nextPlayer - 1 + match.getPlayersWithCardsList().size())
                    % match.getPlayersWithCardsList().size();
        } else {
            this.nextPlayer = (nextPlayer + 1) % match.getPlayersWithCardsList().size();
        }
    }

    private void pullCardFromDeck(int cardsToDraw) {
        if (!deckHasCards(match.deck)) {
            DeckBuilder deckBuilder = new DeckBuilder();
            Deck playedCardsAsDeck = new Deck(match.playedCards);
            Deck newDeck = deckBuilder.shuffle(playedCardsAsDeck);
            match.deck = newDeck;
        }
        for (int i = 0; i < cardsToDraw; i++) {
            playerWithCards.addCard(match.deck.remove(0));
        }
        this.cardsToDraw = 1;
        this.hasAlreadyPulled = true;
        match.getPlayersWithCardsList().set(nextPlayer, playerWithCards);
    }

    public List<Card> getCardsOfNextPlayer(Match match, int playerMove) {
        return match.getPlayersWithCardsList().get(playerMove).getPlayerCards();
    }

    public int whoStarts(int playerCount) {
        Random random = new Random();
        return random.nextInt(playerCount);
    }

    public boolean matchIsNotOver(Match match) {
        for (PlayerWithCards playerWithCards : match.getPlayersWithCardsList()) {
            if (playerWithCards.getPlayerCards().size() == 0) {
                match.setWinnerId(playerWithCards.getPlayer().getId());
                Messages.printMatchOverMessage(playerWithCards.getPlayer().getPlayerName());
                InputHandler.getInput();
                return false;
            }
        }
        return true;
    }

    public void printCardsOfNextPlayer(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(i + 1 + ". " + cards.get(i));
        }
    }

    public void printNextPlayerMove() {
        System.out.println("Last card: " + lastCard);
        if (lastCard.getColor().equals(GlobalConstants.BLACK)) {
            System.out.println("Wished color: " + wishedColor);
        }
        Messages.printNextMove(playerWithCards.getPlayer().getPlayerName(), hasAlreadyPulled, cardsToDraw);
        printCardsOfNextPlayer(playerWithCards.getPlayerCards());
    }

    public boolean checkInput(List<Integer> numbers, List<Card> playerCards) {
        if (checkIfCorrectSyntax(numbers, playerCards) && checkRules(numbers, playerCards)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfCorrectSyntax(List<Integer> numbers, List<Card> playerCards) {
        if (numbers.size() == 1 && numbers.get(0) <= playerCards.size()) {
            return true;
        } else if (numbers.size() == 2) {
            int input1 = numbers.get(0) - 1;
            int input2 = numbers.get(1) - 1;
            if (input1 != -1 && input2 != -1 && playerCards.get(input1).equals(playerCards.get(input2))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean checkRules(List<Integer> numbers, List<Card> playerCards) {
        playedCard = playerCards.get(numbers.get(0) - 1);
        boolean blackOnblackAllowed = match.getMatchRules().getStrategy().isBlackOnBlackCardAllowed();
        boolean isSameColor = false;
        isSameColor = playedCard.getColor().equals(lastCard.getColor());

        // Black cards exception
        if (isSameColor && playedCard.getColor().equals(GlobalConstants.BLACK)
                && !blackOnblackAllowed) {
            return false;
        }
        if (playedCard instanceof Plus4Card) {
            return true;
        }
        // Plus4Card and Plus2Card rules
        if (cardsToDraw > 1) {
            if (lastCard instanceof Plus4Card && !(playedCard instanceof Plus4Card)) {
                return false;
            }
            // If the lastCard is not Plus4, then card is Plus2
            if (playedCard instanceof SpecialCard) {
                SpecialCard playedSpecialCard = (SpecialCard) playedCard;
                if (playedSpecialCard.getSymbol() == GlobalConstants.PLUS2) {
                    return true;
                }
            }
            return false;
        }

        if (lastCard instanceof WishCard || lastCard instanceof Plus4Card) {
            if (playedCard.getColor().equals(wishedColor)) {
                return true;
            }
            return false;
        }

        if (playedCard instanceof WishCard || playedCard instanceof Plus4Card) {
            return true;
        }

        if (isSameColor) {
            return true;
        }
        if ((lastCard instanceof NumberCard && playedCard instanceof SpecialCard)
                || (lastCard instanceof SpecialCard && playedCard instanceof NumberCard)) {
            return false;
        }

        if (lastCard instanceof NumberCard && playedCard instanceof NumberCard) {
            NumberCard lastNumberCard = (NumberCard) lastCard;
            NumberCard playedNumberCard = (NumberCard) playedCard;
            if (lastNumberCard.getNumber() == playedNumberCard.getNumber()) {
                return true;
            } else {
                return false;
            }
        }

        if (lastCard instanceof SpecialCard && playedCard instanceof SpecialCard) {
            SpecialCard lastSpecialCard = (SpecialCard) lastCard;
            SpecialCard playedSpecialCard = (SpecialCard) playedCard;
            if (lastSpecialCard.getSymbol().equals(playedSpecialCard.getSymbol())) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public boolean deckHasCards(Deck deck) {
        if (deck.getCards().size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
