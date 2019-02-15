package com.example.codename_ludos.Core;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
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
    private RectPlayer player;
    private Point playerPoint;
    public static Paint paint;
    private SpriteMap arcadeImage;
    private MainThread thread;

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
                Color.rgb(0, 250, 0));

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
                    playerPoint.set((int)event.getX(), (int)event.getY());
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
        canvas.drawColor(Color.WHITE); //Background
        player.draw(canvas);
        if (arcadeImage != null) {
            arcadeImage.drawFull(0, 0);
        }
    }
}
