package BaseballSimulator;

import java.util.ArrayList;

public class Team {
    private final String name;
    ArrayList<Player> players = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addPlayer(Player player) {
        if(uniquePosition(player)) {
            players.add(player);
        }
    }

    public Player getPlayer(int i) {
        return players.get(i);
    }

    private boolean uniquePosition(Player p) {
        for(Player player : players) {
            if(player.getPosition() == p.getPosition()) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }
}