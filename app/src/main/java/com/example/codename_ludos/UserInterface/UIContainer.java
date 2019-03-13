package com.example.codename_ludos.UserInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class UIContainer extends UIElement {
    protected ArrayList<UIElement> childContainer;
    protected HashMap<String, UIElement> childMap;

    public UIContainer(String id, float x, float y, int width, int height) {
        super(id, x, y, width, height);
        childContainer = new ArrayList<>();
        childMap = new HashMap<>();
    }

    public UIContainer(UIContainer parent, String id, float x, float y, int width, int height) {
        super(parent, id, x, y, width, height);
        childContainer = new ArrayList<>();
        childMap = new HashMap<>();
    }

    public void append(String id, UIElement element) {
        childContainer.add(element);
        childMap.put(id, element);
        element.setParent(this);
    }

    public void remove(String id) {
        childContainer.remove(childMap.get(id));
        childMap.remove(id);
    }

    public UIElement getElementByID(String id) {
        return childMap.get(id);
    }

    public ArrayList<UIElement> getChildNodes() {
        return childContainer;
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
