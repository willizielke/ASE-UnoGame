package application;

import java.io.IOException;

import application.interfaces.IDataPersistence;
import application.usecase.MainMenuManager;
import infrastructure.FileDBService;

public class Main {
    public static void main(String[] args) throws IOException {
        IDataPersistence dbService = new FileDBService(); // Create an instance of FileDBService
        MainMenuManager mainMenuManager = new MainMenuManager(dbService);
        mainMenuManager.StartApplication();
    }
}
