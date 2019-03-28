package com.example.codename_ludos.Core;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.UserInterface.Finger;
import com.example.codename_ludos.LibraryTools.Constants;
import com.example.codename_ludos.LibraryTools.Logger;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    // Class that handles all rendering in the app
    public static Paint paint;
    private MainThread thread;
    private Vector2D touchPosition;
    private Finger[] fingers;

    private int currentActionEvent;
    private int maxPointerCount = 10;
    public static int ptrCnt;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        setLayerType(LAYER_TYPE_HARDWARE, paint);
        Constants.CURRENT_CONTEXT = context;
        paint = new Paint();
        touchPosition = new Vector2D(0, 0);
        fingers = new Finger[maxPointerCount];
        for (int i = 0; i < maxPointerCount; i++) {
            fingers[i] = new Finger(false, -1, -1, -1, MotionEvent.ACTION_UP);
        }
     }

    @Override
    public void surfaceChanged(SurfaceHolder sHolder, int format, int width, int height) {
        Constants.SCREEN_WIDTH = width;
        Constants.SCREEN_HEIGHT = height;
        ArcadeMachine.calibrateScreen();
    }

    private boolean runningInBackground = false;

    @Override
    public void surfaceCreated(SurfaceHolder sHolder) {
        if (!runningInBackground) {
            ArcadeMachine.calibrateScreen();
            ArcadeMachine.initialize();
            runningInBackground = true;
        }
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
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
            } catch (Exception e) { e.printStackTrace(); }
            retry = false;
        }
    }

    public Finger[] getFingers() {
        return fingers;
    }

    private void touchFingerManager(MotionEvent event) {
        int pointerCount = ptrCnt = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            float x = event.getX(i);
            float y = event.getY(i);
            
            int id = event.getPointerId(i);
            int action = event.getAction();

            boolean fingerOnScreen = action == MotionEvent.ACTION_DOWN
                    || action == MotionEvent.ACTION_POINTER_DOWN
                    || action == MotionEvent.ACTION_MOVE;

            if (pointerCount > 1)
                fingers[i+1].setDown(false);
            else
                fingers[i].setDown(false);

            if (fingerOnScreen)
                fingers[i].set(true, id, x, y, action);
            else
                fingers[i].set(true, id, x, y, MotionEvent.ACTION_UP);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ArcadeMachine.touchEventHandle(event);
        touchPosition.set(event.getX(), event.getY());
        currentActionEvent = event.getAction();
        touchFingerManager(event);
        return true;
    }

    public int getCurrentActionEvent() {
        return currentActionEvent;
    }

    public Vector2D getTouchPosition() {
        return touchPosition;
    }

    public void update() {
        ArcadeMachine.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.DKGRAY); //Background
        ArcadeMachine.draw();
        Logger.draw();
    }
}
