package logic;

import input.InputManager;

public class Entity {

    public logic.Vector cOldSpeed;
    public logic.Vector cSpeed;

    public Vector center;
    public float radius;

    public Entity() {
        cSpeed = Vector.zero;
        center = Vector.zero;
        radius = 0;
    }

    public void update(InputManager inputManager) {
        // update position


        // physics stuff

        // collision detection
        updatePhysics();
    }

    /**
     *Updates the position
     */
    public void updatePhysics() {
        cOldSpeed = cSpeed;
        Vector dist = cSpeed.mulConst(Time.deltaTime);
        center = center.add(dist); //updating circle's center += speed*deltaTime, updating circle's new position
    }

    public void draw() {
        // draw a circle
    }

}
