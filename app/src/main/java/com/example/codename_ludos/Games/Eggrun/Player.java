package com.example.codename_ludos.Games.Eggrun;

import com.example.codename_ludos.Assets.SpriteMap;
import com.example.codename_ludos.Entity.BasePlayer;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

public class Player extends BasePlayer {
    private SpriteMap sprite;

    public Player() {
        super();
        mPos = new Vector2D(20,20);
    }

}
