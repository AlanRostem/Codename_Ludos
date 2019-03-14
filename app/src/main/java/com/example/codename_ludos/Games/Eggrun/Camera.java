package com.example.codename_ludos.Games.Eggrun;

import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class Camera {
    private Vector2D subject;

    public float x;
    public float y;

    public Camera(Vector2D subject) {
        this.subject = subject;
    }

    public void update(float offsetX, float offsetY) {
        if (subject.x < offsetX) {
            x = -(x - offsetX);
        }
    }

}
