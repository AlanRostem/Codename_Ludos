package com.example.codename_ludos.Core;

import android.content.Context;
import android.content.res.Resources;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.LibraryTools.BitmapHelper;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.R;
import com.example.codename_ludos.RectPlayer;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    // Class that handles all rendering in the app
    public static Paint paint;
    private MainThread thread;

    private RectPlayer player;
    private Point playerPoint;
    private SpriteMap playerImage;
    private SpriteMap.Animation playerAnim;

    private SpriteMap arcadeImage;


    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        setLayerType(LAYER_TYPE_HARDWARE, paint);
        Constants.CURRENT_CONTEXT = context;
        paint = new Paint();
    }

    @Override
    public void surfaceChanged(SurfaceHolder sHolder, int format, int width, int height) {
        Constants.SCREEN_HEIGHT = height;
        Constants.SCREEN_WIDTH = width;
    }

    @Override
    public void surfaceCreated(SurfaceHolder sHolder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
        // TESTS:
        playerPoint = new Point(150, 150);
        player = new RectPlayer(new Rect(100, 100, 200, 200),
                Color.rgb(0, 250, 255));

        playerImage = new SpriteMap(R.drawable.rubigo, 384, 96);
        playerImage.bindSprite("a1", 0, 0, 48, 48);
        playerAnim = new SpriteMap.
                Animation(0, 7, 8, 0.1f);

        arcadeImage = new SpriteMap(R.drawable.maskin, 1080, 1920);
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder sHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {e.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    playerPoint.set((int)event.getX() + 100, (int)event.getY() - 50);
                    break;
        }
        return true;
    }

    public void update() {
        player.update(playerPoint);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLACK); //Background
        Log.i("DeltaTime", "" + MainThread.getDeltaTime());
        playerImage.Animate("a1", playerAnim);
        playerImage.drawAt("a1", playerPoint.x - 48*2, playerPoint.y - 48*2, 48*4, 48*4);
        if (arcadeImage != null) {
            arcadeImage.drawFull(0, 0);
        }
    }
}
