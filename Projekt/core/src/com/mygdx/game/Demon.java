package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Demon {
    int x = 20;
    int y = 26;
    int health = 3;
    int aniStart = 0;
    int aniCurr = 0;

    TextureAtlas demonTexture;
    TextureRegion[] demon = new TextureRegion[6];

    TextureRegion texture;
    Demon(int x,int y){
        this.x = x;
        this.y = y;

    }

    void init(){
        demonTexture = new TextureAtlas("Oni-bi.atlas");
        demon[0] = demonTexture.findRegion("Oni-bi1");
        demon[1] = demonTexture.findRegion("Oni-bi2");
        demon[2] = demonTexture.findRegion("Oni-bi3");
        demon[3] = demonTexture.findRegion("Oni-bi4");
        demon[4] = demonTexture.findRegion("Oni-bi3");
        demon[5] = demonTexture.findRegion("Oni-bi2");
        texture = demon[0];
    }
    int getX(){return this.x;}
    int getY(){return this.y;}
    void update(){
        //System.out.println(aniCurr);
        if((aniCurr - aniStart) == 5)
            aniCurr = aniStart;
        else
            aniCurr++;
        texture = demon[aniCurr];
    }
    int hit(){


        health -=1;

        if(health < 0)
        {
           ;
        }
        //System.out.println(health);
        return health;
    }
}
