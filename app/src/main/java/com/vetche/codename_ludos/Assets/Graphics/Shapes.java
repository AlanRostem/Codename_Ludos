package com.vetche.codename_ludos.Assets.Graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

import com.vetche.codename_ludos.Core.GamePanel;
import com.vetche.codename_ludos.Core.MainThread;
import com.vetche.codename_ludos.LibraryTools.Constants;
import com.vetche.codename_ludos.LibraryTools.Math.Vector2D;

public class Shapes {

    private static Rect rect = new Rect(0, 0, 0, 0);

    public static void setColor(int color) {
        GamePanel.paint.setColor(color);
    }

    public static void drawRect(float x, float y, int width, int height) {
        rect.set((int)(x * Constants.SCREEN_SCALE_X),
                (int)(y * Constants.SCREEN_SCALE_Y),
                (int)((width + x) * Constants.SCREEN_SCALE_X),
                (int)((height + y) * Constants.SCREEN_SCALE_Y));
        if (MainThread.canvas != null)
            MainThread.canvas.drawRect(rect, GamePanel.paint);
    }

    private static Path path = new Path();

    public static void drawPoly(float x, float y, Vector2D[] array) {
        if (MainThread.canvas != null) {
            Canvas ctx = MainThread.canvas;
            float sx = Constants.SCREEN_SCALE_X;
            float sy = Constants.SCREEN_SCALE_Y;
            float ox = x * Constants.SCREEN_SCALE_X;
            float oy = y * Constants.SCREEN_SCALE_Y;

            GamePanel.paint.setStyle(Paint.Style.FILL);
            for (int i = 0; i < array.length; i += 2) {
                ctx.drawLine(ox + array[i].x * sx, oy + array[i].y * sy,
                        ox + array[i + 1].x * sx, oy + array[i + 1].y * sy,
                        GamePanel.paint);
            }
            ctx.drawPath(path, GamePanel.paint);
        }
    }
}
