package domain.entities;

public class PlayerCurrentCompetitionData {
    private Player player;
    private int matchWinCount;
    private int accumulatedPoints;
    private int timesPointsReseted;

    public PlayerCurrentCompetitionData() {
    }

    public PlayerCurrentCompetitionData(Player player) {
        this.player = player;
        this.matchWinCount = 0;
        this.accumulatedPoints = 0;
        this.timesPointsReseted = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getMatchWinCount() {
        return matchWinCount;
    }

    public void setMatchWinCount(int matchWinCount) {
        this.matchWinCount = matchWinCount;
    }

    public int getAccumulatedPoints() {
        return accumulatedPoints;
    }

    public void setAccumulatedPoints(int accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
    }

    public int getTimesPointsReseted() {
        return timesPointsReseted;
    }

    public void setTimesPointsReseted(int timesPointsReseted) {
        this.timesPointsReseted = timesPointsReseted;
    }

}
