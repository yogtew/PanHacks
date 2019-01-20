import java.util.logging.Level;
import java.util.logging.Logger;

import graphics.Maze;
import input.InputManager;
import logic.GameState;
import logic.Time;
import network.NetworkServer;

public class GameServer {
    private GameState gameState;
    private NetworkServer networkServer;
    private InputManager inputManager;
    private Logger logger;

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.init();
        gameServer.start();
    }

    public void init() {
        logger = Logger.getGlobal();

        gameState = new GameState(Maze.generateGrid());
        networkServer = new NetworkServer(gameState);
        Thread networkThread = new Thread(networkServer);
        logger.log(Level.INFO, "Successfully initialized network server instance");
        networkThread.start();
        Time.lastTime = System.currentTimeMillis();
        logger = Logger.getGlobal();
        logger.log(Level.INFO, "Successfully initialized server");
    }

    public void start() {
        try {
            update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Core gameplay loop, calls the update method of all entities inside GameState
     */
    public void update() throws InterruptedException {
        // gets latest inputs from clients
        // inputManager.updateInputs(networkServer.getInputs());
        Time.deltaTime = (System.currentTimeMillis() - Time.lastTime)/1000f;
        gameState.update(inputManager);

        Time.lastTime = System.currentTimeMillis();
        Thread.sleep(15);
        update();
    }
}
