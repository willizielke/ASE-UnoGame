package domain.entities;

public class Player {
    private String playerName;
    private PlayerHistoryStatistic playerStats;
    private int id;

    public Player(String playerName, int id) {
        this.playerName = playerName;
        this.id = id;
        this.playerStats = new PlayerHistoryStatistic();
    }

    public int getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }
}
