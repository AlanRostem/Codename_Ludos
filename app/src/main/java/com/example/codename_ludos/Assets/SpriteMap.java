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

    public static class Animation {
        //Initializable values
        private int start_col; //Start of row
        private int end_col; //End of row
        private int frames_per_row; //Amount of frames per row
        private float frame_speed; //Frame speed in seconds

        //Non-init values
        private int current_col = start_col;
        private int current_row = 0;
        private float passed_time = 0;
        private boolean reverse = false;
        private boolean stop = false;

        public Animation(int startColumn, int endColumn,int framesPerRow, float frameSpeedInSeconds) {
            this.start_col = startColumn;
            this.end_col = endColumn;
            this.frames_per_row = framesPerRow;
            this.frame_speed = frameSpeedInSeconds;
        }
    }

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
        offsetRects.put("full", new Rect(0, 0, width, height));
    }

    // Bind a location on the sprite sheet to a name
    public void bindSprite(String name, int offsetX, int offsetY, int frameWidth, int frameHeight) {
        offsetRects.put(name, new Rect(offsetX, offsetY, frameWidth + offsetX, frameHeight + offsetY));
    }

    public void Animate(String name, Animation anim) {
        if ((anim.passed_time += MainThread.getAverageDeltaTime()) >= anim.frame_speed)
        {
            if (anim.current_col < anim.end_col)
            {
                anim.current_col++;
            }
            else
            {
                anim.current_col = anim.start_col;
            }
            anim.passed_time = 0;
        }
        int width = offsetRects.get(name).right - offsetRects.get(name).left;
        int height = offsetRects.get(name).bottom - offsetRects.get(name).top;

        offsetRects.get(name).left = (width * (anim.current_col % anim.frames_per_row));
        offsetRects.get(name).top = (height * (anim.current_col / anim.frames_per_row));

        offsetRects.get(name).right = offsetRects.get(name).left + width;
        offsetRects.get(name).bottom = offsetRects.get(name).top + height;
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
