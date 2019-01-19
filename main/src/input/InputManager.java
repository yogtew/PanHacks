package input;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores the movement states (true or false) of each movement direction (up, down, left, right)
 * for each of the four players.
 */
public class InputManager {

    private static InputManager singleton;
    private ArrayList<HashMap<String, Boolean>> playerInputs;

    private InputManager() {
        playerInputs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            HashMap<String, Boolean> hmap = new HashMap<>();
            hmap.put("up", false);
            hmap.put("down", false);
            hmap.put("left", false);
            hmap.put("right", false);
            playerInputs.add(hmap);
        }
    }

    public static InputManager getSingleton() {
        if (singleton == null) {
            singleton = new InputManager();
        }
        return singleton;
    }

    public ArrayList<HashMap<String, Boolean>> getPlayerInputs() {
        return playerInputs;
    }

    public void setPlayerMovement(int playerNum, String movementDirection, boolean movementState) {
        playerInputs.get(playerNum).replace(movementDirection, movementState);
    }

    public GameKeyListener getInput(String key) {
        return GameKeyListener.getSingleton();
    }

}
