package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class Cat extends Object{
    int lives = 3;
    int faza, nFaz = 24;
    int cicle;

    public Cat(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void move() {
        super.move();
        changePhase();
    }

    void changePhase(){
        if(++faza == nFaz) {
            faza = 0;
            if (cicle++ == 4) cicle = 0;
        }
    }
}
