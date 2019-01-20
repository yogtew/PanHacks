import javax.swing.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import graphics.Maze;
import graphics.Renderer;
import input.GameKeyListener;
import logic.GameState;
import logic.Time;
import network.NetworkClient;

public class GameClient extends JFrame {
    private Renderer renderer;
    private GameState gameState;
    private NetworkClient networkManager;
    private int id;
    private Logger logger;
    private GameKeyListener gameKeyListener;

    public GameClient() {
        logger = Logger.getGlobal();
        gameKeyListener = GameKeyListener.getSingleton();
        init();
    }

    public static void main(String[] args) {
        GameClient gameClient = new GameClient();
        gameClient.init();
    }

    public void initUI() {
        add(renderer);
        setTitle("PanHacks Game");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Repaints frame
     */
    public void update() throws InterruptedException {
        renderer.repaint();
        Thread.sleep(15);
        update();
    }

    public void init() {
        id = generateId();
        networkManager = new NetworkClient(id, "localhost", 3000);
        System.exit(1);


        gameState = new GameState(Maze.generateGrid());
        renderer = new Renderer(gameState);
        renderer.addKeyListener(gameKeyListener);
        renderer.setFocusable(true);

        initUI();
        Time.lastTime = System.currentTimeMillis();
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
