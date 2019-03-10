package com.example.codename_ludos.UserInterface;

import com.example.codename_ludos.LibraryTools.Math.Vector2D;

import java.util.ArrayList;

public class UIElement {

    protected Vector2D pos;
    protected UIElement parent;
    protected String ID;
    protected int width;
    protected int height;

    public UIElement(UIContainer parent, String id, float x, float y, int width, int height) {
        pos = new Vector2D(parent.pos.x + x, parent.pos.y + y);
        this.parent = parent;
        ID = id;
        this.width = width;
        this.height = height;
    }

    public UIElement(String id, float x, float y, int width, int height) {
        pos = new Vector2D(x, y);
        ID = id;
        this.width = width;
        this.height = height;
    }

    public UIElement getParent() {
        return parent;
    }

    public Vector2D getPos() {
        return pos;
    }

    public String getID() {
        return ID;
    }

    public boolean hasParent() {
        return parent == null;
    }

    public void draw() {

    }

    public void update() {

    }
}
