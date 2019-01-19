package logic;


import input.InputManager;

public class Player extends Entity {


    public Player(Vector center, float radius) {
        super();
        this.center = center;
        this.radius = radius;

    }

    @Override
    public void update(InputManager inputManager, GameState gameState) {
        super.update(inputManager, gameState);
        this.cSpeed = this.cSpeed.add(new Vector(10f, 0));
    }

    /**
     *Method to check if the two circles collided, by checking if the distance between the centers of the two circles is
     *more or less than the sum of the two radii
     */
    public boolean overlaps(Player other) {
        if ( Math.abs(center.x - other.center.x) > radius + other.radius ) {
            return false;
        } else if ( Math.abs(center.y - other.center.y) > radius + other.radius ) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * If two entities collide
     */
    public void collisionControl(Player player) {

       cSpeed.x = 0;
       cSpeed.y = 0;

    }
}