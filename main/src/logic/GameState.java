package logic;

import input.InputManager;

import java.util.ArrayList;

public class GameState {
    private ArrayList<Player> players;
    GameState() {
        players = new ArrayList<>();
        Player player1 = new Player(new Vector(100, 100), 100);
        players.add(new Player(new Vector(100, 100), 100));
        players.add(new Player(new Vector(300, 300), 100));
    }

    void update(InputManager inputManager, GameState gameState) {
        for (Player p: players) {
            p.update(inputManager, this);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
