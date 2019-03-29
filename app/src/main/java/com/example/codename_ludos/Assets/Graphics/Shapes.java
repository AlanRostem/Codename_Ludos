package com.example.codename_ludos.Assets.Graphics;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;

import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class Shapes {

    private static Rect rect = new Rect(0, 0, 0, 0);
    private static Path path = new Path();

    public static void setColor(int color) {
        GamePanel.paint.setColor(color);
    }

    public static void drawRect(float x, float y, int width, int height) {
        rect.set((int)(x * Constants.SCREEN_SCALE_X),
                (int)(y * Constants.SCREEN_SCALE_Y),
                (int)((width + x) * Constants.SCREEN_SCALE_X),
                (int)((height + y) * Constants.SCREEN_SCALE_Y));
        MainThread.canvas.drawRect(rect, GamePanel.paint);
    }

    public static void drawPoly(float x, float y, Vector2D[] array) {
        Canvas ctx = MainThread.canvas;
        float sx = Constants.SCREEN_SCALE_X;
        float sy = Constants.SCREEN_SCALE_Y;
        float ox = x * Constants.SCREEN_SCALE_X;
        float oy = y * Constants.SCREEN_SCALE_Y;
        path.moveTo(ox + array[0].x * sx, oy + array[0].y * sy);
        path.lineTo(ox + array[1].x * sx, oy + array[1].y * sy);
        for (int i = 2; i < array.length; i+=2) {
            path.lineTo(ox + array[i].x * sx, oy + array[i].y * sy);
            path.lineTo(ox + array[i+1].x * sx, oy + array[i+1].y * sy);
        }
        ctx.drawPath(path, GamePanel.paint);
    }
}
