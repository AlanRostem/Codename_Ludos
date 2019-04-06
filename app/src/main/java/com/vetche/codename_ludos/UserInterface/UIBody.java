package com.vetche.codename_ludos.UserInterface;

import com.vetche.codename_ludos.LibraryTools.Constants;

public class UIBody extends UIContainer {

    public UIBody(String ID, float x, float y, int width, int height) {
        super(ID, x, y, width, height);
    }

    public UIBody(String ID, float x, float y) {
        super(ID, x, y,
                Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT);
    }
}
