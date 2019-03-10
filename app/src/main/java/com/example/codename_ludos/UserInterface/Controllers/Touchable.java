package com.example.codename_ludos.UserInterface.Controllers;

import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.UserInterface.Finger;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.UserInterface.UIContainer;
import com.example.codename_ludos.UserInterface.UIElement;

public class Touchable extends UIElement {
    // Subclass for buttons, joysticks, etc.

    private int width;
    private int height;

    public Touchable(UIContainer parent, String ID, int x, int y, int width, int height) {
        super(parent, ID, x, y, width, height);
        this.width = width;
        this.height = height;
    }

    public float getX() { return pos.x; }

    public float getY() { return pos.y; }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void onTouch(float eventX, float eventY) {

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