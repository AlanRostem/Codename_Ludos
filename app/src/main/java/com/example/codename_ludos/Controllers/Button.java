package com.example.codename_ludos.Controllers;

import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class Button extends Controller {
    private int holding = 0;

    public Button(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        Vector2D pos = getTouchPosition();
        if (isTouched()) {
            if (holding < 1) {
                onPressed(pos.getX(), pos.getY());
            }
            onTouch(pos.getX(), pos.getY());
            onHolding(pos.getX(), pos.getY());
            holding++;
        } else {
            onReleased(pos.getX(), pos.getY());
            holding = 0;
        }
    }

    public void onHolding(float eventX, float eventY) {

    }

    public void onPressed(float eventX, float eventY) {

    }

    public void onReleased(float eventX, float eventY) {

    }
}
