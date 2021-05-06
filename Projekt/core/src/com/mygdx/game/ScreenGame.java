package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ScreenGame extends InputAdapter implements Screen, InputProcessor {
    private static final float WORLD_WIDTH = 1280;
    private static final float WORLD_HEIGHT = 720;
    //screen
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    float zoompower = 10;
    boolean scrollingWindow;
    public int placementX = 0;
    public int placementY = 0;
    int prevX;
    int prevY;
    int mousepositionx = Gdx.input.getX();
    int mousepositiony = Math.abs(Gdx.input.getY()-720);
    Texture mapatest = new Texture("Map1.png");

    private int backgroundoffset;

    //mouse position


    Charcters postac = new Charcters();
    Hex hexy = new Hex(batch);

    public void create () {
        camera = new OrthographicCamera(1280/4,720/4);//320,180
        camera.position.set(160,90,0);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        postac.Load_characters();
    }

    void GameScreen(){
        batch = new SpriteBatch();
    }

    @Override
    public void render(float deltaTime) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(mapatest,0,0);
        Gdx.input.setInputProcessor(this);
        camera.update();
        Gdx.gl.glClearColor(0.3f,0.3f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.end();
        //hexy.drawHexes(batch,camera);
        for(int i = postac.getX()-1;i<=postac.getX()+1;i++)
            for(int j = postac.getY()-1;j<=postac.getY()+1;j++){
            if(i>-1 && j>-1 && i<49 && j<49)
                if(postac.getY()%2 == 1 &&  postac.getY()  != j && i - postac.getX() == -1 ){

                }
                else if(postac.getY()%2 == 0 &&  postac.getY()  != j && i - postac.getX() == 1){

                }
                else
                    hexy.drawHex(i,j,batch,camera,1);
            }

        batch.begin();
        //System.out.println("Postac: " + hexy.hexCenterx(postac.getX(), postac.getY()) + ", " +  hexy.hexCentery(postac.getY()));

        batch.draw(postac.Warrior[0], hexy.hexCenterx(postac.getX(), postac.getY()), hexy.hexCentery(postac.getY()));
        //System.out.println(postac.getX()+", "+postac.getY());
      //  batch.draw(postac.Warrior[0], 0, 0);
        batch.end();
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

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousepositionx = Gdx.input.getX() + (int)(camera.position.x-160)*4;
        mousepositiony = Math.abs(Gdx.input.getY()-720) + (int)(camera.position.y-90)*4;

        //System.out.println(camera.position);

        //System.out.println(mousepositionx/4 + ", " + mousepositiony/4);
        //System.out.println((placementX-160) + ", " + (placementY-90));
        //System.out.println((placementX) + ", " + (placementY));
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if(amountY > 0 && zoompower <=50f){
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            zoompower +=0.1f;
            camera.zoom += 0.1f;

        }
        else if(amountY < 0 && zoompower >=10.1f){
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            zoompower -=0.1f;
            camera.zoom -= 0.1f;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT){
            //System.out.println("YUP");
            //System.out.println( mousepositionx+ ", "+mousepositiony);
            for(int i=0;i<hexy.BSIZE;i++)
                for(int j=0;j<hexy.BSIZE;j++)
                {//TUTAJ TWORZY HEXY. CO JEST ZAKOMENTOWANE NIE RUSZ
                    int y = j * (hexy.s+hexy.t);
                    int x = i * hexy.h + (j%2) * hexy.h/2;
                    float[] points = hexy.hex(x,y);
                    //Polygon poly = new Polygon(points);
                    Array<Vector2> polygon = new Array<>();
                    for (int k = 0; k < 12; k++) {
                        Vector2 v = new Vector2(points[k], points[k+1]);
                        //System.out.println(v);
                        polygon.add(v);
                        k++;
                    }
                    //TUTAJ HUBERT, GDZIEś TUTAJ
                    if(Intersector.isPointInPolygon(polygon,new Vector2(mousepositionx/4,mousepositiony/4))) {//TUTAJ NAM SPRAWDZA KOLIZJE
                        if (postac.getX() == i && (postac.getY() - j == 1 || postac.getY() - j == -1)) {
                            postac.move_player(i, j);
                            //camera.translate(hexy.hexCenterx(postac.getX(), postac.getY()) -160, hexy.hexCentery(postac.getY())-90,0);
                            camera.position.set(hexy.hexCenterx(postac.getX(), postac.getY()), hexy.hexCentery(postac.getY()),0);
                            //System.out.println(hexy.hexCenterx(postac.getX(), postac.getY())+", "+ hexy.hexCentery(postac.getY()));
                        }

                        if (postac.getY() == j && (postac.getX() - i == 1 || postac.getX() - i == -1)) {
                            postac.move_player(i, j);
                            //camera.translate(hexy.hexCenterx(postac.getX(), postac.getY()) -160, hexy.hexCentery(postac.getY())-90,0);
                            camera.position.set(hexy.hexCenterx(postac.getX(), postac.getY()), hexy.hexCentery(postac.getY()),0);
                            System.out.println(hexy.hexCenterx(postac.getX(), postac.getY())+", "+ hexy.hexCentery(postac.getY()));
                        }

                        if (j%2 == 0 && postac.getX() - i == -1 && (postac.getY() - j == 1 || postac.getY() - j == -1)){
                            postac.move_player(i, j);
                            //camera.translate(hexy.hexCenterx(postac.getX(), postac.getY()) -160, hexy.hexCentery(postac.getY())-90,0);
                            camera.position.set(hexy.hexCenterx(postac.getX(), postac.getY()), hexy.hexCentery(postac.getY()),0);
                            System.out.println(hexy.hexCenterx(postac.getX(), postac.getY())+", "+ hexy.hexCentery(postac.getY()));
                        }

                        if (j%2 == 1 && postac.getX() - i == 1 && (postac.getY() - j == 1 || postac.getY() - j == -1)){
                            postac.move_player(i, j);
                            //camera.translate(hexy.hexCenterx(postac.getX(), postac.getY()) -160, hexy.hexCentery(postac.getY())-90,0);
                            camera.position.set(hexy.hexCenterx(postac.getX(), postac.getY()), hexy.hexCentery(postac.getY()),0);
                            System.out.println(hexy.hexCenterx(postac.getX(), postac.getY())+", "+ hexy.hexCentery(postac.getY()));
                    }
                        System.out.println(i+", "+j);
                    }
                }
        }
        if(button == Input.Buttons.RIGHT){
            prevX = screenX;
            prevY = screenY;
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
