package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

/**
 * Klasa przechowujaca pola i metody ekranu gry.
 */
public class ScreenGame extends InputAdapter implements Screen, InputProcessor {

    //screen
    /**
     * Kamera glowna gry.
     */
    private OrthographicCamera camera;
    /**
     * Warstwa na ktorej sa rysowane spraity
     */
    private SpriteBatch batch;
    /**
     * Czcionka uzywana w grze.
     */
    BitmapFont font = new BitmapFont();

    /**
     * Flaga dotyczaca tekstu pojawiajacego sie na ekranie.
     */
    int fTask = 0;
    /**
     * Flaga dotyczaca ktory ekran ma sie wyswietlic.
     */
    int fMenu = 1;
    /**
     * Pozycja X kursora.
     */
    int mousepositionx = Gdx.input.getX();
    /**
     * Pozycja X kursora.
     */
    int mousepositiony = Math.abs(Gdx.input.getY()-720);
    /**
     * Wczytywanie grafiki mapy.
     */
    Texture mapatest = new Texture("Map1.png");

    /**
     * Wskaznik na klase Hex.
     * @see Hex
     */
    Hex hex;
    /**
     * Wskaznik na postac.
     * @see Charcters
     */
    Charcters postac = new Charcters();
    /**
     * Tablica klas zawierajaca wskazniki na przeciwnikow.
     * @see Demon
     */
    Demon[] oni_bi = {new Demon(30,38),new Demon(22,31),new Demon(7,28),new Demon(19,16),new Demon(32,17),new Demon(32,2)};

    /**
     * Timer do zmieniania klatek animacji.
     */
    Timer anim = new Timer();
    /**
     * Zadanie dla Timeru - obslugiwanie animacji przeciwnikow.
     */
    Timer.Task anima = new Timer.Task() {
        @Override
        public void run() {
            postac.update();
            for (Demon demon : oni_bi) {
                if (demon != null)
                    demon.update();
            }
        }
    };
    /**
     * Zadanie dla Timeru - uruchomienie glownej gry po wygasnieciu tekstu.
     */
    Timer.Task zadanie = new Timer.Task() {
        @Override
        public void run() {
            fTask = 1;
        }
    };

    /**
     * Funkcja odpowiadajaca za utowrzenie pozycji kamery, obslugi spriteow, uruchomienie taskow dla timera.
     */
    public void create () {
        hex = new Hex();
        camera = new OrthographicCamera(320,180);
        camera.position.set(Hex.hexCenterx(postac.getX(), postac.getY()), Hex.hexCentery(postac.getY()),0);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        postac.Load_characters();

        font.getData().setScale(0.8f);
        for (Demon demon : oni_bi) {

            demon.init();
            Hex.board[demon.x][demon.y] = 1;
        }
        anim.start();

        anim.scheduleTask(anima,0.15f,0.15f);
        anim.scheduleTask(zadanie,5f);

    }


    /**
     * Utworzenie warstwy do rysowania spriteow.
     */
    void GameScreen(){
        batch = new SpriteBatch();
    }

