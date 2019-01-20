package logic;

import input.InputManager;

public class Bullet extends Entity {

    public Vector bSpeed;

    public Bullet(Vector bSpeed, Vector center) {
        this.cSpeed = bSpeed;
        this.radius = 2;
        this.center = center;

    }

    @Override
    public void update(InputManager inputManager, GameState gameState) {
        super.update(inputManager, gameState);

    }
}
