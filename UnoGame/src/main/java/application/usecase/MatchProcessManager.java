package application.usecase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import application.interfaces.IDataPersistence;
import domain.entities.Card;
import domain.entities.CardColor;
import domain.entities.CardNames;
import domain.entities.Deck;
import domain.entities.DeckBuilder;
import domain.entities.FastMatch;
import domain.entities.Match;
import domain.entities.NumberCard;
import domain.entities.Player;
import domain.entities.PlayerHistoryData;
import domain.entities.PlayerWithCards;
import domain.entities.Plus4Card;
import domain.entities.SpecialCard;
import domain.entities.WishCard;
import domain.service.Addition;
import domain.service.Calculator;
import domain.service.Subtraction;
import presentation.InputHandler;
import presentation.OutputHandler;

public class MatchProcessManager {
    private IDataPersistence dbService;
    private Match match;
    private PlayerWithCards playerWithCards;
    private boolean hasAlreadyPulled;
    private boolean moveToNextPlayer;
    private Card lastCard;
    private int nextPlayer;
    private CardColor wishedColor;
    private int cardsToDraw = 1;
    private boolean isClockwise = true;
    private Card playedCard;
    private Calculator calculator = new Calculator();

    public MatchProcessManager(IDataPersistence dbService) {
        this.dbService = dbService;
    }

    public void startMatch(Match match) throws IOException {
        this.match = match;
        this.nextPlayer = whoStarts(match.getPlayersWithCardsList().size());
        match.playedCards.add(match.deck.remove(0));
        this.lastCard = match.playedCards.get(match.playedCards.size() - 1);
        for (PlayerWithCards playerWithCards : match.getPlayersWithCardsList()) {
            playerWithCards.setTotalCardPoints(countPoints(playerWithCards));
        }

        while (matchIsNotOver(match)) {
            playTurn();
        }
        if (!(match instanceof FastMatch)) {
            setPlayerStatistics();
        }
    }

