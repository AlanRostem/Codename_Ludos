package com.example.codename_ludos.Assets;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.LibraryTools.BitmapHelper;

public class SpriteMap {

    private int resourceID;
    private Bitmap bitmap;


    public SpriteMap(int resourceID, int width, int height) {
        this.resourceID = resourceID;
        bitmap = BitmapHelper.decodeResource(MainActivity.gamePanel.getResources(), this.resourceID);
        // For some stupid reason we gotta do this in order to make the sprite work but hey who cares
        // Java fucking sucks anyway and we prolly wont ever make no cash from this waste of stupid
        // ass nigger fuckery ass fucktardian cock of a sun bucker nugget fag suck fuck!
        bitmap = BitmapHelper.resizeBitmap(bitmap, width, height);
    }

    public void drawFull(float x, float y) {
        if (MainThread.canvas == null)
            return;
        MainThread.canvas.drawBitmap(this.bitmap, (int)x, (int)y, GamePanel.paint);
    }
}
