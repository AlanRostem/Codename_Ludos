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

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.LibraryTools.BitmapHelper;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.LibraryTools.Logger;
import com.example.codename_ludos.R;
import com.example.codename_ludos.RectPlayer;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    // Class that handles all rendering in the app
    public static Paint paint;
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
        Constants.SCREEN_WIDTH = width;
        Constants.SCREEN_HEIGHT = height;
    }

    @Override
    public void surfaceCreated(SurfaceHolder sHolder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
        ArcadeMachine.initialize();
        // TESTS:
        Logger.enableDebugStats(true);
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
        ArcadeMachine.touchEventHandle(event);
        return true;
    }

    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLACK); //Background
        ArcadeMachine.draw();
        Logger.draw();
    }
}
