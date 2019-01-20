package logic;

public class Bullet extends Entity {

    private final int BULLET_SPEED = 500;
    private final int FRAME_WIDTH = 1200;

    protected int x;
    protected int y;
    protected boolean visible;


    public Bullet(Vector center, float radius) {
        super();
        this.center = center;
        this.radius = radius;
        visible = true;
    }

    public void shoot() {

        x += BULLET_SPEED;

        if (x > FRAME_WIDTH || x < 0) {
            visible = false;
        }
    }

}
