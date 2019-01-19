package network;

import input.GameKeyListener;
import logic.GameState;

public class NetworkManager {

    public GameKeyListener getInputs() {
        return new GameKeyListener();
    }

    public void push(GameState gameState) {
    }
}
