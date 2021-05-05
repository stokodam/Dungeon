package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyGdxGame extends ApplicationAdapter {
	ScreenGame screenGame;

	@Override
	public void create () {
		screenGame = new ScreenGame();
		screenGame.create();
		screenGame.GameScreen();
	}

	@Override
	public void render () {
		screenGame.render(60);
	}
	
	@Override
	public void dispose () {

	}

}
