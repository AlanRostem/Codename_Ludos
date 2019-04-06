package com.vetche.codename_ludos.UserInterface;

import android.util.Log;

import com.vetche.codename_ludos.LibraryTools.Math.Vector2D;

public class UIElement {

    protected Vector2D outPutPos;
    protected Vector2D initialPos;
    protected Vector2D offsetPos;
    protected UIContainer parent;
    protected String ID;
    protected int width;
    protected int height;

    float padding = 0;
    float margin = 0;

    public UIElement(UIContainer parent, String id, float x, float y, int width, int height) {
        initialPos = new Vector2D(x, y);
        outPutPos = new Vector2D(parent.outPutPos.x + x, parent.outPutPos.y + y);
        offsetPos = new Vector2D(0, 0);
        this.parent = parent;
        ID = id;
        this.width = width;
        this.height = height;
    }

    public UIElement(String id, float x, float y, int width, int height) {
        initialPos = new Vector2D(x, y);
        outPutPos = new Vector2D(x, y);
        offsetPos = new Vector2D(0, 0);
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
        return parent != null;
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

    public void setOffsetPos(float x, float y) {
        this.offsetPos.x = x;
        this.offsetPos.y = y;
        Log.i("LudosLog", "" + offsetPos + " : (" + x + ", " + y + ")");
    }

    public Vector2D getOffsetPos() {
        return offsetPos;
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

    public void updatePos() {
        try {
            offsetPos.set(parent.getOutPutPos().x, parent.getOutPutPos().y);
        } catch (Exception e) {}
        outPutPos.x = initialPos.x + offsetPos.x;
        outPutPos.y = initialPos.y + offsetPos.y;
    }

    public void update() {

    }
}
