package com.example.codename_ludos.Games.GameSelect;

import android.graphics.Color;
import android.util.Log;

import com.example.codename_ludos.ArcadeMachine.ArcadeGame;
import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Shapes;
import com.example.codename_ludos.Controllers.Button;
import com.example.codename_ludos.Core.GamePanel;
import com.example.codename_ludos.Core.MainThread;

public class GameSelect extends ArcadeGame {

    public GameSelect() {
        super("GameSelect");
        int i = 0;
        for (String n : ArcadeMachine.getGameIDList()) {
            final String N = n;
            controls.createController(n, new Button(240, 250 + i * 200, 600, 100) {
                public void onTouch(float x, float y) {
                    ArcadeMachine.enterGame(N);
                }

                public void draw() {
                    Shapes.setColor(Color.RED);
                    Shapes.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
                    MainThread.canvas.drawText(N, this.x, this.y, GamePanel.paint);
                }
            });
            i++;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        ArcadeMachine.arcadeImage.drawAt("all", 0, 0, ArcadeMachine.rawImageWidth, ArcadeMachine.rawImageHeight);
    }
}
