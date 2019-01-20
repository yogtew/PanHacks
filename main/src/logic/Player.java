package logic;


import graphics.Wall;
import input.InputManager;


public class Player extends Entity {

    public int id;

    public Player(Vector center, float radius, int id) {
        super();
        this.position = center;
        this.radius = radius;
        this.id = id;

        InputManager.initialize(id);
    }

    @Override
    public void update(InputManager inputManager, GameState gameState) {
        super.update(inputManager, gameState);

        if (InputManager.getKey("up", id)) {
            this.cSpeed.y -= 5;
        }

        if (InputManager.getKey("down", id)) {
            this.cSpeed.y += 5;
        }

        if (InputManager.getKey("left", id)) {
            this.cSpeed.x -= 5;
        }

        if (InputManager.getKey("right", id)) {
            this.cSpeed.x += 5;
        }

        //in each frame, check for collision for each player against all other players
        for (Player p : gameState.getPlayers()) {
            for (Player p1 : gameState.getPlayers()) {
                if (p != p1) { //not ownself
                    p.collisionControl(p1);
                }
            }
        }

        for (Wall w : gameState.getWalls()) {
            Vector delta = w.position.sub(this.position);
            if (Math.abs(delta.x) + Math.abs(delta.y) < this.radius + w.radius) {


            }
        }
    }



    /**
     *Method to check if the two circles collided, by checking if the distance between the centers of the two circles is
     *more or less than the sum of the two radii
     */
    public boolean overlaps(Player other) {
        if ( Math.abs(position.x - other.position.x) > radius + other.radius ) {
            return false;
        } else if ( Math.abs(position.y - other.position.y) > radius + other.radius ) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Calculating distance between two centres of two players
     */
    public double distBtwnCenters (Player other) {

        double xdiff = this.position.x - other.position.x;
        double ydiff = this.position.y - other.position.y;

        return Math.sqrt( ( xdiff * xdiff ) + ( ydiff * ydiff ) );
    }

    /**
     * Get vector connecting two centres
     */
    public Vector vecBtwnCenters (Player other) {
        Vector center1 = this.position;
        Vector center2 = other.position;

        return center2.sub(center1);
    }

    /**
     * If two entities collide
     */
    public void collisionControl(Player other) {

       if (this.distBtwnCenters(other) < this.radius) { //the dist between centers<radius of circle
           Vector recoveryVector = this.vecBtwnCenters(other); //one player move in direction if this vector, other opp
           this.position = this.position.sub(recoveryVector.mulConst(0.3f));
           other.position = other.position.add(recoveryVector.mulConst(0.3f));

       }
    }
}