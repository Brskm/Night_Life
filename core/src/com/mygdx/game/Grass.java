package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class Grass extends Object{
    public Grass(float x, float y, float width, float height) {
        super(x, y, width, height);
        vx = -4;
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
