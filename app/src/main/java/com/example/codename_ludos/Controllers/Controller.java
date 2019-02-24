package com.example.codename_ludos.Controllers;

import android.view.MotionEvent;

import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.Input.Finger;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class Controller extends Vector2D {
    // Subclass for buttons, joysticks, etc.

    private int width;
    private int height;

    public Controller(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void onTouch(float eventX, float eventY) {

    }

    public Vector2D getTouchPosition() {
        return MainActivity.gamePanel.getTouchPosition();
    }

    public boolean isTouched(Finger pos) {
        float sx = Constants.SCREEN_SCALE_X;
        float sy = Constants.SCREEN_SCALE_Y;
        return pos.getY() > this.getY() * sy
                &&  pos.getY() < (this.getY() + this.height) * sy
                && pos.getX() > this.getX() * sx
                &&  pos.getX() < (this.getX() + this.width) * sx
                && pos.isDown();
    }

    public void update() {
        for (Finger pos : MainActivity.gamePanel.getFingers()) {
            if (isTouched(pos)) {
                onTouch(pos.getX(), pos.getY());
            }
        }
    }

    public void draw() {

    }
}