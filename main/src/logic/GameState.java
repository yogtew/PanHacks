package logic;

import java.util.ArrayList;

import input.InputManager;

public class GameState {
    private ArrayList<Player> players;
    void update(InputManager inputManager) {
        for (Player p: players) {
            p.update(inputManager);
        }
    }
}
