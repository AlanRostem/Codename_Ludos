package com.example.codename_ludos.Games.TestGame;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.R;

public class Dude extends Vector2D {

    private SpriteMap sprite;
    private SpriteMap.Animation walkL;
    private SpriteMap.Animation walkR;

    int width = 100;
    int height = 100;

    public float velX = 0;
    public float velY = 0;
    public boolean jumping = false;
    public boolean jumpNow = false;

    public boolean right = true;
    public boolean left = true;
    public boolean walk = false;
    float gravity = 0.3f;

   public Dude() {
        super(320, 0);
        sprite = new SpriteMap(R.drawable.rubigo);
        sprite.bindSprite("a1", 0, 0, 48, 48);

        walkL = new SpriteMap.
                Animation(0, 7, 8, 0.1f);
        walkR = new SpriteMap.
                Animation(8, 15, 8, 0.1f);
    }

    public void update() {
        //velX = 0;
        velY += gravity;
        if (jumpNow)
        if (!jumping) {
            jumping = true;
            velY = -10;
        }

        addX(velX);
        addY(velY);
        int H = ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT;
        if (getY() + height > H) {
            setY(H - height);
            jumping = false;
        }
    }

    public void draw() {
       if (left) {
           if (walk)
               sprite.Animate("a1", walkR);
       } else if (right) {
           if (walk)
               sprite.Animate("a1", walkL);
       }
       sprite.drawAt("a1", (int)getX(), (int)getY(), width, height);
    }
}
