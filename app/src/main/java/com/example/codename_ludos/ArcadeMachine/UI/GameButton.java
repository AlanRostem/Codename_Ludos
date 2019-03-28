package com.example.codename_ludos.ArcadeMachine.UI;

import android.graphics.Color;
import android.util.Log;

import com.example.codename_ludos.ArcadeMachine.ArcadeMachine;
import com.example.codename_ludos.Assets.Graphics.Shapes;
import com.example.codename_ludos.Assets.Graphics.TextDrawer;
import com.example.codename_ludos.UserInterface.Controllers.Button;
import com.example.codename_ludos.UserInterface.Elements.ScrollList;
import com.example.codename_ludos.UserInterface.UIContainer;
import com.example.codename_ludos.UserInterface.UIElement;

public class GameButton extends Button {
    String gameID;
    public static final int WIDTH = ArcadeMachine.SCREEN_WIDTH;
    public static final int HEIGHT = 200;
    public GameButton(UIContainer parent, String gameID, int x, int y, int width, int height) {
        super(parent, gameID, x, y, width, height);
        this.gameID = gameID;
    }

    int color = Color.WHITE;
    boolean enteredGame = false;

    @Override
    public void onPressed(float x, float y) {
        if (!ArcadeMachine.getCurrentGame().isStarted() && selected) {
            ArcadeMachine.enterGame(this.gameID);
            selected = false;
            enteredGame = true;
        }

        for (UIElement b : parent.getChildNodes()) {
            if (b != this) {
                ((Button) b).selected = false;
            }
        }

        if (!enteredGame) {
            selected = true;
        }

        enteredGame = false;
    }

    @Override
    public void updatePos() {
        try {
            offsetPos.set(parent.getOutPutPos().x, parent.getOutPutPos().y);
        } catch (Exception e) {}
        outPutPos.x = initialPos.x + offsetPos.x;
        outPutPos.y = initialPos.y + offsetPos.y;
    }

    @Override
    public void draw() {
        if (selected) {
            color = Color.YELLOW;
        } else {
            color = Color.WHITE;
        }
        if (color == Color.YELLOW) {
            Shapes.setColor(Color.rgb(45, 7, 37));
        } else {
            Shapes.setColor(Color.rgb(113, 29, 81));
        }
        Shapes.drawRect(this.outPutPos.x, this.outPutPos.y, this.getWidth(), this.getHeight());
        TextDrawer.draw(this.gameID, color, 60, this.outPutPos.x, this.outPutPos.y);
    }
}
