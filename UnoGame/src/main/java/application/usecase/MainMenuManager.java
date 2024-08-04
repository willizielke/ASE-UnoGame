package application.usecase;

import java.io.IOException;
import java.util.List;

import application.interfaces.IDataPersistence;
import common.GlobalConstants;
import common.GlobalMethods;
import common.Messages;
import domain.entities.Competition;
import domain.entities.Match;
import presentation.input.InputHandler;

public class MainMenuManager {

    private IDataPersistence dbService;

    public MainMenuManager(IDataPersistence dbService) {
        this.dbService = dbService;
    }

    public void StartApplication() throws IOException {
        int mainOptionNr = 0;
        while (mainOptionNr != 5) {
            GlobalMethods.clearConsole();
            Messages.printMainMenuSelection();
            mainOptionNr = InputHandler.getNumberBetween(1, 5);;
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

    public void StartMatchMenu() throws IOException {
        GlobalMethods.clearConsole();
        Messages.printMatchViewSelection();
        int matchOptionNr = InputHandler.getNumberBetween(1, 3);
        MatchCreationManager matchCreationManager= new MatchCreationManager(dbService);
        MatchProcessManager matchProcessManager= new MatchProcessManager(dbService);
        if (matchOptionNr == 1) {
            Match match= matchCreationManager.createFastMatch();
            matchProcessManager.startMatch(match);
        } else if (matchOptionNr == 2) {
            Match match = matchCreationManager.createMatch();
            matchProcessManager.startMatch(match);
        } else if (matchOptionNr == 3) {
            return;
        }
    }

    public void StartCompetitionMenu() throws IOException {
        GlobalMethods.clearConsole();
        Messages.printCompetitionViewSelection();
        int competitionOptionNr = InputHandler.getNumberBetween(1, 3);
        Competition competition = new Competition();
        if (competitionOptionNr == 1) {
            competition = new CompetitionCreationManager(dbService).createCompetition();
            if (competition == null) {
                return;
            }
            new CompetitionProcessManager(dbService).startCompetition(competition);
        } else if (competitionOptionNr == 2) {
            competition = loadCompetition();
            if (competition == null) {
                return;
            }
        } else if (competitionOptionNr == 3) {
            return;
        }
    }

    public Competition loadCompetition() throws IOException {
        List<Competition> competitions = dbService.readAllCompetitions();
        if (competitions.size() == 0) {
            Messages.printNotInDBMessage(GlobalConstants.COMPETITION);
            return null;
        }
        Messages.printLoadMessage(GlobalConstants.COMPETITION);
        for (int i = 0; i < competitions.size(); i++) {
            if (competitions.get(i).getWinner().isEmpty()) {
                System.out.println((i + 1) + ". " + competitions.get(i).getName());
            }
        }
        int optionNr = InputHandler.getNumberBetween(1, competitions.size());
        Competition competition = dbService.loadCompetition(competitions.get(optionNr - 1).getName());
        return competition;
    }

    public void StartHistoryMenu() throws IOException {
        GlobalMethods.clearConsole();
        new HistoryManager(dbService).printHistory();
    }
}
