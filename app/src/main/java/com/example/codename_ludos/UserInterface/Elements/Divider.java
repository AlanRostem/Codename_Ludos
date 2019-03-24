package com.example.codename_ludos.UserInterface.Elements;

import com.example.codename_ludos.UserInterface.UIContainer;
import com.example.codename_ludos.UserInterface.UIElement;

public class Divider extends UIContainer {
    public Divider(UIContainer parent, String id, float x, float y, int width, int height) {
        super(parent, id, x, y, width, height);
    }

    @Override
    public void update() {
        updatePos();
        for (UIElement u : childContainer) {
            u.update();
        }
    }
}
