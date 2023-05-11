package com.mygdx.game;


import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenSettings implements Screen {
    MyGdxGame gg;
    Texture imgBG;
    TextButton btnEnterName, btnSound, btnMusic, btnDeleteScore, btnExit;
    boolean enterPlayerName;
    InputKeyboard keyboard;
    String playerName = "kitty";

    public ScreenSettings(MyGdxGame myGG){
        gg = myGG;
        btnEnterName = new TextButton(gg.fontLarge, "Cat's name: "+playerName, 100, 600);
        btnSound = new TextButton(gg.fontLarge, "Sound ON", 100, 500);
        btnMusic = new TextButton(gg.fontLarge, "Music ON", 100, 400);
        btnDeleteScore = new TextButton(gg.fontLarge, "Delete high score", 100, 300);
        btnExit = new TextButton(gg.fontLarge, "Menu", 100, 200);
        imgBG = new Texture("bg/settBg2.png");
        keyboard = new InputKeyboard(SCR_WIDTH, SCR_HEIGHT, 7);
        loadSettings();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // обработка касаний
        if(Gdx.input.justTouched()) {
            gg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            gg.camera.unproject(gg.touch);
            if(enterPlayerName) {
                if(keyboard.endOfEdit(gg.touch.x, gg.touch.y)) {
                    enterPlayerName = false;
                    playerName = keyboard.getText();
                    update();
                }
            } else {
                if (btnEnterName.hit(gg.touch.x, gg.touch.y)) {
                    enterPlayerName = true;
                }
                if (btnSound.hit(gg.touch.x, gg.touch.y)) {
                    gg.soundOn = !gg.soundOn;
                    update();
                }
                if (btnMusic.hit(gg.touch.x, gg.touch.y)) {
                    gg.musicOn = !gg.musicOn;
                    update();
                }
                if(btnDeleteScore.hit(gg.touch.x, gg.touch.y)){
                    btnDeleteScore.text = "High score reset";
                    resetScore();

                }
                if (btnExit.hit(gg.touch.x, gg.touch.y)) {
                    gg.setScreen(gg.screenIntro);
                    btnDeleteScore.text = "Delete high score";
                }
            }
        }

        // игровые события

        // отрисовка графики
        gg.camera.update();
        gg.batch.setProjectionMatrix(gg.camera.combined);
        gg.batch.begin();
        gg.batch.draw(imgBG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        btnEnterName.font.draw(gg.batch, btnEnterName.text, btnEnterName.x, btnEnterName.y);
        btnSound.font.draw(gg.batch, btnSound.text, btnSound.x, btnSound.y);
        btnMusic.font.draw(gg.batch, btnMusic.text, btnMusic.x, btnMusic.y);
        btnDeleteScore.font.draw(gg.batch, btnDeleteScore.text, btnDeleteScore.x, btnDeleteScore.y);
        btnExit.font.draw(gg.batch, btnExit.text, btnExit.x, btnExit.y);
        if(enterPlayerName){
            keyboard.draw(gg.batch);
        }
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
        Preferences pref = Gdx.app.getPreferences("data");
        pref.putString("name", playerName);
        pref.putBoolean("sound", gg.soundOn);
        pref.putBoolean("music", gg.musicOn);
        pref.flush();
    }

    @Override
    public void dispose() {
        imgBG.dispose();
        keyboard.dispose();
    }
    void loadSettings() {
        Preferences pref = Gdx.app.getPreferences("data");
        if(pref.contains("name")) playerName = pref.getString("name");
        if(pref.contains("sound")) gg.soundOn = pref.getBoolean("sound");
        if(pref.contains("music")) gg.musicOn = pref.getBoolean("music");
        update();
    }
    void resetScore(){
        Preferences pref = Gdx.app.getPreferences("data");
        pref.putLong("record", 0);
        pref.flush();
    }
    void update(){
        btnEnterName.setText("Cat's name: " + playerName);
        btnSound.setText(gg.soundOn ? "Sound ON" : "Sound OFF");
        btnMusic.setText(gg.musicOn ? "Music ON" : "Music OFF");
    }
}



