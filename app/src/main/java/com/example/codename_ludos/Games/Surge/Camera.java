package com.example.codename_ludos.Games.Surge;

import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class Camera extends Vector2D {
    public Camera(float x, float y) {
        super(x, y);
    }

    public void update(float x, float y, float offsetX, float offsetY) {
        if (y < offsetY) {
            this.y = 0;// -(y - offsetY);
        }
    }
}
