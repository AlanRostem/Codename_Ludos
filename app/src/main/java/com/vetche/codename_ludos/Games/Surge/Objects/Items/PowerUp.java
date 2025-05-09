package com.vetche.codename_ludos.Games.Surge.Objects.Items;

import com.vetche.codename_ludos.Assets.Graphics.SpriteMap;
import com.vetche.codename_ludos.Core.MainThread;
import com.vetche.codename_ludos.Games.Surge.Objects.SurgeEntity;
import com.vetche.codename_ludos.Games.Surge.Player;
import com.vetche.codename_ludos.Games.Surge.Surge;

public class PowerUp extends SurgeEntity {


    protected float mRarity;
    protected float mDuration;
    protected float maxDuration;
    protected boolean mDone;
    protected boolean mUsing = false;
    protected String mDrawName;

    public enum PUType {
        d_jump,
        w_kick,
        w_climb
    }

    public PUType type;

    protected SpriteMap.Animation iconAnim = new SpriteMap.
            Animation(0, 3, 4, 0.25f);

    public PowerUp(String drawName, float x, float y, float rarity, float duration) {
        super(drawName, x, y, 20 + 40, 20 + 40);
        mRarity = rarity;
        mDuration = duration;
        maxDuration = duration;
        mDrawName = drawName;
        mDone = false;
    }

    public void draw(float x, float y) {
        objects.drawAt(mDrawName, (int) x + (int) Surge.camera.x,
                (int) y + (int) Surge.camera.y, width, height);
    }

    public void setInactive() {
        mDone = true;
        mDuration = 0;
        mUsing = false;
    }

    public void setDuration(float mDuration) {
        this.mDuration = mDuration;
    }

    public void resetDuration() {
        mDuration = maxDuration;
    }

    public float getDuration() {
        return mDuration;
    }

    public float getMaxDuration() {
        return maxDuration;
    }

    public void addDuration(float val) {
        mDuration += val;
    }

    public boolean isUsing() {
        return mUsing;
    }

    public boolean isDone() {
        return mDone;
    }

    public void use() {
        mUsing = true;
    }

    @Override
    public void draw() {
        if (!mUsing) {
            objects.Animate(mDrawName, iconAnim);
            objects.drawAt(
                    (int) mPos.x + (int) Surge.camera.x,
                    (int) mPos.y + (int) Surge.camera.y, width, height);
        }
    }

    @Override
    public void update() {
        super.update();
        if (mUsing) {
            mDuration -= MainThread.getAverageDeltaTime();
            if (mDuration <= 0.0f) {
                mDone = true;
                mUsing = false;
                remove();
            }
        }
    }

    public void buff(Player player) {

    }

}
