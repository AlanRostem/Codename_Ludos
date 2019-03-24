package com.example.codename_ludos.LibraryTools.Math;

public class Vector2D {
    public float x;
    public float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addX(float offsetX) {
        this.x += offsetX;
    }

    public void addY(float offsetY) {
        this.y += offsetY;
    }

    public void addVec(Vector2D vec){
        this.x += vec.x;
        this.y += vec.y;
    }

    public Vector2D set(float  x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public static boolean intersect(Vector2D a, Vector2D b, Vector2D c, Vector2D d)
    {
        Vector2D r = new Vector2D (b.x - a.x, b.y - a.y);
        Vector2D s = new Vector2D (d.x - c.x, d.y- c.y);

        float dd = r.x * s.y - r.y * s.x;
        float u = ((c.x - a.x) * r.y - (c.y - a.y) * r.x) / dd;
        float t = ((c.x - a.x) * s.y - (c.y - a.y) * s.x) / dd;
        return (0 < u && u < 1 && 0 < t && t < 1);
    }

    public static Vector2D getIntersectPos(Vector2D a, Vector2D b, Vector2D c, Vector2D d)
    {
        Vector2D r = new Vector2D (b.x - a.x, b.y - a.y);
        Vector2D s = new Vector2D (d.x - c.x, d.y- c.y);

        float dd = r.x * s.y - r.y * s.x;
        float u = ((c.x - a.x) * r.y - (c.y - a.y) * r.x) / dd;
        float t = ((c.x - a.x) * s.y - (c.y - a.y) * s.x) / dd;
        return new Vector2D(a.x + t * r.x, a.y + t * r.y);

    }

    //@androidx.annotation.NonNull
    @Override
    public String toString() {
        return "Vector2D(" + x + ", " + y + ")";
    }
}