    /**
     * Renderowanie wszystkich potrzebnych spriteow i grafik na ekran.
     * @param deltaTime deltatime
     */
    @Override
    public void render(float deltaTime) {

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        camera.update();
        Gdx.gl.glClearColor(0.3f,0.3f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(fTask == 0)
            font.draw(batch,"Pokonaj wrogow", Hex.hexCenterx(postac.getX(), postac.getY())-40, Hex.hexCentery(postac.getY())+20);
        else
            Gdx.input.setInputProcessor(this);
        batch.end();
        if (fTask == 1)
        {

        for(int i = postac.getX()-1;i<=postac.getX()+1;i++)
            for(int j = postac.getY()-1;j<=postac.getY()+1;j++){
            if(i>-1 && j>-1 && i<49 && j<49)
                if (postac.getY() % 2 != 1 || postac.getY() == j || i - postac.getX() != -1) {
                    if (postac.getY() % 2 != 0 || postac.getY() == j || i - postac.getX() != 1)
                        Hex.drawHex(i, j, batch, camera, 1);
                }
            }
            for (Demon demon : oni_bi) {

                if (demon != null)
                    Hex.drawHex(demon.x, demon.y, batch, camera, 2);
            }
        batch.begin();

        batch.draw(mapatest,0,0);
            for (Demon demon : oni_bi) {

                if (demon != null)
                    batch.draw(demon.texture, Hex.hexCenterx(demon.x, demon.y) - 15, Hex.hexCentery(demon.y) - 20);
            }

        batch.draw(postac.texture, Hex.hexCenterx(postac.getX(), postac.getY()), Hex.hexCentery(postac.getY()));


        batch.end();
        }
    }

    /**
     * Funkcja sprawdza czy wszyscy przeciwnicy zostali pokonani.
     * @return zwraca czy wszyscy przeciwnicy zostali pokonani.
     */
    int ifClick(){return this.fMenu;}

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

    @Override
    public void show() {

    }

    //Input handling =========================================
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * @param screenX Zwraca pozycje ekranu x.
     * @param screenY Zwraca pozycje ekranu y.
     * @return Zwraca wartosc ktora jest potrzebna jesli uzywasz input multiplexer.
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousepositionx = Gdx.input.getX() + (int)(camera.position.x-160)*4;
        mousepositiony = Math.abs(Gdx.input.getY()-720) + (int)(camera.position.y-90)*4;

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {

        return false;
    }

    /**
     * Funkcja ktora sprawdza na ktory hex kliknal gracz.
     * @param screenX pozycja x ekranu
     * @param screenY pozycja y ekranu
     * @param pointer wskaznik na wydarzenie
     * @param button przycisk
     * @return Zwraca wartosc ktora jest potrzebna jesli uzywasz input multiplexer.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        if(button == Input.Buttons.LEFT){
            for(int i = 0; i< Hex.BSIZE; i++)
                for(int j = 0; j< Hex.BSIZE; j++)
                {
                    int y = j * (Hex.s + Hex.t);
                    int x = i * Hex.h + (j%2) * Hex.h /2;
                    float[] points = Hex.hex(x,y);
                    Array<Vector2> polygon = new Array<>();
                    for (int k = 0; k < 12; k++) {
                        Vector2 v = new Vector2(points[k], points[k+1]);
                        polygon.add(v);
                        k++;
                    }
                    if(Intersector.isPointInPolygon(polygon,new Vector2((float)mousepositionx/4,(float) mousepositiony/4))) {
                        if(Hex.board[i][j] != -1 && Hex.board[i][j] !=1){
                            if (postac.getX() == i && (postac.getY() - j == 1 || postac.getY() - j == -1)) {
                                if(Hex.board[i][j] != -1)
                                {
                                    postac.move_player(i, j);
                                    camera.position.set(Hex.hexCenterx(postac.getX(), postac.getY()), Hex.hexCentery(postac.getY()),0);
                                }
                            }

                            if (postac.getY() == j && (postac.getX() - i == 1 || postac.getX() - i == -1)) {
                                if(Hex.board[i][j] != -1)
                                {
                                    postac.move_player(i, j);
                                    camera.position.set(Hex.hexCenterx(postac.getX(), postac.getY()), Hex.hexCentery(postac.getY()),0);
                                }
                            }

                            if (j%2 == 0 && postac.getX() - i == -1 && (postac.getY() - j == 1 || postac.getY() - j == -1)){
                                if(Hex.board[i][j] != -1)
                                {
                                    postac.move_player(i, j);
                                    camera.position.set(Hex.hexCenterx(postac.getX(), postac.getY()), Hex.hexCentery(postac.getY()),0);

                                }
                            }

                            if (j%2 == 1 && postac.getX() - i == 1 && (postac.getY() - j == 1 || postac.getY() - j == -1)){
                                if(Hex.board[i][j] != -1)
                                    {
                                    postac.move_player(i, j);
                                    camera.position.set(Hex.hexCenterx(postac.getX(), postac.getY()), Hex.hexCentery(postac.getY()),0);

                                }
                            }

                        }
                        else if(Hex.board[i][j] == 1){
                            if(!postac.fAttack){
                                postac.fAttack = true;
                                for (int k = 0; k < oni_bi.length; k++) {
                                    if(oni_bi[k] != null)
                                    if(oni_bi[k].getX() ==i && oni_bi[k].getY() == j)
                                        if(oni_bi[k].hit() < 0){
                                            oni_bi[k] = null;
                                            Hex.board[i][j] = 0;
                                            for(int l = 0;l<= oni_bi.length;l++)
                                            {
                                                if(l == oni_bi.length)
                                                    fMenu=0;
                                                else if (oni_bi[l] != null) {
                                                    break;
                                                }
                                            }
                                        }
                                }
                            }
                        }

                        if(camera.position.x - effectiveViewportWidth/2 < 0)
                            camera.position.x = 0 + effectiveViewportWidth/2;
                        else if(camera.position.x + effectiveViewportWidth/2 > 970)
                            camera.position.x = 970 - effectiveViewportWidth/2;
                        if(camera.position.y + effectiveViewportHeight/2 > 676)
                            camera.position.y = 676 - effectiveViewportHeight/2;
                        else if(camera.position.y - effectiveViewportHeight/2 < 0)
                            camera.position.y = 0 + effectiveViewportHeight/2;

                    }
                }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
    }
}
