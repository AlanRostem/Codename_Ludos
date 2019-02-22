package com.example.codename_ludos.Controllers;

import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.Input.Finger;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

import java.util.Iterator;

public class Button extends Controller {
    private boolean holding = false;

    public Button(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        for (Finger pos : MainActivity.gamePanel.getFingers()) {
            if (isTouched(pos)) {
                if (!holding) {
                    onPressed(pos.getX(), pos.getY());
                }
                onTouch(pos.getX(), pos.getY());
                onHolding(pos.getX(), pos.getY());
                holding = true;
                break;
            } else {
                onReleased(pos.getX(), pos.getY());
                holding = false;
            }
        }
    }

    public void onHolding(float eventX, float eventY) {

    }

    public void onPressed(float eventX, float eventY) {

    }

    public void onReleased(float eventX, float eventY) {

    }
}
