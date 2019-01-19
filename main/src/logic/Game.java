package logic;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import graphics.Renderer;
import input.InputManager;
import network.NetworkManager;

public class Game {
    private Renderer renderer;
    private GameState gameState;
    private NetworkManager networkManager;
    private InputManager inputManager;
    private boolean isServer;
    private int id;
    private Logger logger;

    public Game(Logger logger) {
        this.logger = logger;
    }

    /**
     * Core gameplay loop, calls the update method of all entities inside GameState
     */
    public void update() {
        // gets latest inputs from clients
        inputManager.updateInputs(networkManager.getInputs());
        gameState.update();
        renderer.draw(gameState);
        if (isServer) {
            networkManager.push(gameState);
        }

    }

    public void init() {
        id = generateId();
        gameState = new GameState();
        renderer = new Renderer();
        networkManager = new NetworkManager();
        logger.log(Level.INFO, "Successfully initialized with id " + id);
    }

    private int generateId() {
        return new Random().nextInt();
    }
}
