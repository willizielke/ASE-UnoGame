package domain.entities;

public class PlayerHistoryData {
    private int competitionCount;
    private int competitionWinCount;
    private int matchCount;
    private int matchWinCount;
    private int matchLoseCount;
    private int accumulatedPoints;
    private double pointsPerMatch;

    public PlayerHistoryData() {
        this.competitionCount = 0;
        this.competitionWinCount = 0;
        this.matchCount = 0;
        this.matchWinCount = 0;
        this.matchLoseCount = 0;
        this.accumulatedPoints = 0;
        this.pointsPerMatch = 0;
    }

    public int getCompetitionCount() {
        return competitionCount;
    }

    public void setCompetitionCount(int competitionCount) {
        this.competitionCount = competitionCount;
    }

    public int getCompetitionWinCount() {
        return competitionWinCount;
    }

    public void setCompetitionWinCount(int competitionWinCount) {
        this.competitionWinCount = competitionWinCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    public int getMatchWinCount() {
        return matchWinCount;
    }

    public void setMatchWinCount(int matchWinCount) {
        this.matchWinCount = matchWinCount;
    }

    public int getMatchLoseCount() {
        return matchLoseCount;
    }

    public void setMatchLoseCount(int matchLoseCount) {
        this.matchLoseCount = matchLoseCount;
    }

    public int getAccumulatedPoints() {
        return accumulatedPoints;
    }

    public void setAccumulatedPoints(int accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
    }

    public double getPointsPerMatch() {
        return pointsPerMatch;
    }

    public void setPointsPerMatch(double pointsPerMatch) {
        this.pointsPerMatch = pointsPerMatch;
    }
       
}
