package com.example.codename_ludos.UserInterface;

import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class UIElement {

    protected Vector2D outPutPos;
    protected Vector2D initialPos;
    protected UIContainer parent;
    protected String ID;
    protected int width;
    protected int height;

    float padding = 0;
    float margin = 0;

    public UIElement(UIContainer parent, String id, float x, float y, int width, int height) {
        initialPos = new Vector2D(x, y);
        outPutPos = new Vector2D(parent.outPutPos.x + x, parent.outPutPos.y + y);
        this.parent = parent;
        ID = id;
        this.width = width;
        this.height = height;
    }

    public UIElement(String id, float x, float y, int width, int height) {
        initialPos = new Vector2D(x, y);
        outPutPos = new Vector2D(x, y);
        ID = id;
        this.width = width;
        this.height = height;
    }

    public UIElement getParent() {
        return parent;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Vector2D getOutPutPos() {
        return outPutPos;
    }

    public String getID() {
        return ID;
    }

    public boolean hasParent() {
        return parent == null;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(float x) {
        outPutPos.x = x + margin;
    }

    public void setY(float y) {
        outPutPos.y = y + margin;
    }

    public void setParent(UIContainer parent) {
        this.parent = parent;
    }

    public void setPos(float x, float y) {
        if (hasParent()) {
            outPutPos.x = parent.outPutPos.x + parent.padding + x + margin;
            outPutPos.y = parent.outPutPos.y + parent.padding + y + margin;
            return;
        }
        outPutPos.x = x + margin;
        outPutPos.y = y + margin;
    }

    public float getMargin() {
        return margin;
    }

    public float getPadding() {
        return padding;
    }

    public void setMargin(float margin) {
        this.margin = margin;
    }

    public void setPadding(float padding) {
        this.padding = padding;
    }

    public void draw() {

    }

    public void update() {

    }
}
