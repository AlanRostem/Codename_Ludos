package com.example.codename_ludos.Games.Surge.Objects.Items;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Core.MainThread;
import com.example.codename_ludos.Games.Surge.Objects.SurgeEntity;
import com.example.codename_ludos.Games.Surge.Player;
import com.example.codename_ludos.Games.Surge.Surge;
import com.example.codename_ludos.R;

public class PowerUp extends SurgeEntity {

    public static SpriteMap sprite = new SpriteMap(R.drawable.surgepowerups);

    protected float mRarity;
    protected float mDuration;
    protected boolean mDone;
    protected boolean mUsing = false;
    protected String mDrawName;

    protected static SpriteMap.Animation iconAnim = new SpriteMap.
            Animation(0, 3, 4, 0.25f);

    public PowerUp(String drawName, float x, float y, float rarity, float duration) {
        super(x, y);
        mRarity = rarity;
        mDuration = duration;
        mDrawName = drawName;
        width = height = 80;
    }

    public void setInactive() {
        mDone = true;
        mDuration = 0;
        mUsing = false;
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
            sprite.Animate(mDrawName, iconAnim);
            sprite.drawAt(mDrawName, (int)mPos.x + (int)Surge.camera.x,
                    (int)mPos.y + (int)Surge.camera.y, width, height);
        }
    }

    @Override
    public void update() {
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
