package input;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import events.EventsCenter;
import events.KeyPressedEvent;
import events.KeyReleasedEvent;

/**
 * Stores the movement states (true or false) of each movement direction (up, down, left, right)
 * for each of the four players.
 */
public class InputManager {

    private static InputManager singleton;
    private HashMap<Integer, HashMap<String, Boolean>> playerInputs;

    private InputManager() {
        EventsCenter.getSingleton().registerHandler(this);
        playerInputs = new HashMap<>();
        /*for (int i = 0; i < 4; i++) {
            HashMap<String, Boolean> hmap = new HashMap<>();
            hmap.put("up", false);
            hmap.put("down", false);
            hmap.put("left", false);
            hmap.put("right", false);
            // playerInputs.add(hmap);
        }*/
    }

    public static InputManager getSingleton() {
        if (singleton == null) {
            singleton = new InputManager();
        }
        return singleton;
    }

    public static boolean getKey(String key, int id) {
        if (getSingleton().playerInputs.containsKey(id) && getSingleton().playerInputs.get(id).containsKey(key)) {
            return getSingleton().playerInputs.get(id).get(key);
        }
        return false;
    }

    public static void initialize(int id) {
        HashMap<String, Boolean> hmap = new HashMap<>();
        hmap.put("up", false);
        hmap.put("down", false);
        hmap.put("left", false);
        hmap.put("right", false);
        getSingleton().playerInputs.put(id, hmap);
    }

    /*public ArrayList<HashMap<String, Boolean>> getPlayerInputs() {
        return playerInputs;
    }*/

    public void setPlayerMovement(int playerNum, String movementDirection, boolean movementState) {
        playerInputs.get(playerNum).replace(movementDirection, movementState);
    }

    public GameKeyListener getInput(String key) {
        return GameKeyListener.getSingleton();
    }

    @Subscribe
    public void updateKeyPress(KeyPressedEvent e) {
        char c = e.getKeyEvent().getKeyChar();

        switch (c) {
            case 'w':
                playerInputs.get(1).put("up", true);
                break;
            case 'a':
                playerInputs.get(1).put("left", true);
                break;
            case 's':
                playerInputs.get(1).put("down", true);
                break;
            case 'd':
                playerInputs.get(1).put("right", true);
                break;
        }
    }

    @Subscribe
    public void updateKeyRelease(KeyReleasedEvent e) {
        char c = e.getKeyEvent().getKeyChar();
        switch (c) {
            case 'w':
                playerInputs.get(1).put("up", false);
                break;
            case 'a':
                playerInputs.get(1).put("left", false);
                break;
            case 's':
                playerInputs.get(1).put("down", false);
                break;
            case 'd':
                playerInputs.get(1).put("right", false);
                break;
        }
    }

}
