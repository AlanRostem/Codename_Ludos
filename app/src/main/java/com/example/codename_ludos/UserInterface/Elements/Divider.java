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
            if (u.getOutPutPos().x + u.getWidth() > outPutPos.x + width) {
                u.setX(outPutPos.x + width - u.getWidth());
            }
            if (u.getOutPutPos().x < outPutPos.x) {
                u.setX(outPutPos.x);
            }

            if (u.getOutPutPos().y + u.getHeight() > outPutPos.y + height) {
                u.setY(outPutPos.y + height - u.getHeight());
            }
            if (u.getOutPutPos().y < outPutPos.y) {
                u.setY(outPutPos.y);
            }
        }
    }
}
