package com.example.codename_ludos.UserInterface.Controllers;

import android.view.MotionEvent;

import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.UserInterface.Finger;
import com.example.codename_ludos.UserInterface.UIContainer;

public class Button extends Touchable {
    private boolean holding = false;
    private boolean released = false;
    public boolean selected = false;


    public Button(UIContainer parent, String ID, int x, int y, int width, int height) {
        super(parent, ID, x, y, width, height);
    }

    @Override
    public void update() {
        updatePos();
        for (Finger pos : MainActivity.gamePanel.getFingers()) {
            if(isTouched(pos)) {
                onTouch(pos.getX(), pos.getY());
                onHolding(pos.getX(), pos.getY());
                if (!holding && pos.getAction() != MotionEvent.ACTION_MOVE) {
                    onPressed(pos.getX(), pos.getY());
                }
                holding = true;
                released = false;
                break;
            } else if (fingerOverlap(pos) && pos.getAction() == MotionEvent.ACTION_UP) {
                onReleased(pos.getX(), pos.getY());
            } else {
                onLeft(pos.getX(), pos.getY());
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

    public void onLeft(float eventX, float eventY) {

    }

    public void onClick(float eventX, float eventY) {

    }
}
