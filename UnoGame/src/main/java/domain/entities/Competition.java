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
    private List<PlayerCurrentCompetitionStatistic> playerCurrentCompetitionStatistics;
    private List<Match> matches;
    private String winner;

    public Competition() {
        players = new ArrayList<>();
        playerCurrentCompetitionStatistics = new ArrayList<>();
        matches = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWinner() {
        return winner;
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

    public List<PlayerCurrentCompetitionStatistic> getPlayerCurrentCompetitionStatistics() {
        return playerCurrentCompetitionStatistics;
    }

    public void addPlayerCurrentCompetitionStatistic(Player player) {
        PlayerCurrentCompetitionStatistic playerCurrentCompetitionStatistic = new PlayerCurrentCompetitionStatistic(player);
        playerCurrentCompetitionStatistics.add(playerCurrentCompetitionStatistic);
    }

}
