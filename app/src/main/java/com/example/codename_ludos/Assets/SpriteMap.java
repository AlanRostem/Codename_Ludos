package com.example.codename_ludos.Assets;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.LibraryTools.BitmapHelper;

import java.util.HashMap;
import java.util.Map;

public class SpriteMap {

    private int resourceID;
    private Bitmap bitmap;
    private Map<String, Rect> offsetRects;
    private Rect positionRect;

    public SpriteMap(int resourceID, int width, int height) {
        this.resourceID = resourceID;
        offsetRects = new HashMap<>();
        bitmap = BitmapHelper.decodeResource(MainActivity.gamePanel.getResources(), this.resourceID);
        // For some stupid reason we gotta do this in order to make the sprite work but hey who cares
        // Java fucking sucks anyway and we prolly wont ever make no cash from this waste of stupid
        // ass nigger fuckery ass fucktardian cock of a sun bucker nugget fag suck fuck!
        bitmap = BitmapHelper.resizeBitmap(bitmap, width, height);
        positionRect = new Rect(0, 0, width, height);
    }

    // Bind a location on the sprite sheet to a name
    public void bindSprite(String name, int offsetX, int offsetY, int frameWidth, int frameHeight) {
        offsetRects.put(name, new Rect(offsetX, offsetY, frameWidth + offsetX, frameHeight + offsetY));
    }

    public void drawAt(String name, int x, int y, int width, int height) {
        positionRect.set(x, y, width + x, height + y);
        MainThread.canvas.drawBitmap(bitmap, offsetRects.get(name), positionRect, GamePanel.paint);
    }

    public void drawFull(float x, float y) {
        if (MainThread.canvas == null)
            return;
        MainThread.canvas.drawBitmap(this.bitmap, (int)x, (int)y, GamePanel.paint);
    }
}
