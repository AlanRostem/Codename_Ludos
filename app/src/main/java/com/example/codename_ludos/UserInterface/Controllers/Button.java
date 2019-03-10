package com.example.codename_ludos.UserInterface.Controllers;

import android.view.MotionEvent;

import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.UserInterface.Finger;
import com.example.codename_ludos.UserInterface.UIContainer;

public class Button extends Touchable {
    private int holding = 0;

    public Button(UIContainer parent, String ID, int x, int y, int width, int height) {
        super(parent, ID, x, y, width, height);
    }

    @Override
    public void update() {
        for (Finger pos : MainActivity.gamePanel.getFingers()) {
            if (isTouched(pos)) {
                onTouch(pos.getX(), pos.getY());
                onHolding(pos.getX(), pos.getY());
                if (holding < 1 && pos.getAction() != MotionEvent.ACTION_MOVE) {
                    onPressed(pos.getX(), pos.getY());
                }
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
