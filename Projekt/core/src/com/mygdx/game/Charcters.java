package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Charcters {
    int x = 32;
    int y = 1;
    TextureAtlas playerTexture;
    TextureRegion[] Warrior = new TextureRegion[24];

    void Load_characters(){
        playerTexture = new TextureAtlas("Projekt.atlas");
        Warrior[0] = playerTexture.findRegion("warriorlewo1");
        Warrior[1] = playerTexture.findRegion("warriorlewo2");
        Warrior[2] = playerTexture.findRegion("warriorlewo3");
        Warrior[3] = playerTexture.findRegion("warriorlewo4");
        Warrior[4] = playerTexture.findRegion("warriorlewodol1");
        Warrior[5] = playerTexture.findRegion("warriorlewodol2");
        Warrior[6] = playerTexture.findRegion("warriorlewodol3");
        Warrior[7] = playerTexture.findRegion("warriorlewodol4");
        Warrior[8] = playerTexture.findRegion("warriorlewogora1");
        Warrior[9] = playerTexture.findRegion("warriorlewogora2");
        Warrior[10] = playerTexture.findRegion("warriorlewogora3");
        Warrior[11] = playerTexture.findRegion("warriorlewogora4");
        Warrior[12] = playerTexture.findRegion("warriorprawo1");
        Warrior[13] = playerTexture.findRegion("warriorprawo2");
        Warrior[14] = playerTexture.findRegion("warriorprawo3");
        Warrior[15] = playerTexture.findRegion("warriorprawo4");
        Warrior[16] = playerTexture.findRegion("warriorprawodol1");
        Warrior[17] = playerTexture.findRegion("warriorprawodol2");
        Warrior[18] = playerTexture.findRegion("warriorprawodol3");
        Warrior[19] = playerTexture.findRegion("warriorprawodol4");
        Warrior[20] = playerTexture.findRegion("warriorprawogora1");
        Warrior[21] = playerTexture.findRegion("warriorprawogora2");
        Warrior[22] = playerTexture.findRegion("warriorprawogora3");
        Warrior[23] = playerTexture.findRegion("warriorprawogora4");
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void move_player(int x,int y){
        this.x = x;
        this.y = y;
    }

}