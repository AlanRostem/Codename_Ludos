package com.example.codename_ludos.Games.Eggrun;

import android.util.Log;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.LibraryTools.Math.Vector2D;

import java.util.ArrayList;
import java.util.List;

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

    

    protected boolean worldCollision(){
        for (int i = 0; i < gameMap.level.size(); i++) {
            for (int j = 0; j < gameMap.level.get(i).size(); j++){
                if (gameMap.level.get(i).get(j) == 1) {
                    Vector2D tilePos = new Vector2D(j * gameMap.tileSize + gameMap.offSet + ArcadeMachine.SCREEN_OFFSET_X,
                            i * gameMap.tileSize + ArcadeMachine.SCREEN_OFFSET_Y);
                    if (mPos.y + height >= tilePos.y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
