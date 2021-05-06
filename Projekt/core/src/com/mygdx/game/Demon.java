package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Demon {
    int x = 20;
    int y = 26;

    TextureAtlas demonTexture;
    TextureRegion[] demon = new TextureRegion[4];

    Texture texture;

    void init(){
        demonTexture = new TextureAtlas("Oni-bi.atlas");
        demon[0] = demonTexture.findRegion("Oni-bi1");
        demon[1] = demonTexture.findRegion("Oni-bi2");
        demon[2] = demonTexture.findRegion("Oni-bi3");
        demon[3] = demonTexture.findRegion("Oni-bi4");
        //texture = demon[0];
    }
}
