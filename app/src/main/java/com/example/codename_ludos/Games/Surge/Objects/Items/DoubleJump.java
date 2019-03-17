package com.example.codename_ludos.Games.Surge.Objects.Items;

import android.util.Log;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Entity.GameEntity;
import com.example.codename_ludos.Games.Surge.Player;

public class DoubleJump extends PowerUp {
    public DoubleJump(float x, float y) {
        super("doublejump", x, y, 0.7f, 6);
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
        Log.i("Jumps", "" + player.side.bottom);
    }
}
