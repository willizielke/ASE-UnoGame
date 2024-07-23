package domain.entities;

import java.util.List;

public class PlayerWithCards {
    private Player player;
    private List<Card> playerCards;

    public PlayerWithCards(Player player, List<Card> playerCards) {
        this.player = player;
        this.playerCards = playerCards;
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

}
