package com.vetche.codename_ludos.Games.Surge;

import android.graphics.Color;


import com.vetche.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.vetche.codename_ludos.Assets.Audio.Audio;
import com.vetche.codename_ludos.Assets.Graphics.Shapes;
import com.vetche.codename_ludos.Assets.Graphics.SpriteMap;
import com.vetche.codename_ludos.Core.MainThread;
import com.vetche.codename_ludos.UserInterface.Controllers.Controls;
import com.vetche.codename_ludos.Entity.BasePlayer;
import com.vetche.codename_ludos.Entity.GameEntity;
import com.vetche.codename_ludos.Games.Surge.Objects.Items.PowerUp;
import com.vetche.codename_ludos.Games.Surge.Objects.SurgeEntity;

import java.util.ArrayList;

public class Player extends BasePlayer {
    private static SpriteMap sprite = ArcadeMachine
                    .getGame("Surge")
                    .getAssetLoader()
                    .getAsset("s_rubigo")
                    .asSpriteMap();

    static {
        sprite.bindSprite("a1", 0, 0, 48, 48);
    }

    private ArrayList<PowerUp> activePowerUps = new ArrayList<>();

    private Audio jumpSnd = ArcadeMachine
            .getGame("Surge")
            .getAssetLoader()
            .getAsset("a_jump")
            .asAudio();

    public Player() {
        super(220, ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT);
        mPos.y *= 2f/3f;
        width = 32 + 16;
        height = 64 + 32;
        jumpSnd.setPitch(2);
        for (PowerUp p : activePowerUps) {
            p.setInactive();
        }
    }

    public void setPowerUp(int i, PowerUp p) {
        if (i < activePowerUps.size()) {
            activePowerUps.set(i, p);
        }
    }

    public void onGround() {
        djumping = false;
        jumping = false;
        jumps = 0;
        speedX = defaultSpeed;
        friction = groundFriction;
    }

    public boolean hasPowerUp(PowerUp.PUType type) {
        for (PowerUp p : activePowerUps) {
            if (p.type == type) {
                return true;
            }
        }
        return false;
    }

    private Controls controls = ArcadeMachine.getCurrentGame().getControls();
    private boolean pressedJump = false;

    private void controlling() {

        if (mVel.y != 0) {
            jumping = true;
        }

        if (controls.isTouched("jump")) {
            if (!pressedJump) {
                if (!jumping) {
                    mVel.y = jumpSpeed;
                    jumpSnd.play();
                    jumping = true;
                }
                if (djumping) {
                    mVel.y = jumpSpeed;
                    jumpSnd.play();
                    djumping = false;
                }
            }
            pressedJump = true;
        } else {
            pressedJump = false;
        }

        if (!side.bottom) {
            speedX = airSpeed;
            friction = airFriction;
        }

        if (controls.isTouched("right"))
            accelerateX(speedX);
        else if (controls.isTouched("left"))
            accelerateX(-speedX);
        else
            glideX(friction);

        if (mVel.x >= maxSpeed) {
            mVel.x = maxSpeed;
        } else if (mVel.x <= -maxSpeed) {
            mVel.x = -maxSpeed;
        }
    }

    public boolean jumping = false;
    public int jumps = 0;
    public int maxJumps = 1;
    public boolean djumping = false;
    public int gravity = 1700;
    private float airFriction = 0.9f;
    private float groundFriction = 0.75f;
    private float friction = groundFriction;

    private float yPos = mPos.y;
    private float ySpeed = 100;
    public float jumpSpeed = -900f;

    private final float defaultSpeed = 3000f;
    public final float airSpeed = 950f;
    private float maxSpeed = 500f;
    public float speedX = defaultSpeed;

    private void step() {
        if (!side.bottom) {
            accelerateY(gravity);
        }

        side.reset();

        moveY(mVel.y);
        manageCollisionY();

        moveX(mVel.x);
        manageCollisionX();

        for (int i = 0; i < activePowerUps.size(); i++) {
            if (activePowerUps.get(i).isRemoved()) {
                activePowerUps.remove(i);
                break;
            }
            if (activePowerUps.get(i).isUsing() && !activePowerUps.get(i).isDone()) {
                activePowerUps.get(i).buff(this);
            } else {
                activePowerUps.get(i).remove();
                activePowerUps.remove(activePowerUps.get(i));
            }
        }

        int H = ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT;
        if (mPos.y + height > H) {
            mPos.y = H - height;
            onGround();
            side.bottom = true;
        }

        ySpeed += .1f;
        yPos -= ySpeed * MainThread.getAverageDeltaTime();
        //Surge.camera.update(0, yPos, 0, (ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT) / 3.f);
        Surge.camera.update(mPos.x, mPos.y, 0, (ArcadeMachine.SCREEN_OFFSET_Y + ArcadeMachine.SCREEN_HEIGHT) / 3.f);
    }

    @Override
    public void update() {
        controlling();
        step();
    }

    @Override
    public void draw() {
        Shapes.setColor(Color.argb(0.5f, 0f,1f,1f));

        for (int i = 0; i < activePowerUps.size(); i++) {
            if (activePowerUps.get(i).isUsing() && !activePowerUps.get(i).isDone()) {
                Shapes.drawRect(
                        Surge.camera.x + mPos.x + i * width / 2f - activePowerUps.get(i).width / 2f
                                - i * (activePowerUps.get(i).width)
                                + (activePowerUps.size() - i) * activePowerUps.get(i).width / 2f,
                        Surge.camera.y + mPos.y - activePowerUps.get(i).height - 10,
                        (int)(activePowerUps.get(i).width * (activePowerUps.get(i).getDuration() / activePowerUps.get(i).getMaxDuration())),
                        10);
                activePowerUps.get(i).draw(
                        mPos.x + i * width / 2f - activePowerUps.get(i).width / 2f - i * (activePowerUps.get(i).width)
                            + (activePowerUps.size() - i) * activePowerUps.get(i).width / 2f,
                        mPos.y - activePowerUps.get(i).height);

            }
        }

        Shapes.setColor(Color.argb(1f, 1f,1f,1f));
        sprite.drawAt("a1", (int) mPos.x + Surge.camera.x, (int) mPos.y + (int) Surge.camera.y, width, height);
    }

    private void manageCollisionX() {
        try {
            ArrayList<GameEntity> list = ArcadeMachine.getCurrentGame().getEntityList();
            for (GameEntity e : list) {
                if (e != this)
                    if (overlap(e)) {
                        if (e instanceof SurgeEntity) {
                            ((SurgeEntity) e).playerXCollision(this);
                        }
                    }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void manageCollisionY() {
        try {
            ArrayList<GameEntity> list = ArcadeMachine.getCurrentGame().getEntityList();
            for (GameEntity e : list) {
                if (e != this)
                    if (overlap(e)) {
                        if (e instanceof SurgeEntity) {
                            if (e instanceof PowerUp) {
                                if (activePowerUps.size() < 3) {
                                    PowerUp p = (PowerUp) e;
                                    boolean remove = false;
                                    if (!p.isUsing()) {
                                        for (PowerUp a : activePowerUps) {
                                            if (a.getClass().equals(p.getClass())) {
                                                a.resetDuration();
                                                p.remove();
                                                remove = true;
                                                break;
                                            }
                                        }
                                        if (!remove) {
                                            activePowerUps.add(p);
                                            p.use();
                                        }
                                    }
                                }
                            } else {
                                ((SurgeEntity) e).playerYCollision(this);
                            }
                        }
                    }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}