package logic;

import graphics.Wall;
import input.InputManager;

import java.util.ArrayList;

public class GameState {
    private ArrayList<Player> players;
    private ArrayList<Wall> walls;
    public GameState(ArrayList<Wall> walls) {
        players = new ArrayList<>();
        this.walls = walls;

        //when player joins game must be added to the player arraylist

        //for testing
        players.add(new Player(new Vector(100, 100), 32, 1));
        players.add(new Player(new Vector(300, 300), 32, 2));
    }

    public void update(InputManager inputManager) {
        for (Player p: players) {
            p.update(inputManager, this);
        }

        for (Wall w: walls) {
            w.update(inputManager, this);
        }
    }

    public String serialize() {
        return "";
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Iterable<? extends Wall> getWalls() {
        return walls;
    }
}
