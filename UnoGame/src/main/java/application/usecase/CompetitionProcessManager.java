package application.usecase;

import java.io.IOException;
import java.util.List;

import application.interfaces.IDataPersistence;
import common.GlobalMethods;
import common.Messages;
import domain.entities.Competition;
import domain.entities.Match;
import domain.entities.PlayerWithCards;
import domain.entities.PlayerCurrentCompetitionStatistic;
import presentation.console.CompetitionMatchesViewGenerator;
import presentation.input.InputHandler;

public class CompetitionProcessManager {
    private IDataPersistence dbService;
    private Competition competition;

    public CompetitionProcessManager(IDataPersistence dbService) {
        this.dbService = dbService;
    }

    public void startCompetition(Competition competition) throws IOException {
        this.competition = competition;
        while (competitionIsNotOver(competition)) {
            GlobalMethods.clearConsole();
            Messages.printCompetitionProcessViewSelection();
            int selection = InputHandler.getNumberBetween(1, 3);
            if (selection == 1) {
                Match match = playMatch();
                competition.getMatches().add(match);
                setCompetitionStatistic(match);
            } else if (selection == 2) {
                CompetitionMatchesViewGenerator.printAllMatchScores(competition);
            } else {
                break;
            }
        }
    }

    public boolean competitionIsNotOver(Competition competition) {

        return true;
    }

    public Match playMatch() throws IOException {
        MatchCreationManager matchCreationManager = new MatchCreationManager(dbService);
        Match match = matchCreationManager.createCompetitionMatch(competition.getPlayers(),
                competition.getMatchRules());
        MatchProcessManager matchProcessManager = new MatchProcessManager(dbService);
        matchProcessManager.startMatch(match);
        return match;
    }

    public void setCompetitionStatistic(Match match) {
        List<PlayerCurrentCompetitionStatistic> playerStats = competition.getPlayerCurrentCompetitionStatistics();
        for (PlayerCurrentCompetitionStatistic playerStat : playerStats) {
            for (PlayerWithCards playerWithCards : match.getPlayersWithCardsList()) {
                updatePlayerStatisticsIfMatched(playerStat, playerWithCards, match.getWinnerId());
            }
        }
    }

    private void updatePlayerStatisticsIfMatched(PlayerCurrentCompetitionStatistic playerStat,
            PlayerWithCards playerWithCards, long winnerId) {
        if (playerStat.getPlayer().getId() != playerWithCards.getPlayer().getId()) {
            return;
        }

        // Update win count if this player is the winner
        if (playerWithCards.getPlayer().getId() == winnerId) {
            playerStat.setMatchWinCount(playerStat.getMatchWinCount() + 1);
        }

        // Update accumulated points
        playerStat.setAccumulatedPoints(playerStat.getAccumulatedPoints() + playerWithCards.getTotalCardPoints());

        // Reset points if 101 rule applies
        applyRule101IfNeeded(playerStat);
    }

    private void applyRule101IfNeeded(PlayerCurrentCompetitionStatistic playerStat) {
        boolean isLeastPointsMode = competition.getCompetitionRules().isLeastPointsMode();
        boolean pointsEqual101 = playerStat.getAccumulatedPoints() == 101;

        if (isLeastPointsMode && pointsEqual101) {
            playerStat.setTimesPointsReseted(playerStat.getTimesPointsReseted() + 1);
            playerStat.setAccumulatedPoints(0);
        }
    }
}
