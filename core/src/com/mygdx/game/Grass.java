package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class Grass extends Object{
    int cicle;
    public Grass(float x, float y, float width, float height) {
        super(x, y, width, height);
        vx = -7;
    }
    @Override
    public void move() {
        super.move();
        if (speedUp()) vx -= 0.2;
        if(outOfScreen()) x = SCR_WIDTH*3/2;
    }
    boolean outOfScreen(){
        return x < -SCR_WIDTH/2;
    }
    boolean speedUp(){
        if(cicle > 200){
            cicle = 0;
            return vx > -10;
        }
        return false;
    }
}
