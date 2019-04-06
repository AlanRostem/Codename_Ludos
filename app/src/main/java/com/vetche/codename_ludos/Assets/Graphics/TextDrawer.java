package com.vetche.codename_ludos.Assets.Graphics;

import com.vetche.codename_ludos.Core.GamePanel;
import com.vetche.codename_ludos.Core.MainThread;
import com.vetche.codename_ludos.LibraryTools.Constants;

public class TextDrawer {

    public static void draw(String string, int color, float fontSize, float x, float y) {
        GamePanel.paint.setColor(color);
        GamePanel.paint.setTextSize(fontSize);
        if (MainThread.canvas != null)
            MainThread.canvas.drawText(string,
                x * Constants.SCREEN_SCALE_X,
                (y + fontSize) * Constants.SCREEN_SCALE_Y,
                GamePanel.paint);
    }
}
