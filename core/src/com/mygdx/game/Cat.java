package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class Cat extends Object{
    static final int GO = 0, JUMP = 1, DOWN = 2;
    int lives = 3;
    int faza, nFaz = 24;
    int cicle, mnoj, cicleDw, fazaDw;
    int state = GO;
    float sy, sx, sw, sh;
    boolean change = true;

    public Cat(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.sx = x;
        this.sy = y;
        this.sw = width;
        this.sh = height;
    }

    @Override
    public void move() {
        super.move();
        changePhase();
        changeState();
    }

    void changePhase(){
        if(++faza >= nFaz) {
            faza = 0;
            if (cicle++ == 4) cicle = 0;
        }
    }
    void changeState() {
        if (state == JUMP) {
            jump();
        } else if (state == DOWN) {
            down();
        }
    }
    void jump() {
        cicleDw = 0;
        fazaDw = 0;
        toOriginal();

        faza = 24;
        vy = 20f * direction(cicle) - mnoj * direction(cicle);
        mnoj += 1;
        if (y < sy) {
            mnoj = 0;
            y = sy;
            vy = 0;
            state = GO;
            cicle = 0;
        }
    }
    void down(){
        cicleDw ++;
        if (y > sy){
            vy = -7;
        }
        else {
            vy = 0;
        }
        width = 184;
        height= 92;
        faza = (5 + cicleDw/6) * 6;
        System.out.println(faza);
        if (faza > 48){
            faza = 30;
            cicleDw = 0;
            System.out.println(505);
            if (fazaDw++ > 2) {
                fazaDw = 0;
                toOriginal();
                state = GO;
            }
        }
    }
    int direction(int cle){
        return cle > 30 ? -1 : 1;
    }
    void toOriginal(){
        width = sw;
        height = sh;
    }
}

