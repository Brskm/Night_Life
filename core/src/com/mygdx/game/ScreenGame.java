package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

public class ScreenGame implements Screen {
    MyGdxGame gg;
    Cat cat;

    Texture imgCow;
    Texture imgSky;
    Texture imgGrass;
    Texture imgMoon;
    Texture imgTree0;
    Texture imgTree1;
    Texture[] imgCat = new Texture[4];

    Grass[] grasses = new Grass[2];
    Sky[] skies = new Sky[2];
    Tree[] trees = new Tree[2];
    Cow cow;
    int treeim;

    long timeStart, timeCurrent;

    public ScreenGame(MyGdxGame myGdxGame){
        gg = myGdxGame;

        imgSky = new Texture("bg/sky.png");
        imgGrass = new Texture("bg/grass.png");
        imgMoon = new Texture("moon.png");
        imgTree0 = new Texture("bg/tree0.png");
        imgTree1 = new Texture("bg/tree1.png");
        imgCow = new Texture("cow.png");


        for (int j = 0; j < imgCat.length; j++) {
            imgCat[j] = new Texture("cats/cat"+j+".png");
        }
        timeStart = TimeUtils.millis();

        cat = new Cat(SCR_WIDTH/4f + 50, 200, 150, 150);

        cow = new Cow(148, 231, 36, 27);

        grasses[0] = new Grass(SCR_WIDTH/2f, 104, SCR_WIDTH + 8, 208);
        grasses[1] = new Grass(SCR_WIDTH*3f/2, 104, SCR_WIDTH + 8, 208);

        skies[0] = new Sky(SCR_WIDTH/2f, SCR_HEIGHT/2f, SCR_WIDTH, SCR_HEIGHT);
        skies[1] = new Sky(SCR_WIDTH*3f/2, SCR_HEIGHT/2f, SCR_WIDTH, SCR_HEIGHT);

        trees[0] = new Tree(SCR_WIDTH/2f, 224, SCR_WIDTH + 4, 120);
        trees[1] = new Tree(SCR_WIDTH*3f/2, 224, SCR_WIDTH + 4, 120);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        timeCurrent = TimeUtils.millis() - timeStart;
        gg.camera.update();
        gg.batch.setProjectionMatrix(gg.camera.combined);
        gg.batch.begin();
        for (Grass s: grasses) s.move();
        for (Sky k: skies) k.move();
        for (Tree t: trees) t.move();
        for (Sky k: skies) gg.batch.draw(imgSky, k.scrX(), k.scrY(), k.width, k.height);
        int index = 0;
        for (Tree t: trees) {
            //gg.batch.draw(t.imgtree? imgTree1:imgTree0, t.scrX(), t.scrY(), t.width, t.imgtree? 168:t.height);
            if (t.imgtree){
                System.out.println(t.scrX());
                gg.batch.draw(imgTree1, t.scrX(), t.scrY(), t.width, 168);
                gg.batch.draw(imgCow, cow.scrX(), cow.scrY(), cow.width, cow.height);
                if (t.scrX() == 1022){
                    trees[1].imgtree = false;
                }
            }
            else{
                gg.batch.draw(imgTree0, t.scrX(), t.scrY(), t.width, t.height);
            }
            index++;
        }
        for (Grass s: grasses) gg.batch.draw(imgGrass, s.scrX(), s.scrY(), s.width, s.height);
        gg.batch.draw(imgMoon, 180, 530, 102, 144);
        gg.fontSmall.draw(gg.batch, "high score: ", SCR_WIDTH - 360, SCR_HEIGHT-15);
        gg.fontSmall.draw(gg.batch, tmToStr(timeCurrent), SCR_WIDTH - 150, SCR_HEIGHT-65);
        gg.batch.draw(imgCat[cat.cicle == 3 && cat.faza/6 == 2? 2:cat.faza/6==2? 0:cat.faza/6], cat.faza/5 %2 == 0? cat.scrX():cat.scrX() - 2, cat.scrY(), cat.width, cat.height);
        cat.move();
        cow.move();
        gg.batch.end();
    }

    String tmToStr(long time) {
        String min = "" + time/1000/60/10 + time/1000/60%10;
        String sec = "" + time/1000%60/10 + time/1000%60%10;
        return min+":"+sec;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        imgMoon.dispose();
        imgSky.dispose();
        imgGrass.dispose();
        imgTree1.dispose();
        imgTree0.dispose();
    }
}
