package com.example.codename_ludos.Games.Lodestone;

import android.graphics.Color;
import android.view.MotionEvent;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

import static com.example.codename_ludos.Core.GamePanel.paint;
import static com.example.codename_ludos.Core.MainThread.canvas;

public class Lodestone extends ArcadeGame {

    public Lodestone(){ super("Lodestone"); }

    public Vector2D magnet = new Vector2D(20,20);

    @Override
    public void setup() {
    }

    @Override
    public void update() {
        controls.update();
        updateEntities();
    }

    @Override
    public void draw() {
        paint.setColor((Color.RED));
        canvas.drawRect((int)magnet.x, (int)magnet.y, (int)magnet.x + 40, (int)magnet.y + 40, paint);
    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        // Get the coordinate (x,y) for the current status of event.
        float x = event.getX();
        float y = event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            magnet.x = x;
            magnet.y = y;
        }

        else if(event.getAction() == MotionEvent.ACTION_MOVE) {
            magnet.x = x;
            magnet.y = y;
        }

    }
}
