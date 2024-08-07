package application.usecase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.interfaces.IDataPersistence;
import domain.entities.Competition;
import domain.entities.Match;
import domain.entities.Player;
import domain.entities.PlayerWithCards;
import presentation.InputHandler;
import presentation.OutputHandler;
import domain.entities.PlayerCurrentCompetitionData;

public class CompetitionProcessManager {
    private IDataPersistence dbService;
    private Competition competition;

    public CompetitionProcessManager(IDataPersistence dbService) {
        this.dbService = dbService;
    }

    public void startCompetition(Competition competition) throws IOException {
        this.competition = competition;
        while (competitionIsNotOver()) {
            OutputHandler.clearConsole();
            OutputHandler.printCompetitionProcessViewSelection();
            int selection = InputHandler.getNumberBetween(1, 3);
            if (selection == 1) {
                Match match = playMatch();
                competition.getMatches().add(match);
                setCompetitionStatistic(match);
                dbService.updateCompetition(competition);
            } else if (selection == 2) {
                printAllMatchScores();
            } else {
                break;
            }
        }
        setCompetitionStatisticFinal();
    }

    public boolean competitionIsNotOver() {
        boolean isLeastPointsMode = competition.getCompetitionRules().isLeastPointsMode();
        if (isLeastPointsMode) {
            int playersStillInGame = 0;
            for (PlayerCurrentCompetitionData playerStats : competition.getPlayerCurrentCompetitionStatistics()) {
                if (playerStats.getAccumulatedPoints() < 101) {
                    playersStillInGame++;
                }
            }
            if (playersStillInGame == 1) {
                for (PlayerCurrentCompetitionData playerStats : competition
                        .getPlayerCurrentCompetitionStatistics()) {
                    if (playerStats.getAccumulatedPoints() < 101) {
                        competition.setWinnerId(playerStats.getPlayer().getId());
                        for (Player player : competition.getPlayers()) {
                            if (player.getId() == playerStats.getPlayer().getId()) {
                                OutputHandler.printWinnerOfCompetition(player.getPlayerName());
                                InputHandler.getInput();
                            }
                        }
                    }
                }
            }
        } else {
            for (PlayerCurrentCompetitionData playerStats : competition.getPlayerCurrentCompetitionStatistics()) {
                if (playerStats.getMatchWinCount() >= competition.getCompetitionRules().getMatchesToWin()) {
                    competition.setWinnerId(playerStats.getPlayer().getId());
                    for (Player player : competition.getPlayers()) {
                        if (player.getId() == playerStats.getPlayer().getId()) {
                            OutputHandler.printWinnerOfCompetition(player.getPlayerName());
                            InputHandler.getInput();
                        }
                    }
                }
            }
        }
        return competition.getWinnerId() == -1;
    }

    public Match playMatch() throws IOException {
        MatchCreationManager matchCreationManager = new MatchCreationManager(dbService);
        Match match = matchCreationManager.createCompetitionMatch(getPlayersInGame(),
                competition.getMatchRules());
        MatchProcessManager matchProcessManager = new MatchProcessManager(dbService);
        matchProcessManager.startMatch(match);
        return match;
    }

    public void setCompetitionStatistic(Match match) {
        List<PlayerCurrentCompetitionData> playerStats = competition.getPlayerCurrentCompetitionStatistics();
        for (PlayerCurrentCompetitionData playerStat : playerStats) {
            for (PlayerWithCards playerWithCards : match.getPlayersWithCardsList()) {
                updatePlayerStatisticsIfMatched(playerStat, playerWithCards, match.getWinnerId());
            }
        }
    }

    private void updatePlayerStatisticsIfMatched(PlayerCurrentCompetitionData playerStat,
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

    private void applyRule101IfNeeded(PlayerCurrentCompetitionData playerStat) {
        boolean isLeastPointsMode = competition.getCompetitionRules().isLeastPointsMode();
        boolean pointsEqual101 = playerStat.getAccumulatedPoints() == 101;

        if (isLeastPointsMode && pointsEqual101) {
            playerStat.setTimesPointsReseted(playerStat.getTimesPointsReseted() + 1);
            playerStat.setAccumulatedPoints(0);
        }
    }

    public void printAllMatchScores() {
        OutputHandler.printMatchScores();
        for (Match match : competition.getMatches()) {
            int matchNumber = competition.getMatches().indexOf(match) + 1;
            OutputHandler.printMatchNumber(matchNumber);
            for (PlayerWithCards playerWithCards : match.getPlayersWithCardsList()) {
                System.out.println(
                        playerWithCards.getPlayer().getPlayerName() + ": " + playerWithCards.getTotalCardPoints());
            }

        }
        boolean isLeastPointsMode = competition.getCompetitionRules().isLeastPointsMode();
        if (isLeastPointsMode) {
            OutputHandler.printTotalScores();
            for (PlayerCurrentCompetitionData playerStats : competition.getPlayerCurrentCompetitionStatistics()) {
                System.out.println(playerStats.getPlayer().getPlayerName() + ": " + playerStats.getAccumulatedPoints());
            }
        } else {
            OutputHandler.printTotalWins(competition.getCompetitionRules().getMatchesToWin());
            for (PlayerCurrentCompetitionData playerStats : competition.getPlayerCurrentCompetitionStatistics()) {
                System.out.println(playerStats.getPlayer().getPlayerName() + ": " + playerStats.getMatchWinCount());
            }
        }
        InputHandler.getInput();
    }

    public List<Player> getPlayersInGame() {
        List<Player> playersInGame = new ArrayList<>();
        for (PlayerCurrentCompetitionData playerStats : competition.getPlayerCurrentCompetitionStatistics()) {
            if (playerStats.getAccumulatedPoints() < 101) {
                playersInGame.add(playerStats.getPlayer());
            }
        }
        return playersInGame;
    }

    public void setCompetitionStatisticFinal() throws IOException {
        for (Player player : competition.getPlayers()) {
            player.getPlayerStats().setCompetitionWinCount(player.getPlayerStats().getCompetitionCount() + 1);
            if (player.getId() == competition.getWinnerId()) {
                player.getPlayerStats().setCompetitionWinCount(player.getPlayerStats().getCompetitionWinCount() + 1);
            }
            dbService.updatePlayer(player);
        }
        dbService.updateCompetition(competition);
    }
}
