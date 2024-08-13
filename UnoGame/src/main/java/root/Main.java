package root;

import com.fasterxml.jackson.databind.ObjectMapper;

import application.usecase.MainMenuManager;
import domain.repositories.CompetitionRepository;
import domain.repositories.MatchStrategyRepository;
import domain.repositories.PlayerRepository;
import infrastructure.FileCompetitionRepository;
import infrastructure.FileMatchStrategyRepository;
import infrastructure.FilePlayerRepository;
import presentation.InputHandler;

public class Main {
    private static final String filePath = "C:\\Users\\wzielke\\Desktop\\Ase-UnoGame\\UnoGame\\src\\file_db\\";
    private static final String filePathPlayer = filePath + "players.json";
    private static final String filePathCompetition = filePath + "competitions.json";
    private static final String filePathMatchStrategy = filePath + "matchStrategies.json";

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        PlayerRepository playerRepository = new FilePlayerRepository(objectMapper, filePathPlayer);
        CompetitionRepository competitionRepository = new FileCompetitionRepository(objectMapper, filePathCompetition);
        MatchStrategyRepository matchStrategyRepository = new FileMatchStrategyRepository(objectMapper,
                filePathMatchStrategy);
        InputHandler inputHandler = new InputHandler();
        
        MainMenuManager mainMenuManager = new MainMenuManager(playerRepository, competitionRepository,
                matchStrategyRepository, inputHandler);
        mainMenuManager.StartApplication();
    }
}
