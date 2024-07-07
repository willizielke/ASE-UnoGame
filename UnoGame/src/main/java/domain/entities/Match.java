package domain.entities;

import java.util.List;

import domain.service.MatchRules;

public class Match {
    private List<PlayerWithCards> playersWithCardsList;
    private int winnerId;
    private MatchRules matchRules;
    private Deck deck;

    public Match(List<PlayerWithCards> playersWithCardsList, Deck deck, MatchRules matchRules) {
        this.playersWithCardsList = playersWithCardsList;
        this.deck = deck;
        this.matchRules = matchRules;
        this.winnerId = -1;
    }

    public Match() {
    }

    public List<PlayerWithCards> getPlayersWithCardsList() {
        return playersWithCardsList;
    }

    public void setPlayersWithCardsList(List<PlayerWithCards> playersWithCardsList) {
        this.playersWithCardsList = playersWithCardsList;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    public MatchRules getMatchRules() {
        return matchRules;
    }

    public void setMatchRules(MatchRules matchRules) {
        this.matchRules = matchRules;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
