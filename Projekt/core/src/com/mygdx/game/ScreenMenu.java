package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Timer;

/**
 * Klasa przechowujaca pola i metody dla ekranu menu.
 */
public class ScreenMenu extends InputAdapter implements Screen ,InputProcessor{
    /**
     * Wczytywanie tekstury do przycisku play.
     */
    Texture newGame = new Texture("play.png");
    /**
     * Wczytywanie tekstury do przycisku exit.
     */
    Texture exit = new Texture("exit.png");
    /**
     * Warstwa ekranu.
     */
    SpriteBatch screen = new SpriteBatch();
    /**
     * Wczytanie ognikow.
     */
    Flames[] flames = { new Flames(0),
                        new Flames(1),
                        new Flames(2),
                        new Flames(3)};
    /**
     * Flaga dotyczaca ktory ekran ma sie wyswietlic.
     */
    int fMenu = 0;

    /**
     * Timer do animacji.
     */
    Timer anim = new Timer();
    /**
     * Wyznaczamy zadanie dla timera.
     */
    Timer.Task anima = new Timer.Task() {
        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                flames[i].change_flame();
            }
        }
    };
    /**
     * Pozycja x kursora.
     */
    private int mousepositionx;
    /**
     * Pozycja y kursora.
     */
    private int mousepositiony;

    /**
     * Funkcja uruchamiajaca timery, animacje oraz przechwytuje kursor.
     */
    public void create(){
        anim.start();
        anim.scheduleTask(anima,0.05f,0.05f);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {

    }

    /**
     * renderowanie ekranu i pokazywanie odpowiednich spritow na odpowiednich pozycjach.
     * @param delta delta
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(22/255.f, 22/255.f, 22/255.f,0.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        screen.begin();
        screen.draw(flames[0].frame,920,320,98,122);
        screen.draw(flames[1].frame,255,300,98,122);
        screen.draw(flames[2].frame,640,100,98,122);
        screen.draw(flames[3].frame,600,600,98,122);
        screen.draw(flames[0].frame,100,200,98,122);
        screen.draw(flames[1].frame,100,500,98,122);
        screen.draw(flames[2].frame,1100,200,98,122);
        screen.draw(flames[3].frame,1100,500,98,122);
        screen.draw(newGame,565,400);
        screen.draw(exit,565,300);
        screen.end();
    }

    /**
     * @return Zwraca stan przycisku.
     */
    int ifClick(){
        return this.fMenu;
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

    /**
     * @param screenX pozycja x myszy
     * @param screenY pozycja y myszy
     * @return Zwraca ruch kursora.
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousepositionx = Gdx.input.getX();
        mousepositiony = Gdx.input.getY();
        return false;
    }

    /**
     * @param screenX pozycja x ekranu
     * @param screenY pozycja y ekranu
     * @param pointer wskaznik na wydarzenie
     * @param button przycisk
     * @return Zwraca wartosc ktora jest potrzebna jesli uzywasz input multiplexer
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(button == Input.Buttons.LEFT){

            if (Intersector.isPointInPolygon((new float[] {565,325,565,240,715,240,715,325}),0,8,mousepositionx,mousepositiony) ){

                fMenu = 1;
            }
            if (Intersector.isPointInPolygon((new float[] {565,420,565,340,715,340,715,420}),0,8,mousepositionx,mousepositiony) ){

                Gdx.app.exit();
            }
        }

        return false;
    }
}
