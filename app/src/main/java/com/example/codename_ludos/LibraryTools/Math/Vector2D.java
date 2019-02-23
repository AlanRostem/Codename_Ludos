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

}
