package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class Cat extends Object{
    int lives = 3;
    int faza, nFaz = 24;
    int cicle;
    int state = 0;
    float sy, sx;
    int mnoj = 0;

    public Cat(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.sx = x;
        this.sy = y;
    }

    @Override
    public void move() {
        super.move();
        changePhase();
        changeState();
    }

    void changePhase(){
        if(++faza == nFaz) {
            faza = 0;
            if (cicle++ == 4) cicle = 0;
        }
    }
    void changeState(){
        if (state == 1){
            faza = 0;
            vy = 20f * direction(cicle) - mnoj * direction(cicle);
            cicle++;
            mnoj += 1;
            if (y < sy) {
                mnoj = 0;
                y = sy;
                vy = 0;
                state = 0;
                cicle = 0;
            }
            //vx = 0.5f * direction(cicle);
        }
    }
    int direction(int cle){
        return cle > 60? -1:1;
    }
}
