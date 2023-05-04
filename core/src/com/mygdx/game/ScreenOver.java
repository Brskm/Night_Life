package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenOver implements Screen {
    MyGdxGame gg;

    Texture imgBG;
    TextButton btnExit;

    public ScreenOver(MyGdxGame myGG){
        gg = myGG;
        imgBG = new Texture("bg/settBg.png");
        btnExit = new TextButton(gg.fontLarge, "Menu", 80, 100);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            gg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            gg.camera.unproject(gg.touch);
            if (btnExit.hit(gg.touch.x, gg.touch.y)) {
                gg.setScreen(gg.screenIntro);
            }
        }
        gg.camera.update();
        gg.batch.setProjectionMatrix(gg.camera.combined);
        gg.batch.begin();
        gg.batch.draw(imgBG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        gg.fontTitle.draw(gg.batch, "Game Over", 220 , SCR_HEIGHT/2f + 150);
        btnExit.font.draw(gg.batch, btnExit.text, SCR_WIDTH/2f - btnExit.width/2f, 400);
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

    }
}
