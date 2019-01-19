package logic;

import input.InputManager;

import java.util.ArrayList;

public class Entity {

    public Vector cSpeed;

    public Vector center;
    public float radius;

    public Entity() {
        cSpeed = Vector.zero;
        center = Vector.zero;
        radius = 0;
    }

    public void update(InputManager inputManager, GameState gameState) {
        // update positions
        updatePhysics();
        // check if collision occurs
       /* ArrayList<Player> players = gameState.getPlayers();
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        if (player1.overlaps(player2)) {
            player1.collisionControl(player2);
        }*/
    }

    /**
     * Updates the position
     */
    public void updatePhysics() {
        Vector dist = cSpeed.mulConst(Time.deltaTime);
        center = center.add(dist); //updating circle's center += speed*deltaTime, updating circle's new position
    }

    public void draw() {
        // draw a circle
    }

}
