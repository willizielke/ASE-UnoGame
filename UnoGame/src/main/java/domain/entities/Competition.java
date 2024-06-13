package domain.entities;

import java.util.List;

import domain.service.CompetitionRules;
import domain.service.MatchRules;

public class Competition {
    private String name;
    private List<PlayerCurrentCompetitionStatistic> playerCurrentCompetitionStatistics;
    private String winner;
    private List<Match> matches;
    private List<String> players;
    private MatchRules matchRules;
    private CompetitionRules competitionRules;
    private int matchesPlayed;

    public String getName() {
        return name;
    }
}
