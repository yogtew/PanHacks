package logic;

import java.util.*;

class Vector {
    float x;
    float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector vector) {
        float x = this.x + vector.x;
        float y = this.y + vector.y;
        return new Vector(x, y);
    }

    public Vector sub(Vector vector) {
        float x = this.x - vector.x;
        float y = this.y - vector.y;
        return new Vector(x, y);
    }

    public Vector mul(Vector vector) {

        float x = this.x * vector.x;
        float y = this.y * vector.y;
        //create new vector with updates coordinates and return
        return new Vector(x, y);
    }

    public Vector mulConst(float f) {
        float x = this.x * f;
        float y = this.y * f;
        return new Vector(x, y);
    }

    public Vector div(Vector vector) {
        float x = this.x / vector.x;
        float y = this.y / vector.y;
        return new Vector(x, y);
    }
}