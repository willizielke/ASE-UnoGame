package application.usecase;

import java.io.IOException;
import java.util.List;

import domain.entities.Competition;
import domain.entities.Match;
import domain.repositories.CompetitionRepository;
import domain.repositories.MatchStrategyRepository;
import domain.repositories.PlayerRepository;
import presentation.InputHandler;
import presentation.OutputHandler;

public class MainMenuManager {
    private PlayerRepository playerRepository;
    private CompetitionRepository competitionRepository;
    private InputHandler inputHandler;

    public MainMenuManager(PlayerRepository playerRepository, CompetitionRepository competitionRepository,
            MatchStrategyRepository matchStrategyRepository, InputHandler inputHandler) {
        this.playerRepository = playerRepository;
        this.competitionRepository = competitionRepository;
        this.inputHandler = inputHandler;
    }

    public void StartApplication() throws Exception {
        int mainOptionNr = 0;
        while (mainOptionNr != 5) {
            OutputHandler.clearConsole();
            OutputHandler.printMainMenuSelection();
            mainOptionNr = inputHandler.getNumberBetween(1, 5);
            ;
            switch (mainOptionNr) {
                case 1:
                    StartMatchMenu();
                    break;
                case 2:
                    StartCompetitionMenu();
                    break;
                case 3:
                    StartHistoryMenu();
                    break;
                case 4:
                    // StartSettingsMenu()
                    break;
                case 5:
                    // Exit
                    break;
                default:
                    break;
            }

        }
    }

    public void StartMatchMenu() throws Exception {
        OutputHandler.clearConsole();
        OutputHandler.printMatchViewSelection();
        int matchOptionNr = inputHandler.getNumberBetween(1, 3);
        MatchCreationManager matchCreationManager = new MatchCreationManager(playerRepository, inputHandler);
        MatchProcessManager matchProcessManager = new MatchProcessManager(playerRepository, inputHandler);
        if (matchOptionNr == 1) {
            Match match = matchCreationManager.createFastMatch();
            matchProcessManager.startMatch(match);
        } else if (matchOptionNr == 2) {
            Match match = matchCreationManager.createMatch();
            matchProcessManager.startMatch(match);
        } else if (matchOptionNr == 3) {
            return;
        }
    }

    public void StartCompetitionMenu() throws Exception {
        OutputHandler.clearConsole();
        OutputHandler.printCompetitionViewSelection();
        int competitionOptionNr = inputHandler.getNumberBetween(1, 3);
        Competition competition = new Competition();
        if (competitionOptionNr == 1) {
            competition = new CompetitionCreationManager(playerRepository, competitionRepository, inputHandler).createCompetition();
            if (competition == null) {
                return;
            }
            new CompetitionProcessManager(playerRepository, competitionRepository, inputHandler).startCompetition(competition);
        } else if (competitionOptionNr == 2) {
            competition = loadCompetition();
            if (competition == null) {
                return;
            }
            new CompetitionProcessManager(playerRepository, competitionRepository, inputHandler).startCompetition(competition);
        } else if (competitionOptionNr == 3) {
            return;
        }
    }

    public Competition loadCompetition() throws IOException {
        List<Competition> competitions = competitionRepository.readAllCompetitions();
        if (competitions.size() == 0) {
            OutputHandler.printNotInDBMessage(UseCaseConstants.COMPETITION);
            return null;
        }
        OutputHandler.printLoadMessage(UseCaseConstants.COMPETITION);
        for (int i = 0; i < competitions.size(); i++) {
            System.out.println((i + 1) + ". " + competitions.get(i).getName());
        }
        int optionNr = inputHandler.getNumberBetween(1, competitions.size());
        Competition competition = competitionRepository.loadCompetition(competitions.get(optionNr - 1).getName());
        return competition;
    }

    public void StartHistoryMenu() throws Exception {
        OutputHandler.clearConsole();
        new HistoryManager(playerRepository, inputHandler).printHistory();
    }
}
