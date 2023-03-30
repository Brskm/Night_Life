package com.mygdx.game;


public class ImgButton {
    float x, y;
    float width, height;

    public ImgButton(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    boolean hit(float tx, float ty){
        return x < tx && tx < x+width && y-height < ty && ty < y;
    }
}
