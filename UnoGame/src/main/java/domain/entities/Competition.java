package domain.entities;

import java.util.ArrayList;
import java.util.List;

import domain.service.CompetitionRules;
import domain.service.MatchRules;

public class Competition {
    private String name;
    private List<Player> players;
    private MatchRules matchRules;
    private CompetitionRules competitionRules;
    private List<PlayerCurrentCompetitionData> playerCurrentCompetitionStatistics;
    private List<Match> matches;
    private int winnerId;

    public Competition() {
        players = new ArrayList<>();
        playerCurrentCompetitionStatistics = new ArrayList<>();
        matches = new ArrayList<>();
        winnerId = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public MatchRules getMatchRules() {
        return matchRules;
    }

    public void setMatchRules(MatchRules matchRules) {
        this.matchRules = matchRules;
    }

    public CompetitionRules getCompetitionRules() {
        return competitionRules;
    }

    public void setCompetitionRules(CompetitionRules competitionRules) {
        this.competitionRules = competitionRules;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public List<PlayerCurrentCompetitionData> getPlayerCurrentCompetitionStatistics() {
        return playerCurrentCompetitionStatistics;
    }

    public void addPlayerCurrentCompetitionStatistic(Player player) {
        PlayerCurrentCompetitionData playerCurrentCompetitionStatistic = new PlayerCurrentCompetitionData(
                player);
        playerCurrentCompetitionStatistics.add(playerCurrentCompetitionStatistic);
    }

    public void setWinnerId(int id) {
        this.winnerId = id;
    }

}