    private void playTurn() {
        this.hasAlreadyPulled = false;
        this.moveToNextPlayer = false;
        this.playerWithCards = match.getPlayersWithCardsList().get(nextPlayer);

        do {
            OutputHandler.clearConsole();
            lastCard = match.playedCards.get(match.playedCards.size() - 1);
            printNextPlayerMove();

            List<Integer> numbers = InputHandler.getNumbers(playerWithCards.getPlayerCards().size());

            if (numbers.get(0) == 0 && !hasAlreadyPulled) {
                handleCardPull(cardsToDraw);
            } else if (numbers.get(0) == 0) {
                moveToNextPlayer();
            } else {
                if (!checkInput(numbers, playerWithCards.getPlayerCards())) {
                    OutputHandler.printInvalidInputMessage();
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
            Card card = playerWithCards.removeCard(index - 1);
            Subtraction subtraction = new Subtraction(playerWithCards.getTotalCardPoints(), card.getPoints());
            calculator.calculate(subtraction);
            playerWithCards.setTotalCardPoints(subtraction.getResult());
            match.playedCards.add(card);
        }
        handleCardAction(numbers.size());
        moveToNextPlayer();
    }

    private void handleCardAction(int size) {
        if (playedCard instanceof SpecialCard) {
            handleSpecialCardAction((SpecialCard) playedCard, size);
        } else if (playedCard instanceof Plus4Card) {
            handlePlus4CardAction(size);
        } else if (playedCard instanceof WishCard) {
            handleWishCardAction();
        }
    }

    private void handleSpecialCardAction(SpecialCard specialCard, int size) {
        switch (specialCard.getSymbol()) {
            case PLUS2:
                updateCardsToDraw(2 * size);
                break;
            case SKIP:
                handleSkipAction(size);
                break;
            case REVERSE:
                handleReverseAction(size);
                break;
            case ZERO:
                handleChangeCards();
                break;
            default:
                break;
        }
    }

    private void handlePlus4CardAction(int size) {
        wishedColor = getWishedColor();
        updateCardsToDraw(4 * size);
    }

    private void handleWishCardAction() {
        wishedColor = getWishedColor();
    }

    private void updateCardsToDraw(int newCardsToDraw) {
        if (cardsToDraw == 1) {
            cardsToDraw = newCardsToDraw;
        } else {
            cardsToDraw += newCardsToDraw;
        }
    }

    private void handleSkipAction(int size) {
        if (size > 1 && match.getPlayersWithCardsList().size() > 2) {
            moveToNextPlayer();
        }
        moveToNextPlayer();
    }

    private void handleReverseAction(int size) {
        if (size == 1) {
            isClockwise = !isClockwise;
        }
        if (match.getMatchRules().getStrategy().reverseIsSkipIfOnlyTwoPlayers()) {
            moveToNextPlayer();
        }
    }

    private CardColor getWishedColor() {
        OutputHandler.printWishColorMessage();
        int wishedColorNr = InputHandler.getNumberBetween(1, 4);
        switch (wishedColorNr) {
            case 1:
                return CardColor.RED;
            case 2:
                return CardColor.GREEN;
            case 3:
                return CardColor.BLUE;
            case 4:
                return CardColor.YELLOW;
            default:
                return null;
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
        movePlayer(isClockwise);
    }

    private void moveToLastPlayer() {
        movePlayer(!isClockwise);
    }

    private void movePlayer(boolean clockwise) {
        int direction = clockwise ? 1 : -1;
        this.nextPlayer = (nextPlayer + direction + match.getPlayersWithCardsList().size())
                % match.getPlayersWithCardsList().size();
        this.moveToNextPlayer = true;
    }

    private void pullCardFromDeck(int cardsToDraw) {
        if (!deckHasCards(match.deck)) {
            Deck playedCardsAsDeck = new Deck(match.playedCards);
            Deck newDeck = DeckBuilder.shuffle(playedCardsAsDeck);
            match.deck = newDeck;
        }
        for (int i = 0; i < cardsToDraw; i++) {
            Card card = match.deck.remove(0);
            playerWithCards.addCard(card);
            Addition addition = new Addition(playerWithCards.getTotalCardPoints(), card.getPoints());
            calculator.calculate(addition);
            playerWithCards.setTotalCardPoints(addition.getResult());
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
                OutputHandler.printMatchOverMessage(playerWithCards.getPlayer().getPlayerName());
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
        printDetailsForPlayer();
        OutputHandler.printNextMove(playerWithCards.getPlayer().getPlayerName(), hasAlreadyPulled, cardsToDraw);
        printCardsOfNextPlayer(playerWithCards.getPlayerCards());
    }

    public void printDetailsForPlayer() {
        OutputHandler.printLastCard(lastCard.toString());
        if (lastCard.getColor().equals(CardColor.BLACK.toString())) {
            if (wishedColor == null) {
                Random random = new Random();
                wishedColor = CardColor.values()[random.nextInt(4)];
            }
            OutputHandler.printWishedColor(wishedColor.toString());
        }
        OutputHandler.printPlayerPoints(playerWithCards.getTotalCardPoints());
        List<Integer> cardsCountOfOthers = new ArrayList<>();
        for (int i = 0; i < match.getPlayersWithCardsList().size(); i++) {
            if (i == nextPlayer) {
                continue;
            }
            cardsCountOfOthers.add(match.getPlayersWithCardsList().get(i).getPlayerCards().size());
        }
        OutputHandler.printCardsCountOfOthers(cardsCountOfOthers);
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
        if (isSameColor && playedCard.getColor().equals(CardColor.BLACK.toString())
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
                if (playedSpecialCard.getSymbol() == CardNames.PLUS2) {
                    return true;
                }
            }
            return false;
        }

        if (lastCard instanceof WishCard || lastCard instanceof Plus4Card) {
            if (playedCard.getColor().toString().equals(wishedColor.toString())) {
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

    public void setPlayerStatistics() throws IOException {
        for (PlayerWithCards playerWithCards : match.getPlayersWithCardsList()) {
            PlayerHistoryData playerHistoryStatistic = playerWithCards.getPlayer().getPlayerStats();
            playerHistoryStatistic
                    .setAccumulatedPoints(playerHistoryStatistic.getAccumulatedPoints() + countPoints(playerWithCards));
            playerHistoryStatistic.setMatchCount(playerHistoryStatistic.getMatchCount() + 1);
            if (playerWithCards.getPlayerCards().size() == 0) {
                playerHistoryStatistic.setMatchWinCount(playerHistoryStatistic.getMatchWinCount() + 1);
            } else {
                playerHistoryStatistic.setMatchLoseCount(playerHistoryStatistic.getMatchLoseCount() + 1);
            }
            playerHistoryStatistic.setPointsPerMatch((double) (playerHistoryStatistic.getAccumulatedPoints())
                    / playerHistoryStatistic.getMatchCount());
            Player updatedPlayer = playerWithCards.getPlayer();
            updatedPlayer.setPlayerStats(playerHistoryStatistic);
            dbService.updatePlayer(updatedPlayer);
        }
    }

    public int countPoints(PlayerWithCards playerWithCards) {
        int points = 0;
        for (Card card : playerWithCards.getPlayerCards()) {
            Addition addition = new Addition(points, card.getPoints());
            calculator.calculate(addition);
            points = addition.getResult();
        }
        return points;
    }
}
