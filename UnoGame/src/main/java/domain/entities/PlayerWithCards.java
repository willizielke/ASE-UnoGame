package domain.entities;

import java.util.List;

import domain.valueobjects.Card;

public class PlayerWithCards {
    private Player player;
    private List<Card> playerCards;
    private int totalCardPoints;

    public PlayerWithCards() {
    }

    public PlayerWithCards(Player player, List<Card> playerCards) {
        this.player = player;
        this.playerCards = playerCards;
        this.totalCardPoints = 0;
    }

    public void addCard(Card card) {
        playerCards.add(card);
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

    public Card removeCard(int index) {
        return playerCards.remove(index);
    }

    public int getTotalCardPoints() {
        return totalCardPoints;
    }

    public void setTotalCardPoints(int totalCardPoints) {
        this.totalCardPoints = totalCardPoints;
    }
}
