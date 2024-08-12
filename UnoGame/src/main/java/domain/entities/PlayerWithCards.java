package domain.entities;

import java.util.List;
import domain.service.Addition;
import domain.service.Calculator;
import domain.service.Subtraction;
import domain.valueobjects.Card;

public class PlayerWithCards {
    private Player player;
    private List<Card> playerCards;
    private int totalCardPoints;
    private Calculator calculator = new Calculator();;

    public PlayerWithCards() {
    }

    public PlayerWithCards(Player player, List<Card> playerCards) {
        this.player = player;
        this.playerCards = playerCards;
        this.totalCardPoints = 0;
    }

    public void addCard(Card card) {
        playerCards.add(card);
        Addition addition = new Addition(getTotalCardPoints(), card.getPoints());
        calculator.calculate(addition);
        setTotalCardPoints(addition.getResult());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public void removeCard(int index) {
        Card card = playerCards.get(index);
        playerCards.remove(index);
        Subtraction subtraction = new Subtraction(getTotalCardPoints(), card.getPoints());
        calculator.calculate(subtraction);
        setTotalCardPoints(subtraction.getResult());
    }

    public int getTotalCardPoints() {
        return totalCardPoints;
    }

    public void setTotalCardPoints(int totalCardPoints) {
        this.totalCardPoints = totalCardPoints;
    }

    public int countTotalCardPoints() {
        int totalCardPoints = 0;
        for (Card card : playerCards) {
            totalCardPoints += card.getPoints();
        }
        return totalCardPoints;
    }
}
