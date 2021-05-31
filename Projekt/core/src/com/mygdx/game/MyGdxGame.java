package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyGdxGame extends ApplicationAdapter {
	int flagMenu = 0;
	ScreenGame screenGame;
	ScreenMenu screenMenu;

	@Override
	public void create () {
		switch(flagMenu){
			case 0:
				screenMenu = new ScreenMenu();
				break;
			case 1:
				screenGame = new ScreenGame();
				screenGame.create();
				screenGame.GameScreen();
				break;
		}
	}

	@Override
	public void render () {
		switch(flagMenu){
			case 0:
				screenMenu.render(30);
				break;
			case 1:
				screenGame.render(60);
				break;
		}
	}
	
	@Override
	public void dispose () {

	}

}
