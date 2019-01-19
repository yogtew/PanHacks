package input;

public class InputManager {
    public GameKeyListener getInput(String key) {
        return GameKeyListener.getSingleton();
    }

    public void updateInputs(GameKeyListener gameKeyListener) {

    }

}
