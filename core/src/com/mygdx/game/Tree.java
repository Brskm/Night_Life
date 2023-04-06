package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.math.MathUtils;

public class Tree extends Object{
    public Tree(float x, float y, float width, float height) {
        super(x, y, width, height);
        vx = -1;
        cicle_max = 4;
    }
    @Override
    public void move() {
        super.move();
        if(outOfScreen()) {
            x = SCR_WIDTH*3/2;
            change ++;
            System.out.println(change);
        }
    }
    boolean outOfScreen(){
        return x < -SCR_WIDTH/2;
    }
    int change;
    boolean chBg(){
        if (change == cicle_max){
            change = 0;
            cicle_max = MathUtils.random(3, 6);
            return true;
        }
        else {
            return false;
        }
    }
}
