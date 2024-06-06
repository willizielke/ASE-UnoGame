package domain.entities;

import java.util.List;

import domain.service.MatchRules;

public class Match {
    private List<PlayerWithCards> playersWithCardsList;
    private int winnerId;
    private MatchRules matchRules;
}
