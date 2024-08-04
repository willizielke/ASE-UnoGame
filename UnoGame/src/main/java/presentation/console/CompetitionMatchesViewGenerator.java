package presentation.console;

import common.Messages;
import domain.entities.Card;
import domain.entities.Competition;
import domain.entities.Match;
import domain.entities.PlayerCurrentCompetitionStatistic;
import domain.entities.PlayerWithCards;

public class CompetitionMatchesViewGenerator {

    public static void printAllMatchScores(Competition competition) {
        Messages.printMatchScores();
        for (Match match : competition.getMatches()) {
            int matchNumber = competition.getMatches().indexOf(match) + 1;
            Messages.printMatchNumber(matchNumber);
            for (PlayerWithCards playerWithCards : match.getPlayersWithCardsList()) {
                int points = 0;
                for (Card card : playerWithCards.getPlayerCards()) {
                    points += card.getPoints();
                }
                System.out.println(playerWithCards.getPlayer().getPlayerName() + ": " + points);
            }

        }
        boolean isLeastPointsMode = competition.getCompetitionRules().isLeastPointsMode();
        if (isLeastPointsMode) {
            Messages.printTotalScores();
            for (PlayerCurrentCompetitionStatistic playerStats : competition.getPlayerCurrentCompetitionStatistics()) {
                System.out.println(playerStats.getPlayer().getPlayerName() + ": " + playerStats.getAccumulatedPoints());
            }
        }else {
            Messages.printTotalWins();
            for (PlayerCurrentCompetitionStatistic playerStats : competition.getPlayerCurrentCompetitionStatistics()) {
                System.out.println(playerStats.getPlayer().getPlayerName() + ": " + playerStats.getMatchWinCount());
            }
        }
    }
}
