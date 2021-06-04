package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;


public class MyGdxGame extends ApplicationAdapter {
	int flagMenu = 0;
	ScreenGame screenGame;
	ScreenMenu screenMenu;

	@Override
	public void create () {
		switch(flagMenu){
			case 0:
				screenMenu = new ScreenMenu();
				screenMenu.create();
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
				if(screenMenu == null)
					this.create();
				if(screenGame != null)
					screenGame = null;
				screenMenu.render(30);
				flagMenu = screenMenu.ifClick();
				break;
			case 1:
				if(screenMenu != null)
					screenMenu = null;
				if(screenGame == null)
					this.create();
				screenGame.render(60);
				flagMenu = screenGame.ifClick();
				break;
		}
	}
	
	@Override
	public void dispose () {

	}

}
