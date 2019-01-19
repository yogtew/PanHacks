package logic;

import java.util.ArrayList;

import input.InputManager;

public class GameState {
    private ArrayList<Player> players;
    GameState() {
        players = new ArrayList<>();
        Player player1 = new Player(new Vector(100, 100), 100);
        players.add(player1);
    }

    void update(InputManager inputManager) {
        for (Player p: players) {
            p.update(inputManager);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
