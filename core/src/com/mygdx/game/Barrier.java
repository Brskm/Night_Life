package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.math.MathUtils;

public class Barrier extends Object{
    int cicle = 12;
    int type;

    public Barrier(int type) {
        super(0, 0, 0, 0);
        this.type = type;
        whType();
        x = SCR_WIDTH + 100;
        y = 180;
        vx = -6;
    }

    @Override
    public void move() {
        super.move();
        outOfScreen();
    }

    void hit(float catx, float caty){
    }

    void whType(){
        switch (type){
            case 0:
                width = 102;
                height = 114;
            case 1:
                width = 84;
                height = 108;
        }
    }

    boolean outOfScreen(){
        return x < -SCR_WIDTH/2;
    }
}
