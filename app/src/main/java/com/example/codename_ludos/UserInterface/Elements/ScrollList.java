package com.example.codename_ludos.UserInterface.Elements;

import android.view.MotionEvent;

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

    public boolean oneFingerOverlap(Finger pos) {
        float sx = Constants.SCREEN_SCALE_X;
        float sy = Constants.SCREEN_SCALE_Y;
        return pos.getY() > this.outPutPos.y * sy
                &&  pos.getY() < (this.outPutPos.y + this.height) * sy
                && pos.getX() > this.outPutPos.x * sx
                &&  pos.getX() < (this.outPutPos.x + this.width) * sx
                && pos.getAction() == MotionEvent.ACTION_MOVE;
    }

    @Override
    public void append(String id, UIElement element) {
        div.append(id, element);
    }

    boolean fingOnScreen = false;
    boolean canScroll = true;
    Vector2D startPos = new Vector2D(-1, -1);

    public void setScroll(boolean val) {
        canScroll = val;
    }

    @Override
    public ArrayList<UIElement> getChildNodes() {
        return div.getChildNodes();
    }

    @Override
    public Vector2D getOutPutPos() {
        return div.getOutPutPos();
    }

    float ypos;
    float p;

    @Override
    public void update() {
        updatePos();
        Finger f = MainActivity.gamePanel.getFingers()[0];
        if (oneFingerOverlap(f) && canScroll) {
            if (!fingOnScreen) {
                ypos = div.getOutPutPos().y;
                startPos.set(f.x, f.y);
                fingOnScreen = true;
            }
            p = ypos + f.y - startPos.y;
            div.setPos(0,  p);
        } else {
            fingOnScreen = false;
        }

        div.update();
    }

    @Override
    public void draw() {
        div.draw();
        //Shapes.setColor(Color.argb(0.5f, 1f,1f,1f));
        //Shapes.drawRect(this.outPutPos.x, this.outPutPos.y, this.width, this.height);
    }
}
