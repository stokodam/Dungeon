package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ScreenGame extends InputAdapter implements Screen, InputProcessor {
    private static final float WORLD_WIDTH = 1280;
    private static final float WORLD_HEIGHT = 720;
    //screen
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    float zoompower = 10;
    int prevX;
    int prevY;
    int mousepositionx = Gdx.input.getX();
    int mousepositiony = Math.abs(Gdx.input.getY()-720);
    Texture mapatest = new Texture("Map1.png");


    //mouse position


    Charcters postac = new Charcters();
    Hex hexy = new Hex(batch);
    Demon oni_bi = new Demon();

    public void create () {
        camera = new OrthographicCamera(320,180);
        camera.position.set(hexy.hexCenterx(postac.getX(), postac.getY()), hexy.hexCentery(postac.getY()),0);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        postac.Load_characters();
        oni_bi.init();
        hexy.board[oni_bi.x][oni_bi.y] = 1;
    }


    void GameScreen(){
        batch = new SpriteBatch();
    }

    @Override
    public void render(float deltaTime) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        Gdx.input.setInputProcessor(this);
        camera.update();
        Gdx.gl.glClearColor(0.3f,0.3f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.end();

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
        if(oni_bi != null)
            hexy.drawHex(oni_bi.x,oni_bi.y,batch,camera,2);
        batch.begin();
        batch.draw(mapatest,0,0);
        if(oni_bi != null)
            batch.draw(oni_bi.demon[0],hexy.hexCenterx(oni_bi.x,oni_bi.y)-15,hexy.hexCentery(oni_bi.y)-20);
        batch.draw(postac.Warrior[0], hexy.hexCenterx(postac.getX(), postac.getY()), hexy.hexCentery(postac.getY()));
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

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        if(amountY > 0 && zoompower <=10.5f){
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
            camera.position.set(hexy.hexCenterx(postac.getX(), postac.getY()), hexy.hexCentery(postac.getY()),0);
        }

        if(camera.position.x - effectiveViewportWidth/2 < 0)
            camera.position.x = 0 + effectiveViewportWidth/2;
        else if(camera.position.x + effectiveViewportWidth/2 > 970)
            camera.position.x = 970 - effectiveViewportWidth/2;
        if(camera.position.y + effectiveViewportHeight/2 > 676)
            camera.position.y = 676 - effectiveViewportHeight/2;
        else if(camera.position.y - effectiveViewportHeight/2 < 0)
            camera.position.y = 0 + effectiveViewportHeight/2;

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        if(button == Input.Buttons.LEFT){
            for(int i=0;i<hexy.BSIZE;i++)
                for(int j=0;j<hexy.BSIZE;j++)
                {
                    int y = j * (hexy.s+hexy.t);
                    int x = i * hexy.h + (j%2) * hexy.h/2;
                    float[] points = hexy.hex(x,y);
                    Array<Vector2> polygon = new Array<>();
                    for (int k = 0; k < 12; k++) {
                        Vector2 v = new Vector2(points[k], points[k+1]);
                        polygon.add(v);
                        k++;
                    }
                    if(Intersector.isPointInPolygon(polygon,new Vector2(mousepositionx/4,mousepositiony/4))) {
                        if(hexy.board[i][j] != -1 && hexy.board[i][j] !=1){
                            if (postac.getX() == i && (postac.getY() - j == 1 || postac.getY() - j == -1)) {
                                if(hexy.board[i][j] != -1)
                                    postac.move_player(i, j);
                                    camera.position.set(hexy.hexCenterx(postac.getX(), postac.getY()), hexy.hexCentery(postac.getY()),0);

                            }

                            if (postac.getY() == j && (postac.getX() - i == 1 || postac.getX() - i == -1)) {
                                if(hexy.board[i][j] != -1)
                                    postac.move_player(i, j);
                                    camera.position.set(hexy.hexCenterx(postac.getX(), postac.getY()), hexy.hexCentery(postac.getY()),0);
                            }

                            if (j%2 == 0 && postac.getX() - i == -1 && (postac.getY() - j == 1 || postac.getY() - j == -1)){
                                if(hexy.board[i][j] != -1)
                                    postac.move_player(i, j);
                                    camera.position.set(hexy.hexCenterx(postac.getX(), postac.getY()), hexy.hexCentery(postac.getY()),0);
                            }

                            if (j%2 == 1 && postac.getX() - i == 1 && (postac.getY() - j == 1 || postac.getY() - j == -1)){
                                if(hexy.board[i][j] != -1)
                                    postac.move_player(i, j);
                                    camera.position.set(hexy.hexCenterx(postac.getX(), postac.getY()), hexy.hexCentery(postac.getY()),0);
                            }
                        }
                        else if(hexy.board[i][j] == 1){
                            if(oni_bi.hit() < 0){
                                oni_bi = null;
                                hexy.board[i][j] = 0;
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
