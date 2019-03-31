package com.example.codename_ludos.Games.Surge.Objects.Items;

import com.example.codename_ludos.Games.Surge.Player;

public class WallClimb extends PowerUp {
    public WallClimb(float x, float y) {
        super("wallclimb", x, y, 0, 5);
    }

    private final float climbSpeed = -500f;

    @Override
    public void buff(Player player) {
        if (player.side.left || player.side.right) {
            player.mVel.y = climbSpeed;
        }
    }
}
