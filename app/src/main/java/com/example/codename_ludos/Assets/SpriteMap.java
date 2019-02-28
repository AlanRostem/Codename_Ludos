package com.example.codename_ludos.Assets;

import android.app.VoiceInteractor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainActivity;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Entity.TileMap;
import com.example.codename_ludos.LibraryTools.BitmapHelper;
import com.example.codename_ludos.LibraryTools.Constants;

import java.util.HashMap;
import java.util.Map;



public class SpriteMap {

    public static class Animation {
        // Object used as an instruction set for animating a sprite sheet

        //Init values
        private int start_col; //Start of row
        private int end_col; //End of row
        private int frames_per_row; //Amount of frames per row
        private float frame_speed; //Frame speed in seconds

        //Non-init values
        private int current_col = start_col;
        private int current_row = 0;
        private float passed_time = 0;
        private boolean reverse = false;
        private boolean paused = false;

        public void setReversed(boolean on) {
            reverse = on;
        }

        public void setPaused(boolean on) {
            paused = on;
        }

        public void setStartFrame(int frame) {
            start_col = frame;
        }

        public void setEndFrame(int frame) {
            end_col = frame;
        }

        public void setFramesPerRow(int frames) {
            frames_per_row = frames;
        }

        public void setFrameSpeed(float frameSpeedInSeconds) {
            frame_speed = frameSpeedInSeconds;
        }

        public Animation(int startFrame, int endFrame,int framesPerRow, float frameSpeedInSeconds) {
            this.start_col = startFrame;
            this.end_col = endFrame;
            this.frames_per_row = framesPerRow;
            this.frame_speed = frameSpeedInSeconds;
        }
    }

    private int resourceID;
    private Bitmap bitmap;
    private Map<String, Rect> offsetRects;
    private Rect positionRect;
    private Rect offsetTileRect;
    private BitmapFactory.Options options = new BitmapFactory.Options();

    private int imageWidth;
    private int imageHeight;

    public SpriteMap(int resourceID) {
        this.resourceID = resourceID;
        offsetRects = new HashMap<>();
        options.inScaled = false;

        bitmap = BitmapHelper.decodeResource(MainActivity.gamePanel.getResources(), this.resourceID, options);
        imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();
        //bitmap = BitmapHelper.resizeBitmap(bitmap, width, height);
        positionRect = new Rect(0, 0, 0, 0);
        offsetTileRect = new Rect(0, 0, 0, 0);
        offsetRects.put("full", new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()));
    }

    // Bind a location on the sprite sheet to a name
    public void bindSprite(String name, int offsetX, int offsetY, int frameWidth, int frameHeight) {
        offsetRects.put(name, new Rect(offsetX, offsetY, frameWidth + offsetX, frameHeight + offsetY));
    }

    public void Animate(String name, Animation anim) {
        if (!anim.paused)
            if ((anim.passed_time += MainThread.getAverageDeltaTime()) >= anim.frame_speed) {
                if (!anim.reverse) {
                    if (anim.current_col < anim.end_col)
                        anim.current_col++;
                    else
                        anim.current_col = anim.start_col;
                } else {
                    if (anim.current_col > anim.start_col)
                        anim.current_col--;
                    else
                        anim.current_col = anim.end_col;
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
        positionRect.set(
                (int)(x * Constants.SCREEN_SCALE_X),
                (int)(y * Constants.SCREEN_SCALE_Y),
                (int)((width + x) * Constants.SCREEN_SCALE_X),
                (int)((height + y) * Constants.SCREEN_SCALE_Y));
        MainThread.canvas.drawBitmap(bitmap, offsetRects.get(name), positionRect, GamePanel.paint);
    }

    public void drawFull(float x, float y) {
        if (MainThread.canvas == null)
            return;
        MainThread.canvas.drawBitmap(this.bitmap,
                (int)x * Constants.SCREEN_SCALE_X,
                (int)y * Constants.SCREEN_SCALE_Y,
                GamePanel.paint);
    }

    public void drawTileMap(TileMap map, int tilesPerRow, int offsetX, int offsetY) {
        int cols = imageWidth / map.getTileSize();
        int rows = imageHeight / map.getTileSize();
        int tileSize = map.getTileSize();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int tile = map.get(i).get(j);
                int tileRow = tile / tilesPerRow;
                int tileCol = tile % tilesPerRow;
                positionRect.set(
                        (int)(offsetX * Constants.SCREEN_SCALE_X),
                        (int)(offsetY * Constants.SCREEN_SCALE_Y),
                        (int)((map.getTileSize() + offsetX) * Constants.SCREEN_SCALE_X),
                        (int)((map.getTileSize() + offsetY) * Constants.SCREEN_SCALE_Y));
                offsetTileRect.set((tileCol * tileSize), (tileRow * tileSize), tileSize + (tileCol * tileSize),
                        tileSize + (tileRow * tileSize));
                MainThread.canvas.drawBitmap(bitmap, offsetTileRect, positionRect, GamePanel.paint);
            }
        }
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }
}
