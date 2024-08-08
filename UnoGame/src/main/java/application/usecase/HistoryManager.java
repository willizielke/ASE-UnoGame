package application.usecase;

import java.util.List;

import domain.entities.Player;
import domain.entities.PlayerHistoryData;
import domain.repositories.PlayerRepository;
import presentation.InputHandler;
import presentation.OutputHandler;

public class HistoryManager {
    private PlayerRepository playerRepository;

    public HistoryManager(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void printHistory() throws Exception {
        List<Player> players = playerRepository.readAllPlayers();

        int sortOption;
        do {

            System.out.printf("%-15s | %-18s | %-22s | %-11s | %-15s | %-16s | %-19s | %-15s%n",
                    "Player Name", "Competition Count", "Competition Win Count", "Match Count",
                    "Match Win Count", "Match Lose Count", "Accumulated Points", "Points Per Match");

            for (Player player : players) {
                PlayerHistoryData playerHistory = player.getPlayerStats();
                System.out.printf("%-15s | %-18d | %-22d | %-11d | %-15d | %-16d | %-19d | %-15.2f%n",
                        player.getPlayerName(),
                        playerHistory.getCompetitionCount(),
                        playerHistory.getCompetitionWinCount(),
                        playerHistory.getMatchCount(),
                        playerHistory.getMatchWinCount(),
                        playerHistory.getMatchLoseCount(),
                        playerHistory.getAccumulatedPoints(),
                        playerHistory.getPointsPerMatch());
            }

            OutputHandler.printSortOptions();
            sortOption = InputHandler.getNumberBetween(1, 8);
            // sort the table after the selected header
            switch (sortOption) {
                case 1:
                    players.sort((p1, p2) -> p2.getPlayerStats().getCompetitionCount()
                            - p1.getPlayerStats().getCompetitionCount());
                    break;
                case 2:
                    players.sort((p1, p2) -> p2.getPlayerStats().getCompetitionWinCount()
                            - p1.getPlayerStats().getCompetitionWinCount());
                    break;
                case 3:
                    players.sort((p1, p2) -> p2.getPlayerStats().getMatchCount() - p1.getPlayerStats().getMatchCount());
                    break;
                case 4:
                    players.sort(
                            (p1, p2) -> p2.getPlayerStats().getMatchWinCount()
                                    - p1.getPlayerStats().getMatchWinCount());
                    break;
                case 5:
                    players.sort(
                            (p1, p2) -> p2.getPlayerStats().getMatchLoseCount()
                                    - p1.getPlayerStats().getMatchLoseCount());
                    break;
                case 6:
                    players.sort((p1, p2) -> p2.getPlayerStats().getAccumulatedPoints()
                            - p1.getPlayerStats().getAccumulatedPoints());
                    break;
                case 7:
                    players.sort((p1, p2) -> (int) (p2.getPlayerStats().getPointsPerMatch()
                            - p1.getPlayerStats().getPointsPerMatch()));
                    break;
                case 8:
                    // back
                    break;
                default:
                    break;
            }
        } while (sortOption != 8);
    }

}
