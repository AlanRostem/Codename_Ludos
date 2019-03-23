package com.example.codename_ludos.UserInterface.Controllers;

import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.UserInterface.Finger;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.UserInterface.UIContainer;

public class Touchable extends UIContainer {
    // Subclass for buttons, joysticks, etc.

    private int width;
    private int height;

    public Touchable(UIContainer parent, String ID, float x, float y, int width, int height) {
        super(parent, ID, x, y, width, height);
        this.width = width;
        this.height = height;
    }

    public float getX() { return outPutPos.x; }

    public float getY() { return outPutPos.y; }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void onTouch(float eventX, float eventY) {

    }

    public boolean fingerOverlap(Finger pos) {
        float sx = Constants.SCREEN_SCALE_X;
        float sy = Constants.SCREEN_SCALE_Y;
        return pos.getY() > this.getY() * sy
                &&  pos.getY() < (this.getY() + this.height) * sy
                && pos.getX() > this.getX() * sx
                &&  pos.getX() < (this.getX() + this.width) * sx;
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
        updatePos();
        for (Finger pos : MainActivity.gamePanel.getFingers()) {
            if (isTouched(pos)) {
                onTouch(pos.getX(), pos.getY());
            }
        }
    }

    public void draw() {

    }
}