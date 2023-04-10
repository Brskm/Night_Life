package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.math.MathUtils;

public class Cow extends Object{
    public Cow(float x, float y, float width, float height) {
        super(x, y, width, height);
        vy = 0.5f;
        vx = -1;
        cicle_max = 45;
    }
    @Override
    public void move() {
        super.move();
        if(outOfScreen()) x = SCR_WIDTH*3/2;
        if (cicle == cicle_max){
            cicle = 0;
            vy *= -1;
        }
    }
    boolean outOfScreen(){
        return x < -SCR_WIDTH/2f;
    }
}
