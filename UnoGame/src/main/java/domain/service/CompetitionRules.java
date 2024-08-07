package domain.service;

import presentation.InputHandler;
import presentation.OutputHandler;

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

    public void setCompetitionMode(int mode) {
        if (mode == 1) {
            this.isMatchWinsMode = true;
            this.isLeastPointsMode = false;
            this.maxPoints = 0;
            OutputHandler.getMatchesToWin();
            this.matchesToWin = InputHandler.getNumberBetween(1, 10);
        } else {
            this.isLeastPointsMode = true;
            this.isMatchWinsMode = false;
            this.matchesToWin = 0;
            this.maxPoints = 101;
        }
    }

}
