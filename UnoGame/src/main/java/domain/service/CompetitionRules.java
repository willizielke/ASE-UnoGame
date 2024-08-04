package domain.service;

public class CompetitionRules {
    private boolean isMatchWinsMode;
    private boolean isLeastPointsMode;
    private int maxPoints;
    private int matchesToWin;
    public boolean isMatchWinsMode() {
        return isMatchWinsMode;
    }
    public void setMatchWinsMode(boolean isMatchWinsMode) {
        this.isMatchWinsMode = isMatchWinsMode;
    }
    public boolean isLeastPointsMode() {
        return isLeastPointsMode;
    }
    public void setLeastPointsMode(boolean isLeastPointsMode) {
        this.isLeastPointsMode = isLeastPointsMode;
    }
    public int getMaxPoints() {
        return maxPoints;
    }
    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }
    public int getMatchesToWin() {
        return matchesToWin;
    }
    public void setMatchesToWin(int matchesToWin) {
        this.matchesToWin = matchesToWin;
    }
}
