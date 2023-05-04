package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends Game {
	public static final int SCR_WIDTH = 1024, SCR_HEIGHT = 800;

	SpriteBatch batch;
	OrthographicCamera camera;
	Vector3 touch;
	BitmapFont fontSmall, fontLarge, fontTitle;

	ScreenIntro screenIntro;
	ScreenGame screenGame;
	ScreenSettings screenSettings;
	ScreenAbout screenAbout;
	ScreenOver screenOver;

	String fin_time;
	public boolean soundOn;
	public boolean musicOn;


	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		touch = new Vector3();
		generateFont();

		screenIntro = new ScreenIntro(this);
		screenSettings = new ScreenSettings(this);
		screenAbout = new ScreenAbout(this);
		screenGame = new ScreenGame(this);
		screenOver = new ScreenOver(this);
		setScreen(screenIntro);
	}

	@Override
	public void dispose () {
		batch.dispose();
		fontSmall.dispose();
		fontLarge.dispose();
	}

	void generateFont(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixel.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 30;
		parameter.color = Color.valueOf("ffffff");
		parameter.borderColor = Color.valueOf("341C6B");
		parameter.borderWidth = 4;
		parameter.borderStraight = true;
		parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
		fontSmall = generator.generateFont(parameter);
		parameter.size = 60;
		fontLarge = generator.generateFont(parameter);
		generator = new FreeTypeFontGenerator(Gdx.files.internal("pixel.ttf"));
		parameter.size = 100;
		parameter.color = Color.valueOf("ffffff");
		fontTitle = generator.generateFont(parameter);
		generator.dispose();
	}
}
