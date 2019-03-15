package com.example.codename_ludos.Entity;

public class BasePlayer extends GameEntity {
    public BasePlayer(float x, float y) {
        super(x, y);
    }

    @Override
    public boolean overlap(GameEntity e) {
        if (e instanceof BaseGummyCash && super.overlap(e)) {
            // TODO: Increment the player's amount of in-game currency
            e.remove();
        }
        return super.overlap(e);
    }
}
