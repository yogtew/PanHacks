package logic;

import java.util.ArrayList;

import input.InputManager;

public class GameState {
    private ArrayList<Player> players;
    GameState() {
        players = new ArrayList<>();
        Player player1 = new Player(new Vector(0, 0), new Vector(0, 0));
    }

    void update(InputManager inputManager) {
        for (Player p: players) {
            p.update(inputManager);
        }
    }
}
