package com.example.codename_ludos;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class RectPlayer implements GameEntity {
    private Rect rectangle;
    private int color;
    private Paint paint = new Paint();


    public RectPlayer(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }

    public void update(Point point) {
        rectangle.set(point.x - rectangle.width() / 2,
                      point.y - rectangle.height() / 2,
                        point.x + rectangle.width() / 2,
                      point.y + rectangle.height() / 2);
    }

}
