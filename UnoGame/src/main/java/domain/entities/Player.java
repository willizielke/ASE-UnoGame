package domain.entities;

public class Player {
    private String playerName;
    private PlayerHistoryData playerStats;
    private int id;

    public Player() {
    	this.playerStats= new PlayerHistoryData();
    }

    public Player(String playerName, int id) {
        this.playerName = playerName;
        this.id = id;
        this.playerStats = new PlayerHistoryData();
    }

    public int getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public PlayerHistoryData getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(PlayerHistoryData playerStats) {
        this.playerStats = playerStats;
    }
}
