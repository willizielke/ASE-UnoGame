package application.usecase;

import java.util.List;

import domain.entities.Competition;
import domain.entities.Player;
import domain.repositories.CompetitionRepository;
import domain.repositories.PlayerRepository;
import domain.service.CompetitionRules;
import domain.service.LocalMatchStrategy;
import domain.service.MatchRules;
import domain.service.OriginalMatchStrategy;
import presentation.InputHandler;
import presentation.OutputHandler;

public class CompetitionCreationManager {
    private Competition competition = new Competition();
    private PlayerRepository playerRepository;
    private CompetitionRepository competitionRepository;
    private InputHandler inputHandler;

    public CompetitionCreationManager(PlayerRepository playerRepository, CompetitionRepository competitionRepository,
            InputHandler inputHandler) {
        this.playerRepository = playerRepository;
        this.competitionRepository = competitionRepository;
        this.inputHandler = inputHandler;
    }

    public Competition createCompetition() throws Exception {
        OutputHandler.printGetNameMessage(UseCaseConstants.COMPETITION);
        while (competition.getName() == null) {
            String competitionName = inputHandler.getName(UseCaseConstants.COMPETITION);
            if (competitionRepository.checkIfNameAlreadyExistsCompetition(competitionName)) {
                OutputHandler.printInvalidInputMessageNameAlreadyExists(competitionName);
                continue;
            }
            competition.setName(competitionName);
        }
        OutputHandler.printGetPlayerCountMessage();
        int playerCount = inputHandler.getNumberBetween(1, 10);

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
        competitionRepository.saveCompetition(competition);
        return competition;
    }

    public Player createPlayer() throws Exception {
        OutputHandler.printGetNameMessage(UseCaseConstants.PLAYER);
        String playerName = inputHandler.getName(UseCaseConstants.PLAYER);
        int id = playerRepository.readAllPlayers().size();
        Player player = new Player(playerName, id);
        if (playerRepository.checkIfNameAlreadyExistsPlayer(playerName)) {
            OutputHandler.printInvalidInputMessagePlayerIsAlreadyInTheGame();
            return null;
        }
        playerRepository.savePlayer(player);
        return player;
    }

    public Player loadPlayer() throws Exception {
        OutputHandler.printLoadMessage(UseCaseConstants.PLAYER);
        List<Player> players = playerRepository.readAllPlayers();
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + ". " + players.get(i).getPlayerName());
        }
        int optionNr = inputHandler.getNumberBetween(1, players.size());
        Player player = playerRepository.loadPlayer(players.get(optionNr - 1).getId());
        return player;
    }

    public Player createOrLoadPlayer() throws Exception {
        Player player = null;
        while (player == null) {
            OutputHandler.printCreateOrLoadMessage(UseCaseConstants.PLAYER);
            int option = inputHandler.getNumberBetween(1, 2);
            if (option == 1) {
                player = createPlayer();
            } else {
                player = loadPlayer();
            }
        }
        return player;
    }

    public boolean playerIsAlreadyInTheCompetition(Player player, List<Player> playersList) {
        if (playersList.isEmpty()) {
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
        int matchRulesOption = inputHandler.getNumberBetween(1, 2);
        if (matchRulesOption == 1) {
            return new MatchRules(new LocalMatchStrategy());
        } else {
            return new MatchRules(new OriginalMatchStrategy());
        }
    }

    public CompetitionRules getCompetitionRules() {
        OutputHandler.printCompetitionRulesSelection();
        int competitionRulesOption = inputHandler.getNumberBetween(1, 2);

        CompetitionRules competitionRules = new CompetitionRules();
        int matchesToWin = 0;
        if(competitionRulesOption == 1) {
            OutputHandler.getMatchesToWin();
            matchesToWin = inputHandler.getNumberBetween(1, 10);
        }
        competitionRules.setCompetitionMode(competitionRulesOption, matchesToWin);

        return competitionRules;
    }
}
