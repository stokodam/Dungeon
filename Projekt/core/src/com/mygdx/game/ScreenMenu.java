package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenMenu implements Screen {
    Texture newGame = new Texture("play.png");
    Texture exit = new Texture("exit.png");
    SpriteBatch screen = new SpriteBatch();

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        screen.begin();

        screen.draw(newGame,565,400);
        screen.draw(exit,565,300);
        screen.end();
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
