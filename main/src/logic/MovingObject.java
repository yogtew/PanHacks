package logic;

public class MovingObject {

    public Vector cOldPosition;
    public Vector cPosition;

    public Vector cOldSpeed;
    public Vector cSpeed;

    public Circle circle;


    public void UpdatePhysics() {

        cOldPosition = cPosition;
        cOldSpeed = cSpeed;

        Vector dist = cSpeed.mulConst(Time.deltaTime);
        cPosition = cPosition.add(dist); //newPosition += speed*deltaTime, updating circle's new position
        Vector center = circle.center;
        center = center.add(dist); //updating circle's center


    }


}