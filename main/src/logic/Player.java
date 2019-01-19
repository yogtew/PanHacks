package logic;


public class Player extends Entity {

    public Vector cOldPosition;
    public Vector cPosition;

    public Vector cOldSpeed;
    public Vector cSpeed;

    public Vector center;
    public Vector radius;

    public Player(Vector center, Vector radius) {

        this.center = center;
        this.radius = radius;

    }

    /**
     *Method to check if the two circles collided, by checking if the distance between the centers of the two circles is
     *more or less than the sum of the two radii
     */
    public boolean Overlaps(Player other) {
        if ( Math.abs(center.x - other.center.x) > radius.x + other.radius.x ) {
            return false;
        } else if ( Math.abs(center.y - other.center.y) > radius.y + other.radius.y ) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *Updates the position
     */
    public void UpdatePhysics() {

        cOldPosition = cPosition;
        cOldSpeed = cSpeed;

        Vector dist = cSpeed.mulConst(Time.deltaTime);
        cPosition = cPosition.add(dist); //newPosition += speed*deltaTime, updating circle's new position
        Vector center = this.center;
        center = center.add(dist); //updating circle's center

    }


}