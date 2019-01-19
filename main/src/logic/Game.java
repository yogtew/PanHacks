package logic;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import graphics.Renderer;
import input.InputManager;
import network.NetworkManager;

public class Game extends JFrame {
    private Renderer renderer;
    private GameState gameState;
    private NetworkManager networkManager;
    private InputManager inputManager;
    private boolean isServer;
    private int id;
    private Logger logger;

    public Game(Logger logger) {
        this.logger = logger;
        init();
    }

    public void initUI() {

       add(renderer);
       setTitle("PanHacks Game");
       setSize(1500, 1200);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Core gameplay loop, calls the update method of all entities inside GameState
     */
    public void update() throws InterruptedException {
        // gets latest inputs from clients
        // inputManager.updateInputs(networkManager.getInputs());
        gameState.update(inputManager);
        if (isServer) {
            networkManager.push(gameState);
        }


        Thread.sleep(15);
        update();
    }

    public void init() {
        id = generateId();
        gameState = new GameState();
        renderer = new Renderer(gameState);
        networkManager = new NetworkManager();
        initUI();
        logger.log(Level.INFO, "Successfully initialized with id " + id);
    }

    public void start() {
        try {
            update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int generateId() {
        return new Random().nextInt();
    }
}
