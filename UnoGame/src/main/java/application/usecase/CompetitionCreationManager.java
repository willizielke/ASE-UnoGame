package application.usecase;

import domain.entities.Competition;

public class CompetitionCreationManager {
    public Competition createCompetition() {
        return new Competition();
    }
}
