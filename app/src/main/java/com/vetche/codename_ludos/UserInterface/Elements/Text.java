package com.vetche.codename_ludos.UserInterface.Elements;

import android.graphics.Color;
import android.graphics.Rect;

import com.vetche.codename_ludos.Assets.Graphics.TextDrawer;
import com.vetche.codename_ludos.Core.GamePanel;
import com.vetche.codename_ludos.UserInterface.UIContainer;
import com.vetche.codename_ludos.UserInterface.UIElement;

public class Text extends UIElement {
    private String diplayText = "";
    private float fontSize;
    private int color = Color.WHITE;
    private Rect bounds = new Rect();

    public Text(UIContainer parent, String id, String diplayText, float x, float y, float fontSize) {
        super(parent, id, x, y, 0, 0);
        this.diplayText = diplayText;
        this.fontSize = fontSize;
    }

    public Text(UIContainer parent) {
        super(parent, "", 0, 0, 0, 0);
        this.diplayText = "";
        this.fontSize = 60;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void draw() {
        TextDrawer.draw(diplayText, color, fontSize, outPutPos.x, outPutPos.y);
        GamePanel.paint.getTextBounds(diplayText, 0, diplayText.length(), bounds);
        width = bounds.right;
        height = (int)fontSize;
    }
}
