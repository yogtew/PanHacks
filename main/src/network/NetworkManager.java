package network;

import input.GameKeyListener;
import logic.GameState;

public class NetworkManager {

    public GameKeyListener getInputs() {
        return GameKeyListener.getSingleton();
    }

    public void push(GameState gameState) {
    }
}
