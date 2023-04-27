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
        x = SCR_WIDTH + width;
        y = 180;
        vx = -6;
    }

    @Override
    public void move() {
        super.move();
        outOfScreen();
    }

    void whType(){
        System.out.println(type);
        if (type == 0){
            width = 102;
            height = 114;
        } else if (type == 1) {
            width = 84;
            height = 108;
        } else if (type == 2) {
            width = 100;
            height = 70;
        }
        /*
        switch (type){
            case 0:
                width = 102;
                height = 114;
            case 1:
                width = 84;
                height = 108;
            case 2:
                width = 100;
                height = 70;
        }

         */
    }

    boolean outOfScreen(){
        return x < -SCR_WIDTH/2;
    }
}
