package com.example.codename_ludos.Games.Surge.Objects.Items;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Games.Surge.Player;

public class WallJump extends PowerUp {
    public WallJump(float x, float y) {
        super("walljump", x, y, 0, 15);
    }

    private boolean pressed = false;

    private float acceleration = 200000;
    private float lowSpeed = 5000f;

    static {
        objects.bindSprite("walljump", 0, 20, 20, 20);
    }

    @Override
    public void buff(Player player) {
        if (!player.side.bottom && ArcadeMachine.getCurrentGame().getControls().isTouched("jump")) {
            if (!pressed) {
                pressed = true;
                if (player.side.left) {
                    if (player.jumping) {
                        player.jumping = false;
                        player.mVel.y = -800;
                        player.speedX = lowSpeed;
                        player.accelerateX(acceleration);
                    }
                }
                if (player.side.right) {
                    if (player.jumping) {
                        player.jumping = false;
                        player.mVel.y = -800;
                        player.speedX = lowSpeed;
                        player.accelerateX(-acceleration);
                    }
                }
            }
        } else {
            pressed = false;
        }
    }
}
