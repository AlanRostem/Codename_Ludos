package com.example.codename_ludos.Assets;

import android.graphics.Rect;

import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.LibraryTools.Constants;

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
        MainThread.canvas.drawRect(rect, GamePanel.paint);
    }
}
