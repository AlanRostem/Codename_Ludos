package com.example.codename_ludos.Games.Surge.Objects.Items;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Games.Surge.Player;

import java.security.PublicKey;

public class WallJump extends PowerUp {
    public WallJump(float x, float y) {
        super("walljump", x, y, 0, 15);
        type = PUType.w_kick;
    }

    private boolean pressed = false;

    private float acceleration = 100000;
    private float lowSpeed = 5000f;

    static {
        objects.bindSprite("walljump", 0, 20, 20, 20);
    }

    @Override
    public void buff(Player player) {
        if (player.side.left || player.side.right) {
            if (!player.hasPowerUp(PUType.w_climb)) {
                if (player.mVel.y > 0) {
                    player.mVel.y = 300;
                }
            }
        }
        if (!player.side.bottom && ArcadeMachine.getCurrentGame().getControls().isTouched("jump")) {
            if (!pressed && (player.mVel.y > 0 || player.hasPowerUp(PUType.w_climb))) {
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
