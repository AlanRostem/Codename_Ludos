package com.example.codename_ludos;

import android.content.Context;
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

import com.example.codename_ludos.LibraryTools.BitmapHelper;
import com.example.codename_ludos.LibraryTools.Constants;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    // Class that handles all rendering in the app
    private RectPlayer player;
    private Point playerPoint;
    private Paint paint;
    private Bitmap arcadeImage;
    private MainThread thread;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        setLayerType(LAYER_TYPE_HARDWARE, paint);
        Constants.CURRENT_CONTEXT = context;

        // TESTS:
        playerPoint = new Point(150, 150);
        player = new RectPlayer(new Rect(100, 100, 200, 200),
                Color.rgb(0, 250, 0));

        paint = new Paint();

        arcadeImage = BitmapHelper.decodeResource(getResources(), R.drawable.maskin);
        arcadeImage = BitmapHelper.resizeBitmap(arcadeImage, 1080, 1920);

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

    private int i = 0;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLACK); //Background
        player.draw(canvas);
        canvas.drawBitmap(arcadeImage, 0, 0, paint);
        canvas.drawBitmap(arcadeImage, 100, 100, paint);
        if (i < 1) {
            i++;
            String msgC = "" + canvas.isHardwareAccelerated();
            String msgV = "" + isHardwareAccelerated();
            Log.i("Canvas", msgC);
            Log.i("View", msgV);
        }
    }
}
