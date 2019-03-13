package com.example.codename_ludos.UserInterface.Elements;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.UserInterface.Controllers.Touchable;
import com.example.codename_ludos.UserInterface.Finger;
import com.example.codename_ludos.UserInterface.UIContainer;
import com.example.codename_ludos.UserInterface.UIElement;

import java.util.ArrayList;

public class ScrollList extends UIContainer {
    public ScrollList(UIContainer parent, String id, float x, float y, int width, int height) {
        super(parent, id, x, y, width, height);
    }

    private float elementDistance;

    public boolean oneFingerOverlap(Finger pos) {
        float sx = Constants.SCREEN_SCALE_X;
        float sy = Constants.SCREEN_SCALE_Y;
        return pos.getY() > this.pos.y * sy
                &&  pos.getY() < (this.pos.y + this.height) * sy
                && pos.getX() > this.pos.x * sx
                &&  pos.getX() < (this.pos.x + this.width) * sx
                && pos.isDown();
    }


    boolean fingOnScreen = false;
    boolean canScroll = true;
    Vector2D startPos = new Vector2D(-1, -1);
    ArrayList<Float> oldPositions = new ArrayList<>();

    @Override
    public void update() {
        Finger f = MainActivity.gamePanel.getFingers()[0];
        if (oneFingerOverlap(f)) {
            if (!fingOnScreen) {
                startPos.set(f.x, f.y);
                fingOnScreen = true;
                for (UIElement u : childContainer) {
                    oldPositions.add(u.getPos().y);
                }
            } else {
                UIElement first = childContainer.get(0);
                UIElement last = childContainer.get(childContainer.size() - 1);

                int i = 0;
                for (UIElement u : childContainer) {
                    u.setY(oldPositions.get(i) + (f.y - startPos.y));
                    i++;
                }
                if (last.getPos().y + last.getHeight() < pos.y + height) {
                    last.setY(pos.y + height - last.getHeight());
                    if (first.getPos().y < pos.y)
                        try {
                            for (int j = childContainer.size() - 1; j > 0; j--) {
                            childContainer.get(j).setY(
                                    childContainer.get(j + 1).getPos().y -
                                            childContainer.get(j + 1).getHeight() -
                                            elementDistance);
                            }
                        } catch (Exception e) { }
                }
                if (first.getPos().y > pos.y) {
                    first.setY(pos.y);
                    if (last.getPos().y + last.getHeight() > pos.y + height)
                        for (int j = 1; j < childContainer.size(); j++) {
                            childContainer.get(j).setY(
                                    childContainer.get(j-1).getPos().y +
                                            childContainer.get(j-1).getHeight() +
                                            elementDistance);
                        }
                }
            }
        } else {
            fingOnScreen = false;
            oldPositions.clear();
        }

        for (UIElement e : childContainer) {
            e.update();
        }
    }

    public float getElementDistance() {
        return elementDistance;
    }

    public void setElementDistance(float elementDistance) {
        this.elementDistance = elementDistance;
    }

    @Override
    public void draw() {
        super.draw();
        //Shapes.setColor(Color.argb(0.5f, 1f,1f,1f));
        //Shapes.drawRect(this.pos.x, this.pos.y, this.width, this.height);
    }
}
