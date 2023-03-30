package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Texture;

public class ScreenGame implements Screen {
    MyGdxGame gg;
    Cat cat;

    Texture imgBG1;
    Texture imgBG2;
    Texture imgBG3;
    Texture[] imgCat = new Texture[4];

    public ScreenGame(MyGdxGame myGdxGame){
        gg = myGdxGame;
        imgBG1 = new Texture("bg/bg1.png");
        imgBG2 = new Texture("bg/bg2.png");
        imgBG3 = new Texture("bg/bg3.png");
        for (int j = 0; j < imgCat.length; j++) {
            imgCat[j] = new Texture("cats/cat"+j+".png");
        }

        cat = new Cat(SCR_WIDTH/4f + 50, 280, 150, 150);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gg.camera.update();
        gg.batch.setProjectionMatrix(gg.camera.combined);
        gg.batch.begin();
        gg.batch.draw(imgBG1, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        gg.batch.draw(imgCat[cat.cicle == 3 && cat.faza/6 == 2? 2:cat.faza/6==2? 0:cat.faza/6], cat.faza/5 %2 == 0? cat.scrX():cat.scrX() - 2, cat.scrY(), cat.width, cat.height);
        cat.move();
            /*TimeUnit.SECONDS.sleep(1);
            gg.batch.draw(imgBG2, 3, 4, SCR_WIDTH, SCR_HEIGHT);
            TimeUnit.SECONDS.sleep(1);
            gg.batch.draw(imgBG3, 0, 0, SCR_WIDTH, SCR_HEIGHT);
            TimeUnit.SECONDS.sleep(1);

        } catch (Exception expn) {
            System.out.println(expn);
        }*/
        gg.batch.end();
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
        imgBG1.dispose();
        imgBG2.dispose();
        imgBG3.dispose();
    }
}
