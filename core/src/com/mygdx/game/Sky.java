package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class Sky extends Object{
    public Sky(float x, float y, float width, float height) {
        super(x, y, width, height);
        vx = -0.5f;
    }
    @Override
    public void move() {
        super.move();
        if(outOfScreen()) x = SCR_WIDTH*3/2;
    }
    boolean outOfScreen(){
        return x < -SCR_WIDTH/2;
    }
}
