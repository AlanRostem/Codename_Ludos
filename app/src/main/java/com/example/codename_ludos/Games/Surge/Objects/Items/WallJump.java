package com.example.codename_ludos.Games.Surge.Objects.Items;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Games.Surge.Player;

public class WallJump extends PowerUp {
    public WallJump(float x, float y) {
        super("walljump", x, y, 0, 15);
    }

    @Override
    public void buff(Player player) {
        if (player.mVel.y != 0 && ArcadeMachine.getCurrentGame().getControls().isTouched("jump")) {
            if (player.side.left) {
                player.mVel.y = -800;
                player.mVel.x = 800;
            }
            if (player.side.right) {
                player.mVel.y = -800;
                player.mVel.x = -800;
            }
        }
    }
}
