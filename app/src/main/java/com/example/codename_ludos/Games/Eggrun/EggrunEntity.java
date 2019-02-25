package com.example.codename_ludos.Games.Eggrun;

import com.example.codename_ludos.Entity.GameEntity;


public class EggrunEntity extends GameEntity {

    protected int width;
    protected int height;
    protected float gravity = 25f;

    public EggrunEntity(float x, float y, int w, int h){
        super(x,y);
        width = w;
        height = h;
    }

    public static GameMap gameMap;


}
