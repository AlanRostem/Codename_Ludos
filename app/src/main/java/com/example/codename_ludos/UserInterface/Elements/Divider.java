package com.example.codename_ludos.UserInterface.Elements;

import com.example.codename_ludos.UserInterface.UIContainer;
import com.example.codename_ludos.UserInterface.UIElement;

public class Divider extends UIContainer {
    public Divider(UIContainer parent, String id, float x, float y, int width, int height) {
        super(parent, id, x, y, width, height);
    }

    @Override
    public void update() {
        for (UIElement u : childContainer) {
            u.update();
            if (u.getPos().x + u.getWidth() > pos.x + width) {
                u.setX(pos.x + width - u.getWidth());
            }
            if (u.getPos().x < pos.x) {
                u.setX(pos.x);
            }

            if (u.getPos().y + u.getHeight() > pos.y + height) {
                u.setY(pos.y + height - u.getHeight());
            }
            if (u.getPos().y < pos.y) {
                u.setY(pos.y);
            }
        }
    }
}
