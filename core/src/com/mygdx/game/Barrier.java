package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.math.MathUtils;

public class Barrier extends Object{
    static final int BIN = 0, CRAPIVA = 1, ROCK = 2;

    int cicle = 12;
    int type;

    public Barrier(int type) {
        super(0, 0, 0, 0);
        this.type = type;
        whType();
        x = SCR_WIDTH + width;
        y = 180;
        vx = -8;
    }

    @Override
    public void move() {
        super.move();
        outOfScreen();
    }

    void whType(){
        switch (type){
            case BIN:
                width = 102;
                height = 114;
                break;
            case CRAPIVA:
                width = 84;
                height = 108;
                break;
            case ROCK:
                width = 100;
                height = 70;
        }
    }

    boolean outOfScreen(){
        return x < -SCR_WIDTH/2;
    }
}
