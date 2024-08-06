package application.usecase;

import java.io.IOException;
import java.util.List;

import application.interfaces.IDataPersistence;
import domain.entities.Competition;
import domain.entities.Player;
import domain.service.CompetitionRules;
import domain.service.LocalMatchStrategy;
import domain.service.MatchRules;
import domain.service.OriginalMatchStrategy;
import presentation.InputHandler;
import presentation.OutputHandler;

public class CompetitionCreationManager {
    private Competition competition = new Competition();
    private IDataPersistence dbService;

    public CompetitionCreationManager(IDataPersistence dbService) {
        this.dbService = dbService;
    }

    public Competition createCompetition() throws IOException {
        OutputHandler.printGetNameMessage(UseCaseConstants.COMPETITION);
        competition.setName(InputHandler.getName(UseCaseConstants.COMPETITION));
        OutputHandler.printGetPlayerCountMessage();

        int playerCount = InputHandler.getNumberBetween(1, 10);

        for (int i = 1; i <= playerCount; i++) {
            Player player = null;
            boolean playerIsAlreadyInTheCompetition = false;
            do {
                OutputHandler.printSlash(i, playerCount);
                player = createOrLoadPlayer();
                playerIsAlreadyInTheCompetition = playerIsAlreadyInTheCompetition(player, competition.getPlayers());
                if (playerIsAlreadyInTheCompetition) {
                    OutputHandler.printInvalidInputMessagePlayerIsAlreadyInTheGame();
                }
            } while (playerIsAlreadyInTheCompetition);
            competition.addPlayer(player);
            competition.addPlayerCurrentCompetitionStatistic(player);
        }
        competition.setCompetitionRules(getCompetitionRules());
        competition.setMatchRules(getMatchRules());
        dbService.saveCompetition(competition);
        return competition;
    }

    public Player createPlayer() throws IOException {
        OutputHandler.printGetNameMessage(UseCaseConstants.PLAYER);
        String playerName = InputHandler.getName(UseCaseConstants.PLAYER);
        Player player = dbService.saveAndReturnPlayer(playerName);
        return player;
    }

    public Player loadPlayer() throws IOException {
        OutputHandler.printLoadMessage(UseCaseConstants.PLAYER);
        List<Player> players = dbService.readAllPlayers();
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + ". " + players.get(i).getPlayerName());
        }
        int optionNr = InputHandler.getNumberBetween(1, players.size());
        Player player = dbService.loadPlayer(players.get(optionNr - 1).getId());
        return player;
    }

    public Player createOrLoadPlayer() throws IOException {
        OutputHandler.printCreateOrLoadMessage(UseCaseConstants.PLAYER);
        int option = InputHandler.getNumberBetween(1, 2);
        if (option == 1) {
            return createPlayer();
        } else {
            return loadPlayer();
        }
    }

    public boolean playerIsAlreadyInTheCompetition(Player player, List<Player> playersList) {
        if(playersList.isEmpty()) {
            return false;
        }
        for (Player p : playersList) {
            if (p.getPlayerName().equals(player.getPlayerName())) {
                return true;
            }
        }
        return false;
    }

    public MatchRules getMatchRules() {
        OutputHandler.printMatchRulesSelection();
        int matchRulesOption = InputHandler.getNumberBetween(1, 2);
        if (matchRulesOption == 1) {
            return new MatchRules(new LocalMatchStrategy());
        } else {
            return new MatchRules(new OriginalMatchStrategy());
        }
    }

    public CompetitionRules getCompetitionRules() {
        OutputHandler.printCompetitionRulesSelection();
        int competitionRulesOption = InputHandler.getNumberBetween(1, 2);
        if (competitionRulesOption == 1) {
            CompetitionRules competitionRules = new CompetitionRules();
            competitionRules.setMatchWinsMode(true);
            OutputHandler.getMatchesToWin();
            competitionRules.setMatchesToWin(InputHandler.getNumberBetween(1, 10));
            // not used:
            competitionRules.setLeastPointsMode(false);
            competitionRules.setMaxPoints(0);
            return competitionRules;
        } else {
            CompetitionRules competitionRules = new CompetitionRules();
            competitionRules.setLeastPointsMode(true);
            competitionRules.setMaxPoints(101);
            // not used:
            competitionRules.setMatchWinsMode(false);
            competitionRules.setMatchesToWin(0);
            return competitionRules;
        }
    }
}
