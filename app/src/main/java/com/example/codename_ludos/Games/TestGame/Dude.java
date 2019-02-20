package com.example.codename_ludos.Games.TestGame;

import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;
import com.example.codename_ludos.R;

public class Dude extends Vector2D {

    private SpriteMap sprite;
    private SpriteMap.Animation anim;

    int width = 100;
    int height = 100;

    public float velX = 0;
    public float velY = 0;
    public boolean jumping = false;
    public boolean jumpNow = false;
    float gravity = 0.3f;

   public Dude() {
        super(320, 0);
        sprite = new SpriteMap(R.drawable.rubigo, 384, 96);
        sprite.bindSprite("a1", 0, 0, 48, 48);
        anim = new SpriteMap.
                Animation(0, 7, 8, 0.1f);
    }

    public void update() {
        //velX = 0;
        velY += gravity;
        if (jumpNow)
        if (!jumping) {
            jumping = true;
            velY = -14;
        }

        addX(velX);
        addY(velY);
        int H = 1315;
        if (getY() + height > H) {
            setY(H - height);
            jumping = false;
        }

    }

    public void draw() {
       sprite.Animate("a1", anim);
       sprite.drawAt("a1", (int)getX(), (int)getY(), width, height);
    }
}
