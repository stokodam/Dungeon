package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

/**
 * Klasa tworzaca okno aplikacji.
 */
public class MyGdxGame extends ApplicationAdapter {
	/**
	 * Przechowuje informacje ktory ekran wyswietlic.
	 */
	int flagMenu = 0;
	/**
	 * Wskaznik na ekran gry
	 */
	ScreenGame screenGame;
	/**
	 * Wskaznik na ekran menu
	 */
	ScreenMenu screenMenu;

	/**
	 * Tworzy nam okreslony ekran.
	 */
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

	/**
	 * Metoda ktora wywoluje renderowanie odpowiedniego ekranu.
	 */
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
