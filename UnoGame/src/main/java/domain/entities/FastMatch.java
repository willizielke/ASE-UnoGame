package domain.entities;

import java.util.ArrayList;
import java.util.List;

import common.GlobalConstants;
import domain.service.MatchRules;
import domain.service.OriginalMatchStrategy;

public class FastMatch extends Match {
    public FastMatch(int playerCount) {
        super();
        List<PlayerWithCards> playersWithCardsList = new ArrayList<PlayerWithCards>();
        Deck deck = DeckBuilder.createDeck();
        for (int i = 0; i < playerCount; i++) {
            playersWithCardsList.add(new PlayerWithCards(new Player(GlobalConstants.PLAYER + (i + 0), i + 0), new ArrayList<Card>()));
            for (int j = 0; j < 7; j++) {
                playersWithCardsList.get(i).addCard(deck.getCards().remove(0));
            }
        }
        MatchRules matchRules = new MatchRules(new OriginalMatchStrategy());
        setPlayersWithCardsList(playersWithCardsList);
        setDeck(deck);
        setMatchRules(matchRules);
    }
}
