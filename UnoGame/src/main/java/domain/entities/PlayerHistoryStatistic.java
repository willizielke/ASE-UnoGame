package domain.entities;

public class PlayerHistoryStatistic {
    private int competitionCount;
    private int competitionWinCount;
    private int matchCount;
    private int matchWinCount;
    private int matchLoseCount;
    private int accumulatedPoints;
    private int playerId;
    private double pointsPerMatch;

    public PlayerHistoryStatistic() {
        this.competitionCount = 0;
        this.competitionWinCount = 0;
        this.matchCount = 0;
        this.matchWinCount = 0;
        this.matchLoseCount = 0;
        this.accumulatedPoints = 0;
        this.pointsPerMatch = 0;
    }   
}
