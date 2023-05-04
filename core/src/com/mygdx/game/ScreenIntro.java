package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class ScreenIntro implements Screen {
    MyGdxGame gg;

    Texture imgBG;

    Texture imAbot;
    Texture imSett;
    Texture imPlay;

    Texture imAbotHit;
    Texture imSettHit;
    Texture imPlayHit;

    ImgButton BtAbout, BtSett, BtPlay;
    Music sndMusic;

    public ScreenIntro(MyGdxGame myGG){
        gg = myGG;

        imgBG = new Texture("main.png");

        imAbot = new Texture("buttons/about2.png");
        imSett = new Texture("buttons/settings2.png");
        imPlay = new Texture("buttons/play2.png");

        imAbotHit = new Texture("buttons/about.png");
        imSettHit = new Texture("buttons/settings.png");
        imPlayHit = new Texture("buttons/play.png");

        BtPlay = new ImgButton(80, 480, 300, 120);
        BtSett = new ImgButton(80, 325, 300, 120);
        BtAbout = new ImgButton(80, 170, 300, 120);

        sndMusic = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
        sndMusic.setLooping(true);
        sndMusic.setVolume(0.2f);
    }
    @Override
    public void show() {
        Preferences pref = Gdx.app.getPreferences("data");
        System.out.println(pref.getBoolean("music"));
        if(pref.getBoolean("music")) sndMusic.play();
        else sndMusic.stop();
    }

    @Override
    public void render(float delta) {
        boolean AbHit = false;
        boolean StHit = false;
        boolean PlHit = false;

        if(Gdx.input.justTouched()) {
            gg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            gg.camera.unproject(gg.touch);
            if(BtAbout.hit(gg.touch.x, gg.touch.y)){
                AbHit = !AbHit;
                gg.setScreen(gg.screenAbout);
            }
            if(BtSett.hit(gg.touch.x, gg.touch.y)){
                StHit = !StHit;
                gg.setScreen(gg.screenSettings);
            }
            if(BtPlay.hit(gg.touch.x, gg.touch.y)){
                PlHit = !PlHit;
                gg.screenGame.alive = true;
                gg.setScreen(gg.screenGame);
            }
        }
            
        gg.camera.update();
        gg.batch.setProjectionMatrix(gg.camera.combined);
        gg.batch.begin();
        gg.batch.draw(imgBG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        gg.batch.draw(PlHit ? imPlayHit:imPlay, 80, 360, 300, PlHit ? 110:120);
        gg.batch.draw(StHit ? imSettHit:imSett, 80, 205, 300, StHit ? 110:120);
        gg.batch.draw(AbHit ? imAbotHit:imAbot, 80, 50, 300, AbHit ? 110:120);
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
        imgBG.dispose();
    }
}
