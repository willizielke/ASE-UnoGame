package application.usecase;

import java.util.ArrayList;
import java.util.List;

import domain.entities.Deck;
import domain.entities.DeckBuilder;
import domain.entities.FastMatch;
import domain.entities.Match;
import domain.entities.Player;
import domain.entities.PlayerWithCards;
import domain.repositories.PlayerRepository;
import domain.service.LocalMatchStrategy;
import domain.service.MatchRules;
import domain.service.OriginalMatchStrategy;
import domain.valueobjects.Card;
import presentation.InputHandler;
import presentation.OutputHandler;

public class MatchCreationManager {
    private PlayerRepository playerRepository;
    private InputHandler inputHandler;

    public MatchCreationManager(PlayerRepository playerRepository, InputHandler inputHandler) {
        this.playerRepository = playerRepository;
        this.inputHandler = inputHandler;
    }

    public Match createFastMatch() {
        OutputHandler.printGetPlayerCountMessage();
        int playerCount = inputHandler.getNumberBetween(2, 10);
        return new FastMatch(playerCount);
    }

    public Match createMatch() throws Exception {
        OutputHandler.printGetPlayerCountMessage();
        Match match = new Match();

        int playerCount = inputHandler.getNumberBetween(1, 10);
        List<PlayerWithCards> playersWithCardsList = new ArrayList<PlayerWithCards>();
        Deck deck = DeckBuilder.createDeck();
        for (int i = 1; i <= playerCount; i++) {
            Player player = null;
            boolean playerIsAlreadyInTheMatch = false;
            do {
                OutputHandler.printSlash(i, playerCount);
                player = createOrLoadPlayer();
                playerIsAlreadyInTheMatch = playerIsAlreadyInTheMatch(player, playersWithCardsList);
                if (playerIsAlreadyInTheMatch) {
                    OutputHandler.printInvalidInputMessagePlayerIsAlreadyInTheGame();
                }
            } while (playerIsAlreadyInTheMatch);
            playersWithCardsList.add(
                    new PlayerWithCards(player, new ArrayList<Card>()));
            for (int j = 0; j < 7; j++) {
                playersWithCardsList.get(i - 1).addCard(deck.getCards().remove(0));
            }
        }
        MatchRules matchRules = getMatchRules();
        match.setPlayersWithCardsList(playersWithCardsList);
        match.setDeck(deck);
        match.setMatchRules(matchRules);
        match.setPlayedCards(new ArrayList<Card>());

        return match;
    }

    public Match createCompetitionMatch(List<Player> players, MatchRules matchRules) {
        Match match = new Match();
        List<PlayerWithCards> playersWithCardsList = new ArrayList<PlayerWithCards>();
        Deck deck = DeckBuilder.createDeck();
        for (int i = 0; i < players.size(); i++) {
            playersWithCardsList.add(
                    new PlayerWithCards(players.get(i), new ArrayList<Card>()));
            for (int j = 0; j < 7; j++) {
                playersWithCardsList.get(i).addCard(deck.getCards().remove(0));
            }
        }
        match.setPlayersWithCardsList(playersWithCardsList);
        match.setDeck(deck);
        match.setMatchRules(matchRules);
        match.setPlayedCards(new ArrayList<Card>());

        return match;
    }

    public Player createPlayer() throws Exception {
        OutputHandler.printGetNameMessage(UseCaseConstants.PLAYER);
        String playerName = inputHandler.getName(UseCaseConstants.PLAYER);
        int id = playerRepository.readAllPlayers().size();
        Player player = new Player(playerName, id);
        if(playerRepository.checkIfNameAlreadyExistsPlayer(playerName)) {
            OutputHandler.printInvalidInputMessageNameAlreadyExists(UseCaseConstants.PLAYER);
            return null;
        }
        playerRepository.savePlayer(player);
        return player;
    }

    public Player loadPlayer() throws Exception {
        OutputHandler.printLoadMessage(UseCaseConstants.PLAYER);
        List<Player> players = playerRepository.readAllPlayers();
        if(players.size() == 0) {
            OutputHandler.printNotInDBMessage(UseCaseConstants.PLAYER);
            return null;
        }
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

    public boolean playerIsAlreadyInTheMatch(Player player, List<PlayerWithCards> playersWithCardsList) {
        for (PlayerWithCards playerWithCards : playersWithCardsList) {
            if (playerWithCards.getPlayer().getId() == player.getId()) {
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
}
