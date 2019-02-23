package com.example.codename_ludos.Controllers;

import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.Input.Finger;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

import java.util.Iterator;

public class Button extends Controller {
    private int holding = 0;

    public Button(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        for (Finger pos : MainActivity.gamePanel.getFingers()) {
            if (isTouched(pos)) {
                if (holding < 1) {
                    onPressed(pos.getX(), pos.getY());
                }
                onTouch(pos.getX(), pos.getY());
                onHolding(pos.getX(), pos.getY());
                holding++;
                break;
            } else {
                onReleased(pos.getX(), pos.getY());
                holding = 0;
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
