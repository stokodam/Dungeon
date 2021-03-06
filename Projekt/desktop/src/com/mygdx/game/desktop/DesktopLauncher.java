package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

/**
 * Klasa uruchamiajaca gre.
 */
public class DesktopLauncher {
	/**
	 * Metoda odpowiadajaca za uruchomienie.
	 * @param arg String podawany podczas uruchamiania programu przez wiersz polecen.
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.fullscreen = false;
		config.resizable = false;
		config.title = "Majster Dungeon";
		config.addIcon("tytul.png", Files.FileType.Local);
		new LwjglApplication(new MyGdxGame(), config);
	}
}
