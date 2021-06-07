package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Klasa przechowujaca wszystkie pola i metody dotyczace przeciwnika.
 */
public class Demon {
    /**
     * Poczatkowa pozycja x przeciwnika.
     */
    int x = 20;
    /**
     * Poczatkowa pozycja y przeciwnika.
     */
    int y = 26;
    /**
     * poczatkowe punkty zycia przeciwnika.
     */
    int health = 3;
    /**
     * Poczatkowa klatka animacji.
     */
    int aniStart = 0;
    /**
     * Obecna klatka animacji.
     */
    int aniCurr = 0;

    /**
     * Animacja demona.
     */
    TextureAtlas demonTexture;
    /**
     * Tablica tekstur przeciwnika.
     */
    TextureRegion[] demon = new TextureRegion[6];

    /**
     * Tekstura ktora przekazujemy do pokazania na ekranie.
     */
    TextureRegion texture;

    /**
     * @param x Pozycja x przeciwnika.
     * @param y Pozycja y przeciwnika.
     */
    Demon(int x,int y){
        this.x = x;
        this.y = y;

    }

    /**
     * Funkcja odpowiadajaca z wczytanie kazdej klatki animacji z atlasu.
     */
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

    /**
     * @return zwraca nam pozycje x hexa na ktorym znajduje sie aktualnie przeciwnik.
     */
    int getX(){return this.x;}

    /**
     * @return zwraca nam pozycje y hexa na ktorym znajduje sie aktualnie przeciwnik.
     */
    int getY(){return this.y;}

    /**
     * Funkcja zmieniajaca klatke animacji.
     */
    void update(){
        //System.out.println(aniCurr);
        if((aniCurr - aniStart) == 5)
            aniCurr = aniStart;
        else
            aniCurr++;
        texture = demon[aniCurr];
    }

    /**
     * @return zwraca obecne zycie przeciwnika.
     */
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
