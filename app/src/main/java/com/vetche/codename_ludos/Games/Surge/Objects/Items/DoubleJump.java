package com.vetche.codename_ludos.Games.Surge.Objects.Items;

import com.vetche.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.vetche.codename_ludos.Games.Surge.Player;

public class DoubleJump extends PowerUp {
    public DoubleJump(float x, float y) {
        super("doublejump", x, y, 0.7f, 6);
        type = PUType.d_jump;
    }

    static {
        objects.bindSprite("doublejump", 0, 0, 20, 20);
    }


    @Override
    public void buff(Player player) {
        if (!ArcadeMachine.getCurrentGame().getControls().isTouched("jump")) {
            if (player.jumps < player.maxJumps) {
                if (player.jumping) {
                    player.jumps++;
                    player.djumping = true;
                    player.jumping = false;
                }
            } else {
                if (player.side.bottom) {
                    player.jumps = 0;
                    player.djumping = false;
                }
            }
        }
    }
}
