package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

public class ScreenGame implements Screen {
    MyGdxGame gg;
    Cat cat;

    Texture imgCow;
    Texture imgSky;
    Texture imgGrass;
    Texture imgMoon;
    Texture imgTree0;
    Texture imgHolm;
    Texture imgTree1;
    Texture[] imgCat = new Texture[9];
    Texture[] imgBarrier = new Texture[2];

    Grass[] grasses = new Grass[2];
    Holmy[] holmies = new Holmy[2];
    Tree[] trees = new Tree[2];
    ArrayList<Barrier> barriers = new ArrayList<>();
    Cow cow;

    long timeStart, timeCurrent;
    long barrierLastSpawn, barrierInterval = 3000;

    public ScreenGame(MyGdxGame myGdxGame){
        gg = myGdxGame;

        imgSky = new Texture("bg/sky.png");
        imgGrass = new Texture("bg/grass.png");
        imgMoon = new Texture("moon.png");
        imgTree0 = new Texture("bg/tree0.png");
        imgTree1 = new Texture("bg/tree1.png");
        imgHolm = new Texture("bg/holmy.png");

        imgCow = new Texture("cow.png");


        for (int j = 0; j < imgBarrier.length; j++) {
            imgBarrier[j] = new Texture("barrier/bar"+j+".png");
        }

        for (int j = 0; j < imgCat.length; j++) {
            imgCat[j] = new Texture("cats/cat"+j+".png");
        }

        cat = new Cat(SCR_WIDTH/4f + 50, 200, 150, 150);

        cow = new Cow(156, 231, 36, 27);


        grasses[0] = new Grass(SCR_WIDTH/2f, 104, SCR_WIDTH + 8, 208);
        grasses[1] = new Grass(SCR_WIDTH*3f/2, 104, SCR_WIDTH + 8, 208);

        holmies[0] = new Holmy(SCR_WIDTH/2f, 223, SCR_WIDTH, 168);
        holmies[1] = new Holmy(SCR_WIDTH*3f/2, 223, SCR_WIDTH, 168);

        trees[0] = new Tree(SCR_WIDTH/2f, 224, SCR_WIDTH + 4, 120);
        trees[1] = new Tree(SCR_WIDTH*3f/2, 224, SCR_WIDTH + 4, 120);

    }

    @Override
    public void show() {
        timeStart = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        //нажатия
        if(Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            cat.state = 1;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            cat.state = 2;
        }

        //события
        timeCurrent = TimeUtils.millis() - timeStart;
        for (Grass s: grasses) s.move();
        for (Holmy k: holmies) k.move();
        for (Tree t: trees) t.move();
        spawnEnemyes();
        for (int i = barriers.size()-1; i >= 0; i--) {
            barriers.get(i).move();
            if(barriers.get(i).outOfScreen()) {
                barriers.remove(i);
            }
        }

        //отрисовка
        gg.camera.update();
        gg.batch.setProjectionMatrix(gg.camera.combined);
        gg.batch.begin();

        gg.batch.draw(imgSky, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        for (Holmy k: holmies) gg.batch.draw(imgHolm, k.scrX(), k.scrY(), k.width, k.height);
        int index = 0;
        for (Tree t: trees) {
            //gg.batch.draw(t.imgtree? imgTree1:imgTree0, t.scrX(), t.scrY(), t.width, t.imgtree? 168:t.height);
            if (t.imgtree){
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
        for (Barrier b: barriers) gg.batch.draw(imgBarrier[b.type], b.scrX(), b.scrY(), b.width, b.height);
        gg.batch.draw(imgCat[cat.cicle == 3 && cat.faza/6 == 2? 2:cat.faza/6==2? 0:cat.faza/6], cat.faza/6> 4? 204:cat.faza/5 %2 == 0? cat.scrX():cat.scrX() - 2, cat.faza/6> 4? 124:cat.scrY(), cat.width, cat.height);
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
        for (int j = 0; j < imgCat.length; j++) {
            imgCat[j].dispose();
        }
        imgMoon.dispose();
        imgSky.dispose();
        imgGrass.dispose();
        imgTree1.dispose();
        imgTree0.dispose();
        imgCow.dispose();
    }
    void spawnEnemyes(){
        if(barrierLastSpawn + barrierInterval < TimeUtils.millis()){
            System.out.println(imgBarrier.length);
            barriers.add(new Barrier(MathUtils.random(imgBarrier.length - 1)));
            barrierLastSpawn = TimeUtils.millis();
            barrierInterval = MathUtils.random(1000, 3000);
        }
    }
}
