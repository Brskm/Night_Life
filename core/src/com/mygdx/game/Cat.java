package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class Cat extends Object{
    static final int GO = 0, JUMP = 1, DOWN = 2, DEAD = 3;
    int faza, nFaz = 24;
    int cicle, mnoj, cicleDw, fazaDw;
    int state = GO;
    float sy, sx, sw, sh;
    boolean hitted = false;

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
        outOfScreen();
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
        } else if (state == DEAD) {
            dead();
        }
    }
    void jump() {
        cicleDw = 0;
        fazaDw = 0;
        toOriginal();

        faza = 24;
        vy = 25f * direction(cicle) - mnoj * direction(cicle);
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
            vy = -3;
            System.out.println("wtf");
        }
        else {
            vy = 0;
        }

        width = 184;
        height= 92;

        faza = (5 + cicleDw/6) * 6;
        if (faza > 48){
            faza = 30;
            cicleDw = 0;
            if (fazaDw++ > 2) {
                fazaDw = 0;
                toOriginal();
                state = GO;
            }
        }
    }
    void dead(){
        cicleDw = 0;
        fazaDw = 0;
        toOriginal();
        faza = 24;
    }
    int direction(int cle){
        return cle > 30 ? -1 : 1;
    }
    void toOriginal(){
        width = sw;
        height = sh;
    }

    boolean hit(float bx, float by, float bw, float bh){
        hitted = width / 2 + bw / 3 > Math.abs(x - bx) && height / 2 + bh / 3 > Math.abs(y - by);
        return hitted;
    }

    boolean outOfScreen(){
        return y < -SCR_HEIGHT/2;
    }
}

