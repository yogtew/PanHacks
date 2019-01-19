package logic;


import input.InputManager;

public class Player extends Entity {



    public Player(Vector center, float radius) {

        this.center = center;
        this.radius = radius;

    }

    @Override
    public void update(InputManager inputManager) {
        super.update(inputManager);
        this.cSpeed = this.cSpeed.add(new Vector(10f, 0));
    }

    /**
     *Method to check if the two circles collided, by checking if the distance between the centers of the two circles is
     *more or less than the sum of the two radii
     */
    public boolean Overlaps(Player other) {
        if ( Math.abs(center.x - other.center.x) > radius + other.radius ) {
            return false;
        } else if ( Math.abs(center.y - other.center.y) > radius + other.radius ) {
            return false;
        } else {
            return true;
        }
    }
}