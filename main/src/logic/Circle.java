package logic;


public class Circle {

    public Vector center;
    public Vector radius;

    public Circle(Vector center, Vector radius) {

        this.center = center;
        this.radius = radius;

    }

    /*
    Method to check if the two circles collided, by checking if the distance between the centers of the two circles is
    more or less than the sum of the two radii
     */
    public boolean Overlaps(Circle other) {
        if ( Math.abs(center.x - other.center.x) > radius.x + other.radius.x ) {
            return false;
        } else if ( Math.abs(center.y - other.center.y) > radius.y + other.radius.y ) {
            return false;
        } else {
            return true;
        }
    }


}