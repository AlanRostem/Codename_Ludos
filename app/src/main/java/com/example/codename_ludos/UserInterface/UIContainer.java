package com.example.codename_ludos.UserInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class UIContainer extends UIElement {
    private ArrayList<UIElement> childContainer;
    private HashMap<String, UIElement> childMap;

    public UIContainer(String id, float x, float y, int width, int height) {
        super(id, x, y, width, height);
        childContainer = new ArrayList<>();
        childMap = new HashMap<>();
    }

    public UIElement get(String id) {
        return childMap.get(id);
    }

    public void append(String id, UIElement element) {
        childContainer.add(element);
        childMap.put(id, element);
    }

    public void remove(String id) {
        childContainer.remove(childMap.get(id));
        childMap.remove(id);
    }

    @Override
    public void draw() {
        for (UIElement u : childContainer) {
            u.draw();
        }
    }

    @Override
    public void update() {
        for (UIElement u : childContainer) {
            u.update();
        }
    }
}
