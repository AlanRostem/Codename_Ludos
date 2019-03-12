package com.example.codename_ludos.Assets.Graphics;

import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.LibraryTools.Constants;

public class Text {

    public static void draw(String string, int color, float fontSize, float x, float y) {
        GamePanel.paint.setColor(color);
        GamePanel.paint.setTextSize(fontSize);
        MainThread.canvas.drawText(string,
                x * Constants.SCREEN_SCALE_X,
                (y + fontSize) * Constants.SCREEN_SCALE_Y,
                GamePanel.paint);
    }
}
