package com.example.codename_ludos.LibraryTools.Math;

public class Vector2D {
    private float x;
    private float y;

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

    public Vector2D set(float  x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

}
