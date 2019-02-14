package com.example.codename_ludos;

import android.graphics.Canvas;

public interface GameObject {
    // Subclass for objects that will be pushed into GameObjectManager
    boolean toRemove = false;
    public void draw(Canvas canvas);
    public void update();

}