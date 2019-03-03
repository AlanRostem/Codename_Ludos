package com.example.codename_ludos.Games.Surge.Objects;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.Games.Surge.Player;
import com.example.codename_ludos.Games.Surge.Surge;

public class SurgeEntity extends GameEntity {
    public SurgeEntity(float x, float y) {
        super(x, y);
    }

    public void step() {

    }

    public void playerXCollision(Player player) {

    }

    public void playerYCollision(Player player) {

    }

    @Override
    public void update() {
        if (Math.abs(Surge.player.mPos.y - mPos.y) > ArcadeMachine.SCREEN_HEIGHT + ArcadeMachine.SCREEN_OFFSET_Y) {
            remove();
        }
        step();
    }

}
