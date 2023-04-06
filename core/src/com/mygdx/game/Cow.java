package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class Cow extends Object{
    public Cow(float x, float y, float width, float height) {
        super(x, y, width, height);
        vy = 0.5f;
        cicle_max = 45;
    }
    @Override
    public void move() {
        super.move();
        if (cicle == cicle_max){
            cicle = 0;
            cicle_max = MathUtils.random(35, 55);
            vy *= -1;
        }
    }
}
