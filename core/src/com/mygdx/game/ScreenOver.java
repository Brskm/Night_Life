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
    TextButton btnRetry;

    public ScreenOver(MyGdxGame myGG){
        gg = myGG;
        imgBG = new Texture("bg/settBg2.png");
        btnExit = new TextButton(gg.fontLarge, "Menu", 80, 280);
        btnRetry = new TextButton(gg.fontLarge, "Retry", 80, 420);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        btnExit.x = SCR_WIDTH/2f - btnExit.width/2f;
        btnRetry.x = SCR_WIDTH/2f - btnRetry.width/2f;
        if(Gdx.input.justTouched()) {
            gg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            gg.camera.unproject(gg.touch);
            if (btnExit.hit(gg.touch.x, gg.touch.y)) {
                gg.setScreen(gg.screenIntro);
            }
            if (btnRetry.hit(gg.touch.x, gg.touch.y)) {
                gg.screenGame.alive = true;
                gg.setScreen(gg.screenGame);
            }
        }
        gg.camera.update();
        gg.batch.setProjectionMatrix(gg.camera.combined);
        gg.batch.begin();
        gg.batch.draw(imgBG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        gg.fontTitle.draw(gg.batch, "Game Over", 380 , SCR_HEIGHT/2f + 200);
        gg.fontSmall.draw(gg.batch, gg.screenSettings.playerName + "'s result: " + gg.fin_time, 380 , 500);
        btnExit.font.draw(gg.batch, btnExit.text, btnExit.x, btnExit.y);
        btnRetry.font.draw(gg.batch, btnRetry.text, btnRetry.x, btnRetry.y);
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
