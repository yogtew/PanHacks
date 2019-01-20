package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import graphics.Wall;
import input.InputManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class GameState {
    private ArrayList<Player> players;
    private transient ArrayList<Wall> walls;
    private ArrayList<Bullet> bullets;
    public GameState(ArrayList<Wall> walls) {
        players = new ArrayList<>();
        this.walls = walls;
        bullets = new ArrayList<>();

        //when player joins game must be added to the player arraylist

        //for testing
        // players.add(new Player(new Vector(100, 100), 32, 1));
        // players.add(new Player(new Vector(300, 300), 32, 2));
    }

    public void registerPlayer(int id) {
        players.add(new Player(new Vector(new Random().nextFloat() * 1000, 100), 32, id));
    }


    public void update(InputManager inputManager) {
        for (Player p: players) {
            p.update(inputManager, this);
        }

        for (Wall w: walls) {
            w.update(inputManager, this);
        }

        for (Bullet b: bullets) {
            b.update(inputManager, this);
        }

    }

    public String serialize() {
        Container container = new Container(players, bullets);
        return new Gson().toJson(container);
    }

    public void apply(String newState) {
        Container c = new Gson().fromJson(newState, Container.class);
        players = c.players;
        bullets = c.bullets;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Iterable<? extends Wall> getWalls() {
        return walls;
    }

    public ArrayList<Bullet> getBullets() { return bullets;}

    public void spawnBullet(Vector speed, Vector center) {
        Bullet bullet = new Bullet(speed, center);
        bullets.add(bullet);
    }

}

class Container {
    public ArrayList<Player> players;
    public ArrayList<Bullet> bullets;
    public Container(ArrayList<Player> players, ArrayList<Bullet> bullets) {
        this.players = players;
        this.bullets = bullets;
    }
}
