package com.example.codename_ludos.UserInterface.Elements;

import android.graphics.Color;
import android.util.Log;

import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.UserInterface.Finger;
import com.example.codename_ludos.UserInterface.UIContainer;
import com.example.codename_ludos.UserInterface.UIElement;

import java.util.ArrayList;

public class ScrollList extends UIContainer {
    private Divider div;

    public ScrollList(UIContainer parent, String id, float x, float y, int width, int height) {
        super(parent, id, x, y, width, height);
        div = new Divider(this, "container", x, y, width, height);
    }

    public ScrollList(String id, float x, float y, int width, int height) {
        super(id, x, y, width, height);
        div = new Divider(this, "container", x, y, width, height);
    }

    private float elementDistance;

    public boolean oneFingerOverlap(Finger pos) {
        float sx = Constants.SCREEN_SCALE_X;
        float sy = Constants.SCREEN_SCALE_Y;
        return pos.getY() > this.outPutPos.y * sy
                &&  pos.getY() < (this.outPutPos.y + this.height) * sy
                && pos.getX() > this.outPutPos.x * sx
                &&  pos.getX() < (this.outPutPos.x + this.width) * sx
                && pos.isDown();
    }

    @Override
    public void append(String id, UIElement element) {
        div.append(id, element);
    }

    boolean fingOnScreen = false;
    boolean canScroll = true;
    Vector2D startPos = new Vector2D(-1, -1);

    @Override
    public ArrayList<UIElement> getChildNodes() {
        return div.getChildNodes();
    }

    @Override
    public Vector2D getOutPutPos() {
        return div.getOutPutPos();
    }

    @Override
    public void update() {
        updatePos();
        for (Finger f : MainActivity.gamePanel.getFingers()) {
            if (oneFingerOverlap(f)) {
                Log.i("LudosLog", "" + startPos.x + "," + startPos.y);
                if (!fingOnScreen) {
                    startPos.set(f.x, f.y);
                    div.setPos(div.getOutPutPos().x, (f.y - startPos.y));
                    fingOnScreen = true;
                }
            } else {
                fingOnScreen = false;
            }
        }

        div.update();
    }

    public float getElementDistance() {
        return elementDistance;
    }

    public void setElementDistance(float elementDistance) {
        this.elementDistance = elementDistance;
    }

    @Override
    public void draw() {
        div.draw();
        Shapes.setColor(Color.argb(0.5f, 1f,1f,1f));
        Shapes.drawRect(this.outPutPos.x, this.outPutPos.y, this.width, this.height);
    }
}
