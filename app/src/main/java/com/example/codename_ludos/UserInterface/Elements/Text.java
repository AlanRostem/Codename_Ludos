package com.example.codename_ludos.UserInterface.Elements;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import com.example.codename_ludos.Assets.Graphics.TextDrawer;
import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.UserInterface.UIContainer;
import com.example.codename_ludos.UserInterface.UIElement;

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
        TextDrawer.draw(diplayText, color, fontSize, pos.x, pos.y);
        GamePanel.paint.getTextBounds(diplayText, 0, diplayText.length(), bounds);
        width = bounds.right;
        height = (int)fontSize;
    }
}
