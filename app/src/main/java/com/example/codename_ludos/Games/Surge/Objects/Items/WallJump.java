package com.example.codename_ludos.Games.Surge.Objects.Items;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Games.Surge.Player;

public class WallJump extends PowerUp {
    public WallJump(float x, float y) {
        super("walljump", x, y, 0, 15);
    }

    private boolean pressed = false;

    @Override
    public void buff(Player player) {
        if (!player.side.bottom && ArcadeMachine.getCurrentGame().getControls().isTouched("jump")) {
            if (!pressed) {
                pressed = true;
                if (player.side.left) {
                    if (player.jumping) {
                        player.jumping = false;
                        player.mVel.y = -600;
                        player.mVel.x = 1500;
                    }
                }
                if (player.side.right) {
                    if (player.jumping) {
                        player.jumping = false;
                        player.mVel.y = -600;
                        player.mVel.x = -1500;
                    }
                }
            }
        } else {
            pressed = false;
        }
    }
}
